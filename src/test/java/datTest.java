
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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

import javax.servlet.ServletOutputStream;
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
public class datTest {


    @Test
    public void tday(){
        String json = "[{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/17\",\"city\":\"海淀\",\"dead\":0,\"mtime\":\"2022-05-17 09:35:15\",\"confirm\":215,\"province\":\"北京\",\"confirmAdd\":5,\"grade\":\"点击查看详情\",\"heal\":26,\"nowConfirm\":189,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"18\",\"sdate\":\"05/17\",\"city\":\"嘉定\",\"dead\":2,\"mtime\":\"2022-05-17 09:17:36\",\"confirm\":2596,\"province\":\"上海\",\"confirmAdd\":9,\"grade\":\"点击查看详情\",\"heal\":2358,\"nowConfirm\":236,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/17\",\"city\":\"延边\",\"dead\":0,\"mtime\":\"2022-05-17 09:08:20\",\"confirm\":176,\"province\":\"吉林\",\"confirmAdd\":2,\"grade\":\"点击查看详情\",\"heal\":170,\"nowConfirm\":6,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"47\",\"sdate\":\"05/17\",\"city\":\"虹口\",\"dead\":0,\"mtime\":\"2022-05-17 09:33:28\",\"confirm\":3630,\"confirmAdd\":2,\"province\":\"上海\",\"grade\":\"点击查看详情\",\"heal\":2632,\"nowConfirm\":998,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"4\",\"sdate\":\"05/17\",\"city\":\"朝阳\",\"dead\":0,\"mtime\":\"2022-05-17 09:35:15\",\"confirm\":478,\"province\":\"北京\",\"confirmAdd\":6,\"grade\":\"点击查看详情\",\"heal\":21,\"nowConfirm\":457,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"39\",\"sdate\":\"05/17\",\"city\":\"闵行\",\"dead\":0,\"mtime\":\"2022-05-17 09:34:25\",\"confirm\":5374,\"confirmAdd\":9,\"province\":\"上海\",\"grade\":\"点击查看详情\",\"heal\":4713,\"nowConfirm\":661,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/17\",\"city\":\"无锡\",\"dead\":0,\"mtime\":\"2022-05-17 10:06:20\",\"confirm\":74,\"confirmAdd\":1,\"province\":\"江苏\",\"grade\":\"点击查看详情\",\"heal\":55,\"nowConfirm\":19,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/17\",\"city\":\"西城\",\"dead\":0,\"mtime\":\"2022-05-17 09:35:15\",\"confirm\":88,\"province\":\"北京\",\"confirmAdd\":1,\"grade\":\"点击查看详情\",\"heal\":6,\"nowConfirm\":82,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"48\",\"sdate\":\"05/17\",\"city\":\"静安\",\"dead\":1,\"mtime\":\"2022-05-17 09:34:25\",\"confirm\":3162,\"province\":\"上海\",\"confirmAdd\":14,\"grade\":\"点击查看详情\",\"heal\":2416,\"nowConfirm\":745,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"17\",\"sdate\":\"05/17\",\"city\":\"郑州\",\"dead\":5,\"mtime\":\"2022-05-17 10:22:12\",\"confirm\":598,\"province\":\"河南\",\"confirmAdd\":8,\"grade\":\"点击查看详情\",\"heal\":483,\"nowConfirm\":110,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"240\",\"sdate\":\"05/17\",\"city\":\"杨浦\",\"dead\":0,\"mtime\":\"2022-05-17 09:20:27\",\"confirm\":2126,\"province\":\"上海\",\"confirmAdd\":17,\"grade\":\"点击查看详情\",\"heal\":1194,\"nowConfirm\":932,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"6\",\"sdate\":\"05/17\",\"city\":\"白山\",\"dead\":0,\"mtime\":\"2022-05-17 15:53:31\",\"confirm\":14,\"confirmAdd\":2,\"province\":\"吉林\",\"grade\":\"点击查看详情\",\"heal\":11,\"nowConfirm\":3,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/17\",\"city\":\"大兴\",\"dead\":0,\"mtime\":\"2022-05-17 09:35:15\",\"confirm\":160,\"confirmAdd\":1,\"province\":\"北京\",\"grade\":\"点击查看详情\",\"heal\":111,\"nowConfirm\":49,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"86\",\"sdate\":\"05/17\",\"city\":\"浦东\",\"dead\":1,\"mtime\":\"2022-05-17 09:33:28\",\"confirm\":17012,\"province\":\"上海\",\"confirmAdd\":3,\"grade\":\"点击查看详情\",\"heal\":15902,\"nowConfirm\":1109,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"2\",\"sdate\":\"05/17\",\"city\":\"房山\",\"dead\":0,\"mtime\":\"2022-05-17 09:35:15\",\"confirm\":248,\"confirmAdd\":10,\"province\":\"北京\",\"grade\":\"点击查看详情\",\"heal\":12,\"nowConfirm\":236,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"79\",\"sdate\":\"05/17\",\"city\":\"黄浦\",\"dead\":0,\"mtime\":\"2022-05-17 09:34:24\",\"confirm\":6538,\"confirmAdd\":10,\"province\":\"上海\",\"grade\":\"点击查看详情\",\"heal\":5218,\"nowConfirm\":1320,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/17\",\"city\":\"北辰区\",\"dead\":0,\"mtime\":\"2022-05-17 10:54:16\",\"confirm\":24,\"province\":\"天津\",\"confirmAdd\":1,\"grade\":\"点击查看详情\",\"heal\":23,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"71\",\"sdate\":\"05/17\",\"city\":\"宝山\",\"dead\":1,\"mtime\":\"2022-05-17 09:17:36\",\"confirm\":3017,\"confirmAdd\":7,\"province\":\"上海\",\"grade\":\"点击查看详情\",\"heal\":2460,\"nowConfirm\":556,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"5\",\"sdate\":\"05/17\",\"city\":\"金山\",\"dead\":0,\"mtime\":\"2022-05-17 09:35:17\",\"confirm\":339,\"province\":\"上海\",\"confirmAdd\":1,\"grade\":\"点击查看详情\",\"heal\":328,\"nowConfirm\":11,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/17\",\"city\":\"厦门\",\"dead\":0,\"mtime\":\"2022-05-17 11:01:45\",\"confirm\":320,\"province\":\"福建\",\"confirmAdd\":1,\"grade\":\"点击查看详情\",\"heal\":285,\"nowConfirm\":35,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"72\",\"sdate\":\"05/17\",\"city\":\"广安\",\"dead\":0,\"mtime\":\"2022-05-17 11:05:35\",\"confirm\":82,\"province\":\"四川\",\"confirmAdd\":15,\"grade\":\"点击查看详情\",\"heal\":31,\"nowConfirm\":51,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/17\",\"city\":\"泰安\",\"dead\":2,\"mtime\":\"2022-05-17 16:02:35\",\"confirm\":47,\"confirmAdd\":1,\"province\":\"山东\",\"grade\":\"点击查看详情\",\"heal\":41,\"nowConfirm\":4,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"2\",\"sdate\":\"05/17\",\"city\":\"丰台\",\"dead\":0,\"mtime\":\"2022-05-17 09:35:15\",\"confirm\":448,\"confirmAdd\":10,\"province\":\"北京\",\"grade\":\"点击查看详情\",\"heal\":237,\"nowConfirm\":211,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"19\",\"sdate\":\"05/17\",\"city\":\"青浦\",\"dead\":0,\"mtime\":\"2022-05-17 09:34:25\",\"confirm\":1324,\"province\":\"上海\",\"confirmAdd\":1,\"grade\":\"点击查看详情\",\"heal\":1253,\"nowConfirm\":71,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"8\",\"sdate\":\"05/17\",\"city\":\"普陀\",\"dead\":0,\"mtime\":\"2022-05-17 09:20:27\",\"confirm\":1778,\"confirmAdd\":2,\"province\":\"上海\",\"grade\":\"点击查看详情\",\"heal\":1291,\"nowConfirm\":487,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/17\",\"city\":\"门头沟\",\"dead\":0,\"mtime\":\"2022-05-17 09:35:15\",\"confirm\":12,\"confirmAdd\":2,\"province\":\"北京\",\"grade\":\"点击查看详情\",\"heal\":4,\"nowConfirm\":8,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"9\",\"sdate\":\"05/17\",\"city\":\"松江\",\"dead\":0,\"mtime\":\"2022-05-17 09:32:33\",\"confirm\":2957,\"confirmAdd\":2,\"province\":\"上海\",\"grade\":\"点击查看详情\",\"heal\":2894,\"nowConfirm\":63,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"4\",\"sdate\":\"05/17\",\"city\":\"许昌\",\"dead\":1,\"mtime\":\"2022-05-17 10:22:13\",\"confirm\":535,\"province\":\"河南\",\"confirmAdd\":3,\"grade\":\"全部低风险\",\"heal\":404,\"nowConfirm\":130,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/16\",\"city\":\"湛江\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:02\",\"confirm\":107,\"province\":\"广东\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":23,\"nowConfirm\":84,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"52\",\"sdate\":\"05/16\",\"city\":\"徐汇\",\"dead\":1,\"mtime\":\"2022-05-17 11:33:59\",\"confirm\":4653,\"confirmAdd\":0,\"province\":\"上海\",\"grade\":\"点击查看详情\",\"heal\":3516,\"nowConfirm\":1136,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/16\",\"city\":\"泉州\",\"dead\":0,\"mtime\":\"2022-05-17 10:38:11\",\"confirm\":1187,\"province\":\"福建\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":1179,\"nowConfirm\":8,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/16\",\"city\":\"温州\",\"dead\":1,\"mtime\":\"2022-05-17 11:34:05\",\"confirm\":521,\"province\":\"浙江\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":503,\"nowConfirm\":17,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/16\",\"city\":\"通州\",\"dead\":9,\"mtime\":\"2022-05-17 11:33:52\",\"confirm\":96,\"province\":\"北京\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":3,\"nowConfirm\":84,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/16\",\"city\":\"绍兴\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:06\",\"confirm\":452,\"confirmAdd\":0,\"province\":\"浙江\",\"grade\":\"点击查看详情\",\"heal\":430,\"nowConfirm\":22,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/16\",\"city\":\"遵义\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:19\",\"confirm\":56,\"confirmAdd\":0,\"province\":\"贵州\",\"grade\":\"点击查看详情\",\"heal\":53,\"nowConfirm\":3,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/16\",\"city\":\"佛山\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:03\",\"confirm\":106,\"province\":\"广东\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":101,\"nowConfirm\":5,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"1\",\"sdate\":\"05/16\",\"city\":\"平顶山\",\"dead\":1,\"mtime\":\"2022-05-17 11:33:55\",\"confirm\":70,\"province\":\"河南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":58,\"nowConfirm\":11,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/16\",\"city\":\"营口\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:01\",\"confirm\":218,\"confirmAdd\":0,\"province\":\"辽宁\",\"grade\":\"点击查看详情\",\"heal\":125,\"nowConfirm\":93,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"24\",\"sdate\":\"05/15\",\"city\":\"长宁\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:59\",\"confirm\":2400,\"province\":\"上海\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":1876,\"nowConfirm\":524,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"1\",\"sdate\":\"05/15\",\"city\":\"奉贤\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:00\",\"confirm\":239,\"confirmAdd\":0,\"province\":\"上海\",\"grade\":\"点击查看详情\",\"heal\":153,\"nowConfirm\":86,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/15\",\"city\":\"黔东南州\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:54\",\"confirm\":14,\"confirmAdd\":0,\"province\":\"贵州\",\"grade\":\"点击查看详情\",\"heal\":11,\"nowConfirm\":3,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"2\",\"sdate\":\"05/15\",\"city\":\"唐山\",\"dead\":1,\"mtime\":\"2022-05-17 11:30:39\",\"confirm\":96,\"confirmAdd\":0,\"province\":\"河北\",\"grade\":\"点击查看详情\",\"heal\":75,\"nowConfirm\":20,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/14\",\"city\":\"岳阳\",\"dead\":1,\"mtime\":\"2022-05-17 11:34:00\",\"confirm\":159,\"confirmAdd\":0,\"province\":\"湖南\",\"grade\":\"点击查看详情\",\"heal\":157,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/14\",\"city\":\"渝北区\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:07\",\"confirm\":21,\"province\":\"重庆\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":20,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/14\",\"city\":\"宜宾\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:46\",\"confirm\":14,\"province\":\"四川\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":13,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/14\",\"city\":\"云阳县\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:05\",\"confirm\":28,\"confirmAdd\":0,\"province\":\"重庆\",\"grade\":\"点击查看详情\",\"heal\":27,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/13\",\"city\":\"顺义\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:52\",\"confirm\":99,\"province\":\"北京\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":45,\"nowConfirm\":54,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/13\",\"city\":\"中山\",\"dead\":0,\"mtime\":\"2022-05-17 11:30:36\",\"confirm\":84,\"confirmAdd\":0,\"province\":\"广东\",\"grade\":\"点击查看详情\",\"heal\":71,\"nowConfirm\":13,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/13\",\"city\":\"崇明\",\"dead\":0,\"mtime\":\"2022-05-17 11:30:34\",\"confirm\":393,\"confirmAdd\":0,\"province\":\"上海\",\"grade\":\"点击查看详情\",\"heal\":225,\"nowConfirm\":168,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/13\",\"city\":\"德阳\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:16\",\"confirm\":21,\"confirmAdd\":0,\"province\":\"四川\",\"grade\":\"点击查看详情\",\"heal\":19,\"nowConfirm\":2,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/13\",\"city\":\"广州\",\"dead\":1,\"mtime\":\"2022-05-17 11:30:35\",\"confirm\":948,\"province\":\"广东\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":609,\"nowConfirm\":338,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/13\",\"city\":\"巴南区\",\"dead\":0,\"mtime\":\"2022-05-17 11:30:36\",\"confirm\":13,\"confirmAdd\":0,\"province\":\"重庆\",\"grade\":\"点击查看详情\",\"heal\":12,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"3\",\"sdate\":\"05/12\",\"city\":\"西宁\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:39\",\"confirm\":119,\"province\":\"青海\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":81,\"nowConfirm\":38,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/12\",\"city\":\"沈阳\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:00\",\"confirm\":243,\"province\":\"辽宁\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":151,\"nowConfirm\":92,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/11\",\"city\":\"信阳\",\"dead\":2,\"mtime\":\"2022-05-17 11:32:23\",\"confirm\":288,\"confirmAdd\":0,\"province\":\"河南\",\"grade\":\"点击查看详情\",\"heal\":275,\"nowConfirm\":11,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/11\",\"city\":\"嘉兴\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:33\",\"confirm\":176,\"province\":\"浙江\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":46,\"nowConfirm\":130,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/11\",\"city\":\"濮阳\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:25\",\"confirm\":27,\"province\":\"河南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":17,\"nowConfirm\":10,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/10\",\"city\":\"深圳\",\"dead\":3,\"mtime\":\"2022-05-17 11:34:02\",\"confirm\":1385,\"province\":\"广东\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":778,\"nowConfirm\":604,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/10\",\"city\":\"宁德\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:04\",\"confirm\":77,\"confirmAdd\":0,\"province\":\"福建\",\"grade\":\"点击查看详情\",\"heal\":26,\"nowConfirm\":51,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/10\",\"city\":\"昌平\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:52\",\"confirm\":84,\"province\":\"北京\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":50,\"nowConfirm\":34,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/09\",\"city\":\"杭州\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:05\",\"confirm\":530,\"province\":\"浙江\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":328,\"nowConfirm\":202,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/09\",\"city\":\"怀化\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:26\",\"confirm\":65,\"confirmAdd\":0,\"province\":\"湖南\",\"grade\":\"点击查看详情\",\"heal\":64,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/08\",\"city\":\"长春\",\"dead\":0,\"mtime\":\"2022-05-17 09:38:38\",\"confirm\":25176,\"province\":\"吉林\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":24523,\"nowConfirm\":653,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/08\",\"city\":\"定州\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:52\",\"confirm\":8,\"province\":\"河北\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":2,\"nowConfirm\":6,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/08\",\"city\":\"东城\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:53\",\"confirm\":50,\"province\":\"北京\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":6,\"nowConfirm\":44,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/08\",\"city\":\"哈尔滨\",\"dead\":4,\"mtime\":\"2022-05-17 10:45:11\",\"confirm\":1341,\"confirmAdd\":0,\"province\":\"黑龙江\",\"grade\":\"点击查看详情\",\"heal\":1331,\"nowConfirm\":6,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/08\",\"city\":\"周口\",\"dead\":1,\"mtime\":\"2022-05-17 11:33:54\",\"confirm\":183,\"confirmAdd\":0,\"province\":\"河南\",\"grade\":\"点击查看详情\",\"heal\":100,\"nowConfirm\":82,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/07\",\"city\":\"济南\",\"dead\":0,\"mtime\":\"2022-05-17 09:38:37\",\"confirm\":135,\"confirmAdd\":0,\"province\":\"山东\",\"grade\":\"点击查看详情\",\"heal\":103,\"nowConfirm\":32,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/07\",\"city\":\"开封\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:24\",\"confirm\":39,\"province\":\"河南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":33,\"nowConfirm\":6,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/07\",\"city\":\"湘西自治州\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:27\",\"confirm\":13,\"province\":\"湖南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":10,\"nowConfirm\":3,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/07\",\"city\":\"长沙\",\"dead\":2,\"mtime\":\"2022-05-17 11:32:26\",\"confirm\":274,\"province\":\"湖南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":267,\"nowConfirm\":5,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/07\",\"city\":\"商丘\",\"dead\":3,\"mtime\":\"2022-05-17 11:32:24\",\"confirm\":112,\"province\":\"河南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":106,\"nowConfirm\":3,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/06\",\"city\":\"上饶\",\"dead\":0,\"mtime\":\"2022-05-17 10:16:19\",\"confirm\":328,\"province\":\"江西\",\"confirmAdd\":0,\"grade\":\"全部低风险\",\"heal\":323,\"nowConfirm\":5,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/06\",\"city\":\"石景山\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:53\",\"confirm\":28,\"province\":\"北京\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":12,\"nowConfirm\":16,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/06\",\"city\":\"九龙坡区\",\"dead\":1,\"mtime\":\"2022-05-17 11:34:06\",\"confirm\":23,\"province\":\"重庆\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":21,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/06\",\"city\":\"湘潭\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:03\",\"confirm\":41,\"province\":\"湖南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":39,\"nowConfirm\":2,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/05\",\"city\":\"淮安\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:45\",\"confirm\":83,\"province\":\"江苏\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":78,\"nowConfirm\":5,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"1\",\"sdate\":\"05/05\",\"city\":\"成都\",\"dead\":3,\"mtime\":\"2022-05-17 11:32:14\",\"confirm\":319,\"confirmAdd\":0,\"province\":\"四川\",\"grade\":\"点击查看详情\",\"heal\":248,\"nowConfirm\":68,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/05\",\"city\":\"南京\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:45\",\"confirm\":369,\"confirmAdd\":0,\"province\":\"江苏\",\"grade\":\"点击查看详情\",\"heal\":331,\"nowConfirm\":38,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/05\",\"city\":\"湖州\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:33\",\"confirm\":37,\"province\":\"浙江\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":10,\"nowConfirm\":27,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/05\",\"city\":\"密云\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:23\",\"confirm\":9,\"province\":\"北京\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":7,\"nowConfirm\":2,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/05\",\"city\":\"舟山\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:34\",\"confirm\":28,\"confirmAdd\":0,\"province\":\"浙江\",\"grade\":\"点击查看详情\",\"heal\":10,\"nowConfirm\":18,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/04\",\"city\":\"牡丹江\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:41\",\"confirm\":100,\"province\":\"黑龙江\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":97,\"nowConfirm\":3,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/04\",\"city\":\"盐城\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:37\",\"confirm\":68,\"province\":\"江苏\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":27,\"nowConfirm\":41,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/03\",\"city\":\"呼伦贝尔\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:56\",\"confirm\":658,\"province\":\"内蒙古\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":656,\"nowConfirm\":2,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/03\",\"city\":\"太原\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:11\",\"confirm\":109,\"province\":\"山西\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":104,\"nowConfirm\":5,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"\",\"sdate\":\"05/02\",\"city\":\"经济开发区\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:08\",\"confirm\":4,\"province\":\"北京\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":0,\"nowConfirm\":4,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/01\",\"city\":\"泰州\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:45\",\"confirm\":47,\"province\":\"江苏\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":37,\"nowConfirm\":10,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/01\",\"city\":\"邵阳\",\"dead\":1,\"mtime\":\"2022-05-17 11:32:26\",\"confirm\":154,\"province\":\"湖南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":102,\"nowConfirm\":51,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"05/01\",\"city\":\"安阳\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:23\",\"confirm\":555,\"province\":\"河南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":522,\"nowConfirm\":33,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/30\",\"city\":\"丽水\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:29\",\"confirm\":22,\"province\":\"浙江\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":17,\"nowConfirm\":5,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"1\",\"sdate\":\"04/30\",\"city\":\"徐州\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:11\",\"confirm\":153,\"confirmAdd\":0,\"province\":\"江苏\",\"grade\":\"点击查看详情\",\"heal\":79,\"nowConfirm\":74,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/29\",\"city\":\"金华\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:28\",\"confirm\":142,\"province\":\"浙江\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":57,\"nowConfirm\":85,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/29\",\"city\":\"文山州\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:17\",\"confirm\":13,\"confirmAdd\":0,\"province\":\"云南\",\"grade\":\"点击查看详情\",\"heal\":5,\"nowConfirm\":8,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/29\",\"city\":\"吉林市\",\"dead\":1,\"mtime\":\"2022-05-17 11:30:38\",\"confirm\":14097,\"confirmAdd\":0,\"province\":\"吉林\",\"grade\":\"点击查看详情\",\"heal\":14089,\"nowConfirm\":7,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/29\",\"city\":\"衢州\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:33\",\"confirm\":116,\"province\":\"浙江\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":22,\"nowConfirm\":94,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/28\",\"city\":\"宁波\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:28\",\"confirm\":330,\"province\":\"浙江\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":269,\"nowConfirm\":61,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/28\",\"city\":\"宿迁\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:37\",\"confirm\":29,\"province\":\"江苏\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":16,\"nowConfirm\":13,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/28\",\"city\":\"保定\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:51\",\"confirm\":56,\"province\":\"河北\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":38,\"nowConfirm\":18,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/28\",\"city\":\"洛阳\",\"dead\":1,\"mtime\":\"2022-05-17 11:32:24\",\"confirm\":45,\"confirmAdd\":0,\"province\":\"河南\",\"grade\":\"点击查看详情\",\"heal\":40,\"nowConfirm\":4,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/27\",\"city\":\"苏州\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:36\",\"confirm\":228,\"province\":\"江苏\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":205,\"nowConfirm\":23,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/24\",\"city\":\"秦皇岛\",\"dead\":1,\"mtime\":\"2022-05-17 11:32:52\",\"confirm\":22,\"province\":\"河北\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":9,\"nowConfirm\":12,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/24\",\"city\":\"红河\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:38\",\"confirm\":38,\"province\":\"云南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":9,\"nowConfirm\":29,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/23\",\"city\":\"宣城\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:18\",\"confirm\":9,\"province\":\"安徽\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":8,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/22\",\"city\":\"昆明\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:38\",\"confirm\":68,\"province\":\"云南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":62,\"nowConfirm\":6,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/22\",\"city\":\"西双版纳州\",\"dead\":1,\"mtime\":\"2022-05-17 11:34:16\",\"confirm\":21,\"province\":\"云南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":14,\"nowConfirm\":6,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/21\",\"city\":\"衡水\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:52\",\"confirm\":14,\"province\":\"河北\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":13,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/21\",\"city\":\"漯河\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:55\",\"confirm\":44,\"confirmAdd\":0,\"province\":\"河南\",\"grade\":\"点击查看详情\",\"heal\":36,\"nowConfirm\":8,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/17\",\"city\":\"汕头\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:10\",\"confirm\":26,\"confirmAdd\":0,\"province\":\"广东\",\"grade\":\"点击查看详情\",\"heal\":25,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/17\",\"city\":\"韶关\",\"dead\":1,\"mtime\":\"2022-05-17 11:33:11\",\"confirm\":12,\"province\":\"广东\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":9,\"nowConfirm\":2,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/16\",\"city\":\"三亚\",\"dead\":1,\"mtime\":\"2022-05-17 11:32:18\",\"confirm\":129,\"province\":\"海南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":57,\"nowConfirm\":71,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/16\",\"city\":\"四平\",\"dead\":1,\"mtime\":\"2022-05-17 11:34:13\",\"confirm\":244,\"confirmAdd\":0,\"province\":\"吉林\",\"grade\":\"点击查看详情\",\"heal\":242,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/14\",\"city\":\"邯郸\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:51\",\"confirm\":64,\"confirmAdd\":0,\"province\":\"河北\",\"grade\":\"点击查看详情\",\"heal\":41,\"nowConfirm\":23,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/12\",\"city\":\"连云港\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:36\",\"confirm\":195,\"confirmAdd\":0,\"province\":\"江苏\",\"grade\":\"点击查看详情\",\"heal\":193,\"nowConfirm\":2,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/08\",\"city\":\"镇江\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:38\",\"confirm\":24,\"province\":\"江苏\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":12,\"nowConfirm\":12,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"04/08\",\"city\":\"遂宁\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:46\",\"confirm\":22,\"province\":\"四川\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":21,\"nowConfirm\":1,\"syear\":2022},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"莆田\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:13\",\"confirm\":322,\"confirmAdd\":0,\"province\":\"福建\",\"grade\":\"点击查看详情\",\"heal\":275,\"nowConfirm\":47,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"\",\"sdate\":\"\",\"city\":\"万宁\",\"dead\":0,\"mtime\":\"2022-05-17 11:30:38\",\"confirm\":14,\"confirmAdd\":0,\"province\":\"海南\",\"grade\":\"点击查看详情\",\"heal\":13,\"nowConfirm\":1,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"临沂\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:48\",\"confirm\":66,\"province\":\"山东\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":62,\"nowConfirm\":4,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"1\",\"sdate\":\"\",\"city\":\"台州\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:28\",\"confirm\":149,\"province\":\"浙江\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":147,\"nowConfirm\":2,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"云浮\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:12\",\"confirm\":7,\"province\":\"广东\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":0,\"nowConfirm\":7,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"茂名\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:10\",\"confirm\":21,\"province\":\"广东\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":14,\"nowConfirm\":7,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"保山市\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:18\",\"confirm\":11,\"province\":\"云南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":9,\"nowConfirm\":2,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"惠州\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:09\",\"confirm\":70,\"province\":\"广东\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":62,\"nowConfirm\":8,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"乐山\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:46\",\"confirm\":12,\"province\":\"四川\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":8,\"nowConfirm\":4,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"梅州\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:10\",\"confirm\":18,\"province\":\"广东\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":17,\"nowConfirm\":1,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"海口\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:51\",\"confirm\":51,\"confirmAdd\":0,\"province\":\"海南\",\"grade\":\"点击查看详情\",\"heal\":41,\"nowConfirm\":10,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"临沧\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:17\",\"confirm\":13,\"confirmAdd\":0,\"province\":\"云南\",\"grade\":\"点击查看详情\",\"heal\":1,\"nowConfirm\":12,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"琼海\",\"dead\":1,\"mtime\":\"2022-05-17 11:32:19\",\"confirm\":11,\"province\":\"海南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":5,\"nowConfirm\":5,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"铁岭\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:05\",\"confirm\":14,\"province\":\"辽宁\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":12,\"nowConfirm\":2,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"葫芦岛\",\"dead\":1,\"mtime\":\"2022-05-17 11:33:04\",\"confirm\":227,\"confirmAdd\":0,\"province\":\"辽宁\",\"grade\":\"点击查看详情\",\"heal\":207,\"nowConfirm\":19,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"沧州\",\"dead\":3,\"mtime\":\"2022-05-17 11:32:50\",\"confirm\":138,\"province\":\"河北\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":58,\"nowConfirm\":77,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"阳江\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:11\",\"confirm\":15,\"confirmAdd\":0,\"province\":\"广东\",\"grade\":\"点击查看详情\",\"heal\":14,\"nowConfirm\":1,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"漳州\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:14\",\"confirm\":40,\"confirmAdd\":0,\"province\":\"福建\",\"grade\":\"点击查看详情\",\"heal\":23,\"nowConfirm\":17,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"南通\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:37\",\"confirm\":41,\"province\":\"江苏\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":40,\"nowConfirm\":1,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"福州\",\"dead\":1,\"mtime\":\"2022-05-17 11:33:13\",\"confirm\":74,\"confirmAdd\":0,\"province\":\"福建\",\"grade\":\"点击查看详情\",\"heal\":71,\"nowConfirm\":2,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"珠海\",\"dead\":1,\"mtime\":\"2022-05-17 11:33:09\",\"confirm\":146,\"confirmAdd\":0,\"province\":\"广东\",\"grade\":\"点击查看详情\",\"heal\":115,\"nowConfirm\":30,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"常州\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:36\",\"confirm\":84,\"province\":\"江苏\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":54,\"nowConfirm\":30,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"汕尾\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:12\",\"confirm\":8,\"province\":\"广东\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":6,\"nowConfirm\":2,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"锦州\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:05\",\"confirm\":15,\"confirmAdd\":0,\"province\":\"辽宁\",\"grade\":\"点击查看详情\",\"heal\":14,\"nowConfirm\":1,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"河源\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:12\",\"confirm\":7,\"confirmAdd\":0,\"province\":\"广东\",\"grade\":\"点击查看详情\",\"heal\":5,\"nowConfirm\":2,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"永州\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:03\",\"confirm\":45,\"province\":\"湖南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":44,\"nowConfirm\":1,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"三明\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:14\",\"confirm\":15,\"province\":\"福建\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":14,\"nowConfirm\":1,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"廊坊\",\"dead\":0,\"mtime\":\"2022-05-17 11:32:49\",\"confirm\":314,\"province\":\"河北\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":175,\"nowConfirm\":139,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"儋州\",\"dead\":1,\"mtime\":\"2022-05-17 11:32:19\",\"confirm\":17,\"province\":\"海南\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":14,\"nowConfirm\":2,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"大连\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:04\",\"confirm\":620,\"confirmAdd\":0,\"province\":\"辽宁\",\"grade\":\"点击查看详情\",\"heal\":603,\"nowConfirm\":17,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"鞍山\",\"dead\":0,\"mtime\":\"2022-05-17 11:33:04\",\"confirm\":19,\"confirmAdd\":0,\"province\":\"辽宁\",\"grade\":\"点击查看详情\",\"heal\":14,\"nowConfirm\":5,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"肇庆\",\"dead\":1,\"mtime\":\"2022-05-17 11:33:09\",\"confirm\":27,\"confirmAdd\":0,\"province\":\"广东\",\"grade\":\"点击查看详情\",\"heal\":18,\"nowConfirm\":8,\"syear\":0},{\"date\":\"05/17\",\"wzz_add\":\"0\",\"sdate\":\"\",\"city\":\"阜新\",\"dead\":0,\"mtime\":\"2022-05-17 11:34:01\",\"confirm\":10,\"province\":\"辽宁\",\"confirmAdd\":0,\"grade\":\"点击查看详情\",\"heal\":8,\"nowConfirm\":2,\"syear\":0}]\n";
        List<StatisGradeCityDetail> statisGradeCityDetails = JSON.parseArray(json, StatisGradeCityDetail.class);
        for (StatisGradeCityDetail sg : statisGradeCityDetails) {
            System.out.println(DateUtils.isSameDay(new Date(),sg.getMtime()));
        }
    }

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
            String d1 = diseaseh5Shelf.toString();

            //各地区总数和按省份分类
            Object areaTree = JSON.parseObject(d1.toString()).get("areaTree");
            Object chinaTotal = JSON.parseObject(d1.toString()).get("chinaTotal");
            Object chinaAdd = JSON.parseObject(d1.toString()).get("chinaAdd");


            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println(areaTree);
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println(chinaTotal);
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println(chinaAdd);
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");
            System.out.println("---");




//            //拿到全国情况
//            Object statisGradeCityDetail = JSON.parseObject(data1.toString()).get("statisGradeCityDetail");
//            //转换成string
//            String s = statisGradeCityDetail.toString();

//            //转换为list数组
//            List <StatisGradeCityDetail> list = JSON.parseArray(s,StatisGradeCityDetail.class);

            //筛选有效数据数组录入数据库
//            ArrayList<CityDetail> cityDetails = new ArrayList<>();
//            for (StatisGradeCityDetail  sg: list) {
//
//                //是否今天的数据 DateUtils.isSameDay(new Date(),sg.getMtime())
//                System.out.println(DateUtils.isSameDay(new Date(),sg.getMtime()));
//
//                if(!DateUtils.isSameDay(new Date(),sg.getMtime())){
//                    continue;
//                }
//
//
//                CityDetail cityDetail = new CityDetail();
//
//                cityDetail.setMtime(sg.getMtime());
//                cityDetail.setSyear(sg.getSyear()==0?2022: sg.getSyear());
//                cityDetail.setDate(sg.getDate());
//                cityDetail.setProvince(sg.getProvince());
//                cityDetail.setCity(sg.getCity());
//
//                cityDetail.setConfirmAdd(sg.getConfirmAdd());
//                cityDetail.setWzz_add(sg.getWzz_add());
//                cityDetail.setNowConfirm(sg.getNowConfirm());
//
//                cityDetail.setConfirm(sg.getConfirm());
//                cityDetail.setHeal(sg.getHeal());
//                cityDetail.setDead(sg.getDead());
//                cityDetails.add(cityDetail);
//
//
//                System.out.println(cityDetail);
//            }

//            //导入集合
//            sqlss(cityDetails);





        } catch (IOException e) {
            e.printStackTrace();
        }

    }












    //一次加入一个
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

    //一次加入多个
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
