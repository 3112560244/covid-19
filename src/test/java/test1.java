
import com.alibaba.fastjson.JSON;
import com.qx.dao.UserDao;
import com.qx.domain.CityDetail;
import com.qx.domain.StatisGradeCityDetail;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.apache.commons.lang3.time.DateUtils;
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
import java.util.Date;
import java.util.List;

/**
 * @Author: ZedQ
 * @Date: 2022/5/18 19:11
 * @Description:
 */
public class test1{



    @Test
    public void getDate(){
        String str="";
        String keyWord = "statisGradeCityDetail,diseaseh5Shelf";
        String url = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=" + keyWord;



        try {
            Document doc = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)")
                    .header("content-type", "application/json")
                    .timeout(5000).get();

            //hutol
//            Object data = JSONUtil.parseObj(doc.body().html()).get("data");
//            Object statisGradeCityDetail = JSONUtil.parseObj(data).get("statisGradeCityDetail");
//            String s = statisGradeCityDetail.toString();
//            System.out.println(s);

            //阿里巴巴
            //String类型json 转换成object  然后获取key 进行解耦
            String json = doc.body().html();
            Object data1 = JSON.parseObject(json).get("data");


            //暂不使用  其他数据
            Object diseaseh5Shelf = JSON.parseObject(data1.toString()).get("diseaseh5Shelf");
            Object areaTree = JSON.parseObject(data1.toString()).get("areaTree");
            Object chinaTotal = JSON.parseObject(data1.toString()).get("chinaTotal");
            Object chinaAdd = JSON.parseObject(data1.toString()).get("chinaAdd");


            //拿到全国情况
            Object statisGradeCityDetail = JSON.parseObject(data1.toString()).get("statisGradeCityDetail");
            //转换成string
            String s = statisGradeCityDetail.toString();



            //转换为list数组
            List <StatisGradeCityDetail> list = JSON.parseArray(s,StatisGradeCityDetail.class);

            //筛选有效数据数组录入数据库
            ArrayList<CityDetail> cityDetails = new ArrayList<>();
            for (StatisGradeCityDetail  sg: list) {

                //是否今天的数据 DateUtils.isSameDay(new Date(),sg.getMtime())
                System.out.println(DateUtils.isSameDay(new Date(),sg.getMtime()));

                if(!DateUtils.isSameDay(new Date(),sg.getMtime())){
                    continue;
                }


                CityDetail cityDetail = new CityDetail();

                cityDetail.setMtime(sg.getMtime());
                cityDetail.setSyear(sg.getSyear()==0?2022: sg.getSyear());
                cityDetail.setDate(sg.getDate());
                cityDetail.setProvince(sg.getProvince());
                cityDetail.setCity(sg.getCity());

                cityDetail.setConfirmAdd(sg.getConfirmAdd());
                cityDetail.setWzz_add(sg.getWzz_add());
                cityDetail.setNowConfirm(sg.getNowConfirm());

                cityDetail.setConfirm(sg.getConfirm());
                cityDetail.setHeal(sg.getHeal());
                cityDetail.setDead(sg.getDead());
                cityDetails.add(cityDetail);


                System.out.println(cityDetail);
            }
            //导入集合
            sqlss(cityDetails);


            //单条记录导入
//            for (CityDetail cityDetail : cityDetails) {
//                System.out.println(cityDetail);
//                sqls(cityDetail);
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }













    public void sqls(CityDetail cityDetail){
        String resource = "mybatis-config.xml";

        SqlSession sqlSession = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);

            userDao.save(cityDetail);
            System.out.println("----------------------");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("打开数据库失败");
        }
        sqlSession.commit();
        sqlSession.close();
    }


    public void sqlss(ArrayList<CityDetail> list){
        String resource = "mybatis-config.xml";

        SqlSession sqlSession = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            userDao.saves(list);
            System.out.println("----------------------");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("打开数据库失败");
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
