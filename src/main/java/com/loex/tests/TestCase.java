package com.loex.tests;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loex.base.TestBase;
import com.loex.data.BibiBuyUsers;
import com.loex.db.Connectdb;
import com.loex.restclient.RestClient;
import com.loex.token.TokenInfo;
import com.loex.util.HttpUtils;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.rmi.runtime.Log;

import static com.jayway.restassured.RestAssured.given;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class TestCase extends TestBase{

//
    TestBase testBase;
    String host;
    String token;
    String bibibuyurl;
    String ispengzhuang;
    String userConfigProperties_Path = System.getProperty("user.dir") + "/src/main/java/com/loex/config/config.properties";
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    CloseableHttpResponse closeableHttpResponseGet;
    JSONArray jsonArray = null;
    private static Logger log = Logger.getLogger(WebApiTests.class.getClass());

    //    String gettoken;
//    private static Logger log = Logger.getLogger(org.testng.annotations.Test.class.getClass());

    /**传入txt路径和内容写入txt文件
     * @param
     * @param
     * @return 无
     */
//    public static void main(String[] args) throws IOException, InterruptedException {
//        ss();
//    }
    @Test
    public void tt() throws IOException, InterruptedException {
        //获取HOST和API
        HttpUtils hu = new HttpUtils();
//        host = hu.getProperties("HOST");
//        apiurl = host + url;

        //获取token
        token = hu.getProperties("TOKEN");
//        log.info("正确购买" + token);
        restClient = new RestClient();
        //准备请求头信息
        HashMap<String, String> headermap = new HashMap<String, String>();
//        headermap.put("Accept", "application/json, text/plain, */*"); //这个在postman中可以查询到
//        headermap.put("Accept-Encoding", "gzip, deflate, br");
//        headermap.put("Accept-Language", "zh-CN,zh;q=0.9");
//        headermap.put("Connection", "keep-alive");
        headermap.put("Content-Type", "application/json;charset=UTF-8");
//        headermap.put("exchange-client", "pc");
//        headermap.put("Content-Length", "45");
//        headermap.put("exchange-language", "zh_CN");
        headermap.put("exchange-token", TokenInfo.token);
//        headermap.put("Host", "webapi.loex.abc");
//        headermap.put("Origin", "https://www.loex.abc");
//        headermap.put("Referer", "https://www.loex.abc/trade");
//        headermap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");


        String url = "http://webapi.loex.abc/exchange-web-api/v1/coupon/amount";
//        String url = "http://webapi.loex.abc/exchange-web-api/v1/order/stop/list";
//        String param = "";
//        String param = "{ \"symbol\": \"lcpusdt\", \"orderId\": \"11570\"}";
        String param = "{\"symbol\": \"lcpusdt\",\"side\": \"BUY\", \"type\": \"1\", \"volume\": \"1\"}";
            closeableHttpResponse = restClient.post(url, param, headermap);
//            closeableHttpResponse = restClient.get(url+param, headermap);

        //每次获取新的token传递给请求头
        if(closeableHttpResponse.containsHeader("token")){
          String tokenNew=  closeableHttpResponse.getHeaders("token")[0].getValue();
          if(tokenNew!=null&&!tokenNew.equals("token")){
              TokenInfo.token=tokenNew;
          }
        }


        //断言响应json内容中name和job是不是期待结果
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"utf-8");
        JSONObject responseJson = JSON.parseObject(responseString);
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

//        String yl1 = yongliname.split("\\_")[1];
//        String yl2 = yongliname.split("\\_")[2];
//        if(yl1.equals("空参数")){
//            //空参数都返回404
//            Assert.assertEquals(statusCode, 404);
//        }else {
//            if (yl2.equals("code=0")) {
//                Assert.assertEquals(statusCode, 200);
//                String code = responseJson.getString("code");
//                Assert.assertEquals(code, yuqijieguo);
//            } else if (yl2.equals("code=200")) {
//
//
//            } else {
//                Assert.assertEquals(statusCode, 200);
//                Assert.assertEquals(responseString, yuqijieguo, "接口请求失败......." + "请求URL:" + url + "序号:" + xuhao + "接口名称:" + apiname + "用例标题:" + yongliname + "请求方法" + requestmtd + "请求参数" + param + "预期结果" + yuqijieguo + "实际结果" + responseString);
//            }
//        }
//        String buyTotalAmount = responseJson.getJSONObject("data").getString("buyTotalAmount");

        log.info(responseString);
//        log.info("---------");
//        log.info(responseJson);
        Thread.sleep(3000);
    }
    public void ss() throws IOException, InterruptedException {
//        testBase = new TestBase();
//        host = TestBase.readProperties(userConfigProperties_Path, "HOST");
//        String bibibuyurl = "http://webapi.loex.abc/exchange-web-api/v1/barrage/send";
        //获取token
//        token = TestBase.readProperties(userConfigProperties_Path, "Token");
//        log.info("正确购买" + token);
//        RestClient restClient = new RestClient();
        //准备请求头信息
//        HashMap<String, String> headermap = new HashMap<String, String>();
//        headermap.put("Accept", "application/json, text/plain, */*"); //这个在postman中可以查询到
//        headermap.put("Accept-Encoding", "gzip, deflate, br");
//        headermap.put("Accept-Language", "zh-CN,zh;q=0.9");
//        headermap.put("Connection", "keep-alive");
//        headermap.put("Content-Type", "application/json;charset=UTF-8");
//        headermap.put("exchange-client", "pc");
//        headermap.put("Content-Length", "45");
//        headermap.put("exchange-language", "zh_CN");
//        headermap.put("exchange-token", "fff13fc23b01c60934dad103e80edeb78f9fb83bc7ae4a4a9c6a428e6016525c");
//        headermap.put("Host", "webapi.loex.abc");
//        headermap.put("Origin", "https://www.loex.abc");
//        headermap.put("Referer", "https://www.loex.abc/trade");
//        headermap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");

//        String pram = "{symbol: \"secusdt\", content: \"eeee\", type: \"0\"}";
//        String sparm = JSONObject.toJSONString(pram);
//        CloseableHttpResponse closeableHttpResponse = restClient.post(bibibuyurl,sparm , headermap);
//        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"utf-8");
//        JSONObject responseJson = JSON.parseObject(responseString);

        //String getdata = jsonObject.getString("data");
//        JSONObject jsonObject1 = JSONObject.parseObject(getdata);
//        String gettoken = jsonObject1.getString("token");
//        log.info(responseString);
//        log.info(responseJson);

//        RestAssured.baseURI = "http://webapi.loex.abc";
//        RestAssured.basePath = "/exchange-web-api/v1/barrage/send";
//        RestAssured.basePath = "/exchange-web-api/order/create";

          //要改一下币对


//        for (int i = 0; i <1000 ; i++) {
//
//
//            String s = "天若赐我辉煌；我定比天猖狂，手拿菜刀砍电线,一路火花带闪电;软中华,硬玉溪;头发越短越牛逼"+i;
//            String  json= "{\"symbol\":\"seclcny\",\"content\":\""+s+"\",\"type\":\"0\"}";
////            String  json= "{\"type\": \"1\", \"side\": \"BUY\", \"price\": \"1\", \"volume\": \"1\", \"symbol\": \"secusdt\"}";
//
//            String a="{\"msg\":\"操作成功\",\"code\":\"0\"}";
//
//        Response response = given()
//                .contentType("application/json;charset=UTF-8")
//                .header("exchange-token","480cc2f4568503414832d1af07595990b04c1342c4df4e898d4db47fb2ad7397")      //要加上exchange-token
//                .body(json)
//                .when().log().all().post();
//            int responseCode = response.statusCode();
////            String responseContent = response.getBody().prettyPrint();
//        String responseContent = response.getBody().print();
////            System.out.println(responseCode);
//        System.out.println("------------------------------------------");
//            System.out.println(responseContent.replaceAll(" ",""));
//                  System.out.println(responseContent.equals(a));
//
//            Thread.sleep(1000);
//
//        }
//
//
//
//
//    }
//
//    @Test
//    public void aa() throws IOException, InterruptedException {
//        HttpUtils httpUtils = new HttpUtils();
//        testBase = new TestBase();
//        host = TestBase.readProperties(userConfigProperties_Path, "HOST");
//        bibibuyurl = host + "/v1/order/create";
//
//        //获取token
//        token = TestBase.readProperties(userConfigProperties_Path, "Token");
//
//        restClient = new RestClient();
//        //准备请求头信息
//        HashMap<String, String> headermap = new HashMap<String, String>();
////        headermap.put("Accept", "application/json, text/plain, */*"); //这个在postman中可以查询到
////        headermap.put("Accept-Encoding", "gzip, deflate, br");
////        headermap.put("Accept-Language", "zh-CN,zh;q=0.9");
////        headermap.put("Connection", "keep-alive");
//        headermap.put("Content-Type", "application/json;charset=UTF-8");
////        headermap.put("exchange-client", "pc");
////        headermap.put("Content-Length", "45");
////        headermap.put("exchange-language", "zh_CN");
//        headermap.put("exchange-token", "0f394c0ebed36947e5ca5c96896af4d713575a51bdce414498893759ba2e5892");
////        headermap.put("Host", "webapi.loex.abc");
////        headermap.put("Origin", "https://www.loex.abc");
////        headermap.put("Referer", "https://www.loex.abc/trade");
////        headermap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
//






//        String ethchukuaiapiurl = "https://explorer-web.api.btc.com/v1/eth/blocks?page=1&size=25";


        // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        //static final String DB_URL = "182.61.40.106";

        // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
        //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://182.61.40.106:3306/exchange_order?useSSL=false&serverTimezone=UTC";


        // 数据库的用户名与密码，需要根据自己的设置
//        String USER = "linjianwei";
//        String PASS = "aMXAFGSPpWybh1Q6";
//        Connection conn = null;
//        Statement stmt = null;
//        Statement stmt2 = null;

        //活动配置参数
//        int chushigaodu = 10155836;
//        double meiqiqukuai = 3.00;
//        int zongqishu = 6;
//        double leijiqishu = 2.00;
//        int meiqijianglibili = 10;
//
//        for (int i = 0; i <200 ; i++) {
//            //币币交易
//            String param = "{\"type\": \"1\", \"side\": \"BUY\", \"price\": \"1\", \"volume\": \"1\", \"symbol\": \"lcpusdt\"}";
////            String param = "{\"type\": \"2\", \"side\": \"BUY\", \"volume\": \"3\", \"symbol\": \"dskusdt\"}";
//            closeableHttpResponsePost = restClient.post(bibibuyurl, param, headermap);
//            String responseStringPost = EntityUtils.toString(closeableHttpResponsePost.getEntity(),"utf-8");
//            JSONObject responseJsonPost = JSON.parseObject(responseStringPost);
//            log.info("币币交易"+responseStringPost);
//            log.info("---------");
//            Thread.sleep(500);

            //调用接口获取当前出块高度和时间
//            closeableHttpResponseGet = restClient.get(ethchukuaiapiurl);
//            String responseStringGet = EntityUtils.toString(closeableHttpResponseGet.getEntity(),"utf-8");
//            JSONObject responseJsonGet = JSON.parseObject(responseStringGet);
//            String getblockheight = responseJsonGet.getJSONObject("data").getJSONArray("list").getJSONObject(0).getString("block_height");
//            String getcreatedts = responseJsonGet.getJSONObject("data").getJSONArray("list").getJSONObject(0).getString("created_ts");
//            log.info("ETH出块高度："+getblockheight+"--------------------"+"ETH出块时间："+httpUtils.getTime(getcreatedts));
//            String time = httpUtils.getTime(getcreatedts);

            //通过第一个出块的高度获取出块时间
//            String tongguogaoduchashijiandiyigeurl = "https://explorer-web.api.btc.com/v1/eth/blocks/"+chushigaodu;
//            closeableHttpResponseGet = restClient.get(tongguogaoduchashijiandiyigeurl);
//            String tongguogaoduchashijiandiyigeget = EntityUtils.toString(closeableHttpResponseGet.getEntity(),"utf-8");
//            JSONObject jsontongguogaoduchashijiandiyigeget = JSON.parseObject(tongguogaoduchashijiandiyigeget);
//            String tongguogaoduchashijiandiyigetime = jsontongguogaoduchashijiandiyigeget.getJSONObject("data").getString("created_ts");
//            String diyigequkuaitime = httpUtils.getTime(tongguogaoduchashijiandiyigetime);
//            String diyigequkuaitime = "2020-05-14 18:54:57";
            //判断活动是否过期
//            if (zongqishu*meiqiqukuai+chushigaodu<=Integer.parseInt(getblockheight)){
//                log.info("活动到期了");
//                break;
//            }
//
//
//
//            int suoyougaodu = Integer.parseInt(getblockheight)-chushigaodu;
//            double lun = suoyougaodu/meiqiqukuai;
//            int lunhou = (int)Math.ceil(lun);
////            int lunhou = 2;
//            int leijiqishuhou = (int)Math.floor(lunhou/leijiqishu);

            //获取每期最后一个区块时间
//            double meilunqukuaishu = lunhou*meiqiqukuai;
//            int meiqizuihuoqukuaigaodu = (int)(meilunqukuaishu+chushigaodu);
//            String tongguogaoduchashijianzuihouyigeurl = "https://explorer-web.api.btc.com/v1/eth/blocks/"+meiqizuihuoqukuaigaodu;
//            closeableHttpResponseGet = restClient.get(tongguogaoduchashijianzuihouyigeurl);
//            String tongguogaoduchashijianzuihouyigeget = EntityUtils.toString(closeableHttpResponseGet.getEntity(),"utf-8");
//            JSONObject jsontongguogaoduchashijianduihouyigeget = JSON.parseObject(tongguogaoduchashijianzuihouyigeget);
//            String strtongguogaoduchashijianzuihouyigetime = jsontongguogaoduchashijianduihouyigeget.getJSONObject("data").getString("created_ts");
//            int inttongguogaoduchashijianzuihouyigetime = Integer.parseInt(strtongguogaoduchashijianzuihouyigetime)+1;
//           String tongguogaoduchashijianzuihouyigejiayitime = String.valueOf(inttongguogaoduchashijianzuihouyigetime);
//            String meiqizuihouyigequkuaitime = httpUtils.getTime(tongguogaoduchashijianzuihouyigejiayitime);
//            String meiqizuihouyigequkuaitime = "2020-05-14 18:55:06";

            //获取每期第一个区块时间
//            int shangyilunhou = lunhou-1;
//            double meilunqukuaishumeiqidiyi = shangyilunhou*meiqiqukuai;
//            int meiqidiyiqukuaigaodu = (int)(meilunqukuaishumeiqidiyi+chushigaodu);
//            String tongguogaoduchashijiandiyiyigeurl = "https://explorer-web.api.btc.com/v1/eth/blocks/"+meiqidiyiqukuaigaodu;
//            closeableHttpResponseGet = restClient.get(tongguogaoduchashijiandiyiyigeurl);
//            String tongguogaoduchashijiandiyiget = EntityUtils.toString(closeableHttpResponseGet.getEntity(),"utf-8");
//            JSONObject jsontongguogaoduchashijiandiyiget = JSON.parseObject(tongguogaoduchashijiandiyiget);
//            String strtongguogaoduchashijiandiyitime = jsontongguogaoduchashijiandiyiget.getJSONObject("data").getString("created_ts");
//            int inttongguogaoduchashijiandiytime = Integer.parseInt(strtongguogaoduchashijiandiyitime);
//            String tongguogaoduchashijiandiyijiayitime = String.valueOf(inttongguogaoduchashijiandiytime);
//            String meiqidiyigequkuaitime = httpUtils.getTime(tongguogaoduchashijiandiyijiayitime);
////            String meiqidiyigequkuaitime = "2020-05-14 18:55:02";
//
//            log.info("已经出了"+suoyougaodu+"个区块");
//            log.info("当前在第"+lunhou+"期");
//            log.info("已经累积了"+leijiqishuhou+"期");
//
//
//            if (suoyougaodu%(leijiqishu*meiqiqukuai)==0){
//                log.info("累计周期到了，注意开奖了！！！");
//            }else {
//                int sss = leijiqishuhou+1;
//                log.info("等待第"+sss+"个累积周期吧！");
//            }



//            try{
//                // 注册 JDBC 驱动
//                Class.forName(JDBC_DRIVER);
//
//                // 打开链接
//                System.out.println("连接数据库...");
//                conn = DriverManager.getConnection(DB_URL,USER,PASS);
//
//                // 执行查询
//                System.out.println(" 实例化Statement对象...");
//                stmt = conn.createStatement();
//                stmt2 = conn.createStatement();
//               String sql1 = "SELECT * FROM ex_order_dskusdt where ctime LIKE"+"'"+"%"+time+"%"+"'";
//                String sql2 = "SELECT sum(buy_fee) as buyFees,sum(sell_fee) as sellFees FROM ex_trade_dskusdt where ctime BETWEEN "+"'"+diyigequkuaitime+"'"+" AND "+"'"+meiqizuihouyigequkuaitime+"'";
//                ResultSet rs2;
//                if (lunhou == 1){
//                    String sql2 = "SELECT sum(buy_fee) as buyFees,sum(sell_fee) as sellFees FROM ex_trade_dskusdt where ctime BETWEEN "+"'"+diyigequkuaitime+"'"+" AND "+"'"+meiqizuihouyigequkuaitime+"'";
//                    rs2 = stmt2.executeQuery(sql2);
//                }else {
//                    String sql2 = "SELECT sum(buy_fee) as buyFees,sum(sell_fee) as sellFees FROM ex_trade_dskusdt where ctime BETWEEN "+"'"+meiqidiyigequkuaitime+"'"+" AND "+"'"+meiqizuihouyigequkuaitime+"'";
//                    rs2 = stmt2.executeQuery(sql2);
//                }


//                ResultSet rs1 = stmt.executeQuery(sql1);
//                ResultSet rs2 = stmt2.executeQuery(sql2);





                // 展开结果集数据库
//                if(rs1.next()){
//                    // 通过字段检索
//                    //  int id  = rs.getInt("id");
//                    Timestamp ctime = rs1.getTimestamp("ctime");
////                    Long time = ctime.getTime()/1000;
//
//                    log.info("有");
////                    int bid_user_id = rs1.getInt("bid_user_id");
////                    int ask_user_id = rs1.getInt("ask_user_id");
////                    log.info("买方用户ID："+bid_user_id+"---------------------"+"卖方用户ID："+ask_user_id);
//
//                    int sum = 0;
//                    sum = sum+1;
//                    log.info("一共碰撞"+sum+"次");
//                    //把哪期是否碰撞到写到配置文件
////                    String userConfigProperties_Path=System.getProperty("user.dir")+ "/src/main/java/com/loex/config/config.properties";
////                    TestBase.writeProperties(userConfigProperties_Path,"Ispengzhuang","yes");
////                    log.info("碰撞到写入properties文件成功");
//
//                }else {
//                    log.info("没有");
////                    //把哪期是否碰撞到写到配置文件
////                    String userConfigProperties_Path=System.getProperty("user.dir")+ "/src/main/java/com/loex/config/config.properties";
////                    TestBase.writeProperties(userConfigProperties_Path,"Ispengzhuang","no");
////                    log.info("没有碰撞到写入properties文件成功");
//
//                    System.out.print("\n");
//                }

                // 展开结果集数据库
//                while(rs2.next()){
//                    // 通过字段检索
//                    //  int id  = rs.getInt("id");
//
////                    Long time = ctime.getTime()/1000;
//                    testBase = new TestBase();
//                    ispengzhuang = TestBase.readProperties(userConfigProperties_Path, "Ispengzhuang");
//
//                    if(ispengzhuang.equals("yes")) {
//                        int meiqizongqukuai = (int) (lunhou * meiqiqukuai);
//                        int meiqizuihuoqukuaigaoduxiayige = chushigaodu + meiqizongqukuai + 1;
//                        String stringmeiqizuihuoqukuaigaoduxiayige = String.valueOf(meiqizuihuoqukuaigaoduxiayige);
//                        if (getblockheight == stringmeiqizuihuoqukuaigaoduxiayige) {
//                            BigDecimal buyFees = rs2.getBigDecimal("buyFees");
//                            BigDecimal sellFees = rs2.getBigDecimal("sellFees");
//                            log.info("买的" + buyFees);
//                            log.info("卖的" + sellFees);
//                            double v = Double.parseDouble(String.valueOf(buyFees));
//                            double s = Double.parseDouble(String.valueOf(sellFees));
//                            double mairujiangli = v/meiqijianglibili;
//                            double maichujiangli = s/meiqijianglibili;
//                            log.info("买入的奖励"+mairujiangli);
//                            log.info("卖出的奖励"+maichujiangli);
//                            break;
//                        }
//                    }
//                    System.out.print("\n");
//                }
                // 完成后关闭
//                rs2.close();
//                rs1.close();
//                stmt.close();
//                conn.close();
//            }catch(SQLException se){
//                // 处理 JDBC 错误
//                se.printStackTrace();
//            }catch(Exception e){
//                // 处理 Class.forName 错误
//                e.printStackTrace();
//            }finally{
//                // 关闭资源
//                try{
//                    if(stmt!=null) stmt.close();
//                }catch(SQLException se2){
//                }// 什么都不做
//                try{
//                    if(conn!=null) conn.close();
//                }catch(SQLException se){
//                    se.printStackTrace();
//                }
//            }
//            System.out.println("Goodbye!");
//            Thread.sleep(500);
//
//        }


    }

//    @Test
//    public void bb() throws IOException, InterruptedException {
//        // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
//        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//        //static final String DB_URL = "182.61.40.106";
//
//        // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
//        //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//        String DB_URL = "jdbc:mysql://182.61.40.106:3306/exchange_order?useSSL=false&serverTimezone=UTC";
//
//
//        // 数据库的用户名与密码，需要根据自己的设置
//        String USER = "linjianwei";
//        String PASS = "aMXAFGSPpWybh1Q6";
//        Connection conn = null;
//        Statement stmt = null;
//        Statement stmt2 = null;
//        HttpUtils httpUtils = new HttpUtils();
//        testBase = new TestBase();
//        host = TestBase.readProperties(userConfigProperties_Path, "HOST");
//        bibibuyurl = host + "/order/create";
//
//        //获取token
//        token = TestBase.readProperties(userConfigProperties_Path, "Token");
//
//        for (int i = 0; i <500 ; i++) {
//        String str[] = {"4f83cc2101f7384529e0d28fc6936ce22cfa5311e7d54c2bb22c026b7a3a37a1","10c6544a5579e4c9f794e656f77d2e62fb6cfd9e87834c2398c7793d6ddddc7e","88c4c6522a98dd20603f46ad40bfaad4e13f4f388a204d2388b30eb97d7374f3","7e3138927325dba77b21d49383296c9debe0c314409941d1846d46fb16c651f6"};
//            int t = (int) Math.floor(4*Math.random()) ;
////            System.out.println(str[t]);
//
//        restClient = new RestClient();
//        //准备请求头信息
//        HashMap<String, String> headermap = new HashMap<String, String>();
////        headermap.put("Accept", "application/json, text/plain, */*"); //这个在postman中可以查询到
////        headermap.put("Accept-Encoding", "gzip, deflate, br");
////        headermap.put("Accept-Language", "zh-CN,zh;q=0.9");
////        headermap.put("Connection", "keep-alive");
//        headermap.put("Content-Type", "application/json;charset=UTF-8");
////        headermap.put("exchange-client", "pc");
////        headermap.put("Content-Length", "45");
////        headermap.put("exchange-language", "zh_CN");
//        headermap.put("exchange-token", str[t]);
////        headermap.put("Host", "webapi.loex.abc");
////        headermap.put("Origin", "https://www.loex.abc");
////        headermap.put("Referer", "https://www.loex.abc/trade");
////        headermap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
//
//
//
//
//
//
//
//        String ethchukuaiapiurl = "https://explorer-web.api.btc.com/v1/eth/blocks?page=1&size=25";
//
//
//
//
//        //活动配置参数
//            int chushigaodu = 10146492;
//            double meiqiqukuai = 20.00;
//            int zongqishu = 500;
//            double leijiqishu = 5.00;
//            int meiqijianglibili = 10;
//
//
//            //币币交易
//
//            int s = t+1;
//            String param = "{\"type\": \"1\", \"side\": \"BUY\", \"price\": \"1\", \"volume\": \""+s+"\", \"symbol\": \"dskusdt\"}";
//            closeableHttpResponsePost = restClient.post(bibibuyurl, param, headermap);
//            String responseStringPost = EntityUtils.toString(closeableHttpResponsePost.getEntity(),"utf-8");
//            JSONObject responseJsonPost = JSON.parseObject(responseStringPost);
//            log.info("币币交易"+responseStringPost);
//            log.info("---------");
//
//            //调用接口获取当前出块高度和时间
//            closeableHttpResponseGet = restClient.get(ethchukuaiapiurl);
//            String responseStringGet = EntityUtils.toString(closeableHttpResponseGet.getEntity(),"utf-8");
//            JSONObject responseJsonGet = JSON.parseObject(responseStringGet);
//            String getblockheight = responseJsonGet.getJSONObject("data").getJSONArray("list").getJSONObject(0).getString("block_height");
//            String getcreatedts = responseJsonGet.getJSONObject("data").getJSONArray("list").getJSONObject(0).getString("created_ts");
//            log.info("ETH出块高度："+getblockheight+"--------------------"+"ETH出块时间："+httpUtils.getTime(getcreatedts));
//            String time = httpUtils.getTime(getcreatedts);
//
//            //通过第一个出块的高度获取出块时间
////            String tongguogaoduchashijiandiyigeurl = "https://explorer-web.api.btc.com/v1/eth/blocks/"+chushigaodu;
////            closeableHttpResponseGet = restClient.get(tongguogaoduchashijiandiyigeurl);
////            String tongguogaoduchashijiandiyigeget = EntityUtils.toString(closeableHttpResponseGet.getEntity(),"utf-8");
////            JSONObject jsontongguogaoduchashijiandiyigeget = JSON.parseObject(tongguogaoduchashijiandiyigeget);
////            String tongguogaoduchashijiandiyigetime = jsontongguogaoduchashijiandiyigeget.getJSONObject("data").getString("created_ts");
////            String diyigequkuaitime = httpUtils.getTime(tongguogaoduchashijiandiyigetime);
////            String diyigequkuaitime = "2020-05-14 18:54:57";
//            //判断活动是否过期
//            if (zongqishu*meiqiqukuai+chushigaodu<=Integer.parseInt(getblockheight)){
//                log.info("活动到期了");
//                break;
//            }
//
//
//
//            int suoyougaodu = Integer.parseInt(getblockheight)-chushigaodu;
//            double lun = suoyougaodu/meiqiqukuai;
//            int lunhou = (int)Math.ceil(lun);
////            int lunhou = 2;
//            int leijiqishuhou = (int)Math.floor(lunhou/leijiqishu);
//
//            //获取每期最后一个区块时间
////            double meilunqukuaishu = lunhou*meiqiqukuai;
////            int meiqizuihuoqukuaigaodu = (int)(meilunqukuaishu+chushigaodu);
////            String tongguogaoduchashijianzuihouyigeurl = "https://explorer-web.api.btc.com/v1/eth/blocks/"+meiqizuihuoqukuaigaodu;
////            closeableHttpResponseGet = restClient.get(tongguogaoduchashijianzuihouyigeurl);
////            String tongguogaoduchashijianzuihouyigeget = EntityUtils.toString(closeableHttpResponseGet.getEntity(),"utf-8");
////            JSONObject jsontongguogaoduchashijianduihouyigeget = JSON.parseObject(tongguogaoduchashijianzuihouyigeget);
////            String strtongguogaoduchashijianzuihouyigetime = jsontongguogaoduchashijianduihouyigeget.getJSONObject("data").getString("created_ts");
////            int inttongguogaoduchashijianzuihouyigetime = Integer.parseInt(strtongguogaoduchashijianzuihouyigetime)+1;
////           String tongguogaoduchashijianzuihouyigejiayitime = String.valueOf(inttongguogaoduchashijianzuihouyigetime);
////            String meiqizuihouyigequkuaitime = httpUtils.getTime(tongguogaoduchashijianzuihouyigejiayitime);
////            String meiqizuihouyigequkuaitime = "2020-05-14 18:55:06";
//
//            //获取每期第一个区块时间
//            int shangyilunhou = lunhou-1;
//            double meilunqukuaishumeiqidiyi = shangyilunhou*meiqiqukuai;
//            int meiqidiyiqukuaigaodu = (int)(meilunqukuaishumeiqidiyi+chushigaodu);
//            String tongguogaoduchashijiandiyiyigeurl = "https://explorer-web.api.btc.com/v1/eth/blocks/"+meiqidiyiqukuaigaodu;
//            closeableHttpResponseGet = restClient.get(tongguogaoduchashijiandiyiyigeurl);
//            String tongguogaoduchashijiandiyiget = EntityUtils.toString(closeableHttpResponseGet.getEntity(),"utf-8");
//            JSONObject jsontongguogaoduchashijiandiyiget = JSON.parseObject(tongguogaoduchashijiandiyiget);
//            String strtongguogaoduchashijiandiyitime = jsontongguogaoduchashijiandiyiget.getJSONObject("data").getString("created_ts");
//            int inttongguogaoduchashijiandiytime = Integer.parseInt(strtongguogaoduchashijiandiyitime);
//            String tongguogaoduchashijiandiyijiayitime = String.valueOf(inttongguogaoduchashijiandiytime);
//            String meiqidiyigequkuaitime = httpUtils.getTime(tongguogaoduchashijiandiyijiayitime);
////            String meiqidiyigequkuaitime = "2020-05-14 18:55:02";
//
//            log.info("已经出了"+suoyougaodu+"个区块");
//            log.info("当前在第"+lunhou+"期");
//            log.info("已经累积了"+leijiqishuhou+"期");
//            log.info("当前是"+str[t]+"在交易");
//
//
//            if (suoyougaodu%(leijiqishu*meiqiqukuai)==0){
//                log.info("累计周期到了，注意开奖了！！！");
//            }else {
//                int sss = leijiqishuhou+1;
//                log.info("等待第"+sss+"个累积周期吧！");
//            }
//
//
//
//            try{
//                // 注册 JDBC 驱动
//                Class.forName(JDBC_DRIVER);
//
//                // 打开链接
//                System.out.println("连接数据库...");
//                conn = DriverManager.getConnection(DB_URL,USER,PASS);
//
//                // 执行查询
//                System.out.println(" 实例化Statement对象...");
//                stmt = conn.createStatement();
////                stmt2 = conn.createStatement();
//                String sql1 = "SELECT * FROM ex_order_dskusdt where ctime LIKE"+"'"+"%"+time+"%"+"'";
////                String sql2 = "SELECT sum(buy_fee) as buyFees,sum(sell_fee) as sellFees FROM ex_trade_dskusdt where ctime BETWEEN "+"'"+diyigequkuaitime+"'"+" AND "+"'"+meiqizuihouyigequkuaitime+"'";
////                ResultSet rs2;
////                if (lunhou == 1){
////                    String sql2 = "SELECT sum(buy_fee) as buyFees,sum(sell_fee) as sellFees FROM ex_trade_dskusdt where ctime BETWEEN "+"'"+diyigequkuaitime+"'"+" AND "+"'"+meiqizuihouyigequkuaitime+"'";
////                    rs2 = stmt2.executeQuery(sql2);
////                }else {
////                    String sql2 = "SELECT sum(buy_fee) as buyFees,sum(sell_fee) as sellFees FROM ex_trade_dskusdt where ctime BETWEEN "+"'"+meiqidiyigequkuaitime+"'"+" AND "+"'"+meiqizuihouyigequkuaitime+"'";
////                    rs2 = stmt2.executeQuery(sql2);
////                }
//
//
//                ResultSet rs1 = stmt.executeQuery(sql1);
////                ResultSet rs2 = stmt2.executeQuery(sql2);
//
//
//
//
//
//                // 展开结果集数据库
//                if(rs1.next()){
//                    // 通过字段检索
//                    //  int id  = rs.getInt("id");
//                    Timestamp ctime = rs1.getTimestamp("ctime");
////                    Long time = ctime.getTime()/1000;
//
//                    log.info("有");
////                    int bid_user_id = rs1.getInt("bid_user_id");
////                    int ask_user_id = rs1.getInt("ask_user_id");
////                    log.info("买方用户ID："+bid_user_id+"---------------------"+"卖方用户ID："+ask_user_id);
//
//                    int sum = 0;
//                    sum = sum+1;
//                    log.info("一共碰撞"+sum+"次");
//                    //把哪期是否碰撞到写到配置文件
////                    String userConfigProperties_Path=System.getProperty("user.dir")+ "/src/main/java/com/loex/config/config.properties";
////                    TestBase.writeProperties(userConfigProperties_Path,"Ispengzhuang","yes");
////                    log.info("碰撞到写入properties文件成功");
//
//                }else {
//                    log.info("没有");
////                    //把哪期是否碰撞到写到配置文件
////                    String userConfigProperties_Path=System.getProperty("user.dir")+ "/src/main/java/com/loex/config/config.properties";
////                    TestBase.writeProperties(userConfigProperties_Path,"Ispengzhuang","no");
////                    log.info("没有碰撞到写入properties文件成功");
//
//                    System.out.print("\n");
//                }
//
//                // 展开结果集数据库
////                while(rs2.next()){
////                    // 通过字段检索
////                    //  int id  = rs.getInt("id");
////
//////                    Long time = ctime.getTime()/1000;
////                    testBase = new TestBase();
////                    ispengzhuang = TestBase.readProperties(userConfigProperties_Path, "Ispengzhuang");
////
////                    if(ispengzhuang.equals("yes")) {
////                        int meiqizongqukuai = (int) (lunhou * meiqiqukuai);
////                        int meiqizuihuoqukuaigaoduxiayige = chushigaodu + meiqizongqukuai + 1;
////                        String stringmeiqizuihuoqukuaigaoduxiayige = String.valueOf(meiqizuihuoqukuaigaoduxiayige);
////                        if (getblockheight == stringmeiqizuihuoqukuaigaoduxiayige) {
////                            BigDecimal buyFees = rs2.getBigDecimal("buyFees");
////                            BigDecimal sellFees = rs2.getBigDecimal("sellFees");
////                            log.info("买的" + buyFees);
////                            log.info("卖的" + sellFees);
////                            double v = Double.parseDouble(String.valueOf(buyFees));
////                            double s = Double.parseDouble(String.valueOf(sellFees));
////                            double mairujiangli = v/meiqijianglibili;
////                            double maichujiangli = s/meiqijianglibili;
////                            log.info("买入的奖励"+mairujiangli);
////                            log.info("卖出的奖励"+maichujiangli);
////                            break;
////                        }
////                    }
////                    System.out.print("\n");
////                }
//                // 完成后关闭
////                rs2.close();
//                rs1.close();
//                stmt.close();
//                conn.close();
//            }catch(SQLException se){
//                // 处理 JDBC 错误
//                se.printStackTrace();
//            }catch(Exception e){
//                // 处理 Class.forName 错误
//                e.printStackTrace();
//            }finally{
//                // 关闭资源
//                try{
//                    if(stmt!=null) stmt.close();
//                }catch(SQLException se2){
//                }// 什么都不做
//                try{
//                    if(conn!=null) conn.close();
//                }catch(SQLException se){
//                    se.printStackTrace();
//                }
//            }
//            System.out.println("Goodbye!");
//            Thread.sleep(500);
//
//        }
//
//    }

//    @Test
//    public void cc(){
//        String str[] = {"dd","sfsd","gg"};
//        for (int i = 0; i < 10; i++) {
//            int t = (int) Math.floor(3*Math.random()) ;
//            System.out.println(str[t]);
//        }
//
//
    }

//}
