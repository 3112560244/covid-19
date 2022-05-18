import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.qx.dao.CityDao;
import com.qx.dao.UserDao;
import com.qx.domain.City;
import com.qx.domain.CityDay;
import com.qx.domain.CityDetail;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @Author: ZedQ
 * @Date: 2022/5/18 22:27
 * @Description: 生成cityday数据  每日省份的总数居
 */
public class AllToday {


    @Test
    public void test1(){
        String str="";
        String keyWord = "statisGradeCityDetail,diseaseh5Shelf";
        String url = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=" + keyWord;



        try {
            Document doc = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)")
                    .header("content-type", "application/json")
                    .timeout(5000).get();
            //阿里巴巴
            //String类型json 转换成object  然后获取key 进行解耦
            String json = doc.body().html();
            Object data1 = JSON.parseObject(json).get("data");


            //暂不使用  其他数据
            Object diseaseh5Shelf = JSON.parseObject(data1.toString()).get("diseaseh5Shelf");
            String d1 = diseaseh5Shelf.toString();

            //各地区总数和按省份分类
            Object areaTree = JSON.parseObject(d1).get("areaTree");
//            System.out.println(areaTree);
            //children

            JSONArray jsonArray1 = JSON.parseArray(areaTree.toString());

            //json格式为   [{},{}] 但是对象数组里面的 对象格式不同  通过转换为 JSONArray 使用itear 或者for 便利每一个 {} ,{}
            for (Object o : jsonArray1) {
                areaTree = o;
            }



            Object children = JSON.parseObject(areaTree.toString()).get("children");

            JSONArray jsonArray = JSON.parseArray(children.toString());


            ArrayList<City> list = new ArrayList<>();
            for (Object o : jsonArray) {
                //城市名称
                String name = (String) JSON.parseObject(o.toString()).get("name");

                Object total = JSON.parseObject(o.toString()).get("total");
                //现存感染者
                Integer nowConfirm = (Integer) JSON.parseObject(total.toString()).get("nowConfirm");

                //死亡人数
                Integer dead = (Integer) JSON.parseObject(total.toString()).get("dead");
                City city = new City();
                city.setDead(dead);
                city.setNowConfirm(nowConfirm);
                city.setName(name);
                list.add(city);


            }

            sqlss(list);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //一次加入多个
    public void sqlss(ArrayList<City> list){
        String resource = "mybatis-config.xml";

        SqlSession sqlSession = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            CityDao cityDao = sqlSession.getMapper(CityDao.class);
            cityDao.saves(list);
            System.out.println("----------------------");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("打开数据库失败");
        }
        sqlSession.commit();
        sqlSession.close();
    }


    //从数据库读取最新cityday
    public ArrayList<CityDay> getCityDay(){
        String resource = "mybatis-config.xml";
        ArrayList<CityDay> list = null;
        SqlSession sqlSession = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            CityDao cityDao = sqlSession.getMapper(CityDao.class);
            list = cityDao.getAll();
            System.out.println("----------------------");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("打开数据库失败");
        }
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    //测试读取cityday
    @Test
    public void getTest(){
        ArrayList<CityDay> cityDay = getCityDay();
        Object o = JSON.toJSON(cityDay);
        System.out.println(o);
    }

}
