
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qx.dao.UserDao;
import com.qx.domain.CityDetail;
import com.qx.domain.StatisGradeCityDetail;
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
import java.net.URL;
import java.util.*;

/**
 * @Author: ZedQ
 * @Date: 2022/5/17 12:45
 * @Description:
 */
public class test {

    @Test
    public void doGetjd() throws Exception {
//        String keyword = "java";
//        String url = "https://search.jd.com/Search?keyword="+keyword;
//        String keyWord = "310000";
//        String url = "https://feiyan.wecity.qq.com/wuhan/dist/index.html#/map-detail?provinceCode="+keyWord;
        String keyWord = "statisGradeCityDetail,diseaseh5Shelf";
        String url = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules="+keyWord;
        Document document = Jsoup.parse(new URL(url),300000);
        System.out.println(document.body().html());
    }

    @Test
    public void totalDataOne() {
        Object res = "", res1 = "", res2="";
        JSONObject jsonObject = new JSONObject();
        String str="";
        String keyWord = "statisGradeCityDetail,diseaseh5Shelf";
        String url = "https://api.inews.qq.com/newsqa/v1/query/inner/publish/modules/list?modules=" + keyWord;
//        try {
//            Document doc = Jsoup.connect(url)
//                    .ignoreContentType(true)
//                    .userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)")
//                    .header("content-type", "application/json")
//                    .timeout(5000).get();
//            res = JSONUtil.parseObj(doc.body().html()).get("data");
//            res1 = JSONUtil.parseObj(res).get("statisGradeCityDetail");
//        System.out.println(res);
//            System.out.println("------------------------------------");
//            System.out.println("------------------------------------");
//            System.out.println("------------------------------------");
//            System.out.println("------------------------------------");
//            System.out.println("------------------------------------");
//            System.out.println("------------------------------------");
//            System.out.println(res1);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        try {
            Document doc = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)")
                    .header("content-type", "application/json")
                    .timeout(5000).get();
            Object data = JSONUtil.parseObj(doc.body().html()).get("data");
            Object statisGradeCityDetail = JSONUtil.parseObj(data).get("statisGradeCityDetail");
            String s = statisGradeCityDetail.toString();
            System.out.println(s);
            List <StatisGradeCityDetail> list = JSON.parseArray(s,StatisGradeCityDetail.class);

            for (StatisGradeCityDetail gradeCityDetail : list) {
                System.out.println(gradeCityDetail);
            }

            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            System.out.println("------------------------------------");
            List<CityDetail> cityDetails = new ArrayList<>();
            for (StatisGradeCityDetail  sg: list) {

                CityDetail cityDetail = new CityDetail();

                System.out.println(DateUtils.isSameDay(new Date(),sg.getMtime()));


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
            }
            for (CityDetail cityDetail : cityDetails) {
                System.out.println(cityDetail);
                sqls(cityDetail);
            }


//            按照省份sg分组
//            Map<String, List<StatisGradeCityDetail>> collect = list.stream().collect(Collectors.groupingBy(StatisGradeCityDetail::getProvince));
//            for (String s1 : collect.keySet()) {
//                System.out.println(s1);
//                System.out.println(collect.get(s1));
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




}
