package com.loex.tests;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.loex.base.TestBase;
import com.loex.data.BibiBuyUsers;
import com.loex.data.LoginUser;
import com.loex.data.TokenUser;
import com.loex.restclient.RestClient;
import com.loex.token.TokenInfo;
import com.loex.util.HttpUtils;
import jdk.nashorn.internal.ir.LiteralNode;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jayway.restassured.RestAssured.given;
//import com.qa.base.TestBase;
//import com.qa.data.Users;
//import com.qa.restclient.RestClient;
//import com.qa.util.TestUtil;

public class WebApiTests extends TestBase {
    HashMap<String, String> map = new HashMap<String, String>();
    TestBase testBase;
    String host;
    String token;
    String url;
    String userConfigProperties_Path = System.getProperty("user.dir") + "/src/main/java/com/loex/config/config.properties";
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    JSONArray jsonArray = null;
    //    String gettoken;
    private static Logger log = Logger.getLogger(WebApiTests.class.getClass());

    /**
     * 正确买币
     *
     * @param
     */
    @DataProvider(name = "num")
    public Object[][] Numbers() throws BiffException, IOException {
        ExcelData e = new ExcelData("testdata", "calculator");
        return e.getExcelData();
    }

    @Test(dataProvider = "num")
    public void apiTest(HashMap<String, String> data) throws ClientProtocolException, IOException, InterruptedException {
        String url = data.get("请求URL");
        String xuhao = data.get("序号");
        String apiname = data.get("接口名称");
        String yongliname = data.get("用例标题");
        String requestmtd = data.get("请求方法");
        String param = data.get("请求参数");
        String yuqijieguo = data.get("预期结果");
        //获取HOST和API
        HttpUtils hu = new HttpUtils();
//        host = hu.getProperties("HOST");
//        apiurl = host + url;

        //获取token
        token = hu.getProperties("TOKEN");
//        log.info("正确购买" + token);
        restClient = new RestClient();

        String yl0 = yongliname.split("\\_")[0];
        String yl1 = yongliname.split("\\_")[1];
        String yl2 = yongliname.split("\\_")[2];

        HashMap<String, String> headermap = new HashMap<String, String>();
        //准备请求头信息
        //判断是否登录,未登录
        if(yl1.equals("未登录")) {

//        headermap.put("Accept", "application/json, text/plain, */*"); //这个在postman中可以查询到
//        headermap.put("Accept-Encoding", "gzip, deflate, br");
//        headermap.put("Accept-Language", "zh-CN,zh;q=0.9");
//        headermap.put("Connection", "keep-alive");
            headermap.put("Content-Type", "application/json;charset=UTF-8");
//        headermap.put("exchange-client", "pc");
//        headermap.put("Content-Length", "45");
//        headermap.put("exchange-language", "zh_CN");
            headermap.put("exchange-token", "");
//        headermap.put("Host", "webapi.loex.abc");
//        headermap.put("Origin", "https://www.loex.abc");
//        headermap.put("Referer", "https://www.loex.abc/trade");
//        headermap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");

            if (requestmtd.equals("post")) {
                closeableHttpResponse = restClient.post(url, param, headermap);
            } else {
                closeableHttpResponse = restClient.get(url + param, headermap);
            }



//            if(closeableHttpResponse.containsHeader("token")){
//                Header[] ss = closeableHttpResponse.getHeaders("token");
//                String tokenNew=  closeableHttpResponse.getHeaders("token")[0].getValue();
//                if(tokenNew!=null&&!tokenNew.equals("token")){
//                    TokenInfo.token=tokenNew;
//                    log.info("jjjjj");
//                    log.info("hhhhh"+tokenNew);
//                }
//            }

            //断言响应json内容中name和job是不是期待结果
            String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "utf-8");
            JSONObject responseJson = JSON.parseObject(responseString);
            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

            //每次获取新的token传递给请求头
            Header[] allHeaders = closeableHttpResponse.getAllHeaders();
            if(allHeaders != null && allHeaders.length > 0){
                for(Header header : allHeaders){
                    if(header.getName().equals("token")){
                        TokenInfo.token = header.getValue();
                        log.info(header.getValue());
                    }
                }
            }


            if (yl1.equals("空参数")) {
                //空参数都返回404
//                Assert.assertEquals(statusCode, 404);
                Assert.assertEquals("非0", yuqijieguo);
            } else {
                if (yl2.equals("返回code")) {
                    String code = responseJson.getString("code");
                    if (code.equals("0")) {
                        Assert.assertEquals(code, yuqijieguo);
                    } else {
                        Assert.assertEquals("非0", yuqijieguo);
                    }
                } else {
//                    Assert.assertEquals(statusCode, 200);
                    Assert.assertEquals(responseString, yuqijieguo, "接口请求失败......." + "请求URL:" + url + "序号:" + xuhao + "接口名称:" + apiname + "用例标题:" + yongliname + "请求方法" + requestmtd + "请求参数" + param + "预期结果" + yuqijieguo + "实际结果" + responseString);
                }
            }



//        String buyTotalAmount = responseJson.getJSONObject("data").getString("buyTotalAmount");

            log.info(responseString);
//        log.info("---------");
//        log.info(responseJson);

        }else {
            //登录
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

            if(yl0.equals("正确创建订单")) {
                if (requestmtd.equals("post")) {
                    closeableHttpResponse = restClient.post(url, param, headermap);
                } else {
                    closeableHttpResponse = restClient.get(url + param, headermap);
                }

                //每次获取新的token传递给请求头
//                if(closeableHttpResponse.containsHeader("token")){
//                    String tokenNew=  closeableHttpResponse.getHeaders("token")[0].getValue();
//                    if(tokenNew!=null&&!tokenNew.equals("token")){
//                        TokenInfo.token=tokenNew;
//                        log.info(tokenNew);
//                    }
//                }

                //断言响应json内容中name和job是不是期待结果
                String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "utf-8");
                JSONObject responseJson = JSON.parseObject(responseString);
                int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

                //每次获取新的token传递给请求头
                Header[] allHeaders = closeableHttpResponse.getAllHeaders();
                if(allHeaders != null && allHeaders.length > 0){
                    for(Header header : allHeaders){
                        if(header.getName().equals("token")){
                            TokenInfo.token = header.getValue();
                            log.info(header.getValue());
                        }
                    }
                }

                //把订单id写入properties文件
                String wrdata = responseJson.getString("data");
                hu.wrProperties("ORDERID",wrdata);

                if (yl1.equals("空参数")) {
                    //空参数都返回404
//                    Assert.assertEquals(statusCode, 404);
                    Assert.assertEquals("非0", yuqijieguo);
                } else {
                    if (yl2.equals("返回code")) {
//                        Assert.assertEquals(statusCode, 200);
                        String code = responseJson.getString("code");
                        if(code.equals("0")) {
                            Assert.assertEquals(code, yuqijieguo);
                        }else {
                            Assert.assertEquals("非0", yuqijieguo);
                        }
                    }else {
//                        Assert.assertEquals(statusCode, 200);
                        Assert.assertEquals(responseString, yuqijieguo, "接口请求失败......." + "请求URL:" + url + "序号:" + xuhao + "接口名称:" + apiname + "用例标题:" + yongliname + "请求方法" + requestmtd + "请求参数" + param + "预期结果" + yuqijieguo + "实际结果" + responseString);
                    }
                }


//        String buyTotalAmount = responseJson.getJSONObject("data").getString("buyTotalAmount");

                log.info(responseString);
//        log.info("---------");
//        log.info(responseJson);
            }else if (yl0.equals("正确取消订单")){
                if (requestmtd.equals("post")) {
                    //先获取上一个订单创建的ID
                    String getdata = hu.getProperties("ORDERID");
                    String param1 = "{\"symbol\": \"lcpusdt\", \"orderId\":\""+getdata+"\"}";
//                                    "{\"symbol\":\"seclcny\",\"content\":\""+s+"\",\"type\":\"0\"}";
                    closeableHttpResponse = restClient.post(url, param1, headermap);
                } else {
                    String getdata = hu.getProperties("ORDERID");
                    String param1 = "{\"symbol\": \"lcpusdt\", \"orderId\":\""+getdata+"\"}";
                    closeableHttpResponse = restClient.get(url + param1, headermap);
                }

                //每次获取新的token传递给请求头
//                if(closeableHttpResponse.containsHeader("token")){
//                    String tokenNew=  closeableHttpResponse.getHeaders("token")[0].getValue();
//                    if(tokenNew!=null&&!tokenNew.equals("token")){
//                        TokenInfo.token=tokenNew;
//                        log.info(tokenNew);
//                    }
//                }

                //断言响应json内容中name和job是不是期待结果
                String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "utf-8");
                JSONObject responseJson = JSON.parseObject(responseString);
                int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

                //每次获取新的token传递给请求头
                Header[] allHeaders = closeableHttpResponse.getAllHeaders();
                if(allHeaders != null && allHeaders.length > 0){
                    for(Header header : allHeaders){
                        if(header.getName().equals("token")){
                            TokenInfo.token = header.getValue();
                            log.info(header.getValue());
                        }
                    }
                }

                if (yl1.equals("空参数")) {
                    //空参数都返回404
//                    Assert.assertEquals(statusCode, 404);
                    Assert.assertEquals("非0", yuqijieguo);
                } else {
                    if (yl2.equals("返回code")) {
//                        Assert.assertEquals(statusCode, 200);
                        String code = responseJson.getString("code");
                        if (code.equals("0")) {
                            Assert.assertEquals(code, yuqijieguo);
                        } else {
                            Assert.assertEquals("非0", yuqijieguo);
                        }
                    } else {
//                        Assert.assertEquals(statusCode, 200);
                        Assert.assertEquals(responseString, yuqijieguo, "接口请求失败......." + "请求URL:" + url + "序号:" + xuhao + "接口名称:" + apiname + "用例标题:" + yongliname + "请求方法" + requestmtd + "请求参数" + param + "预期结果" + yuqijieguo + "实际结果" + responseString);
                    }
                }


//        String buyTotalAmount = responseJson.getJSONObject("data").getString("buyTotalAmount");

                log.info(responseString);
//        log.info("---------");
//        log.info(responseJson);

            }else {
                if (requestmtd.equals("post")) {
                    closeableHttpResponse = restClient.post(url, param, headermap);
                } else {
                    closeableHttpResponse = restClient.get(url + param, headermap);
                }

                //每次获取新的token传递给请求头
//                if(closeableHttpResponse.containsHeader("token")){
//                    String tokenNew=  closeableHttpResponse.getHeaders("token")[0].getValue();
//                    if(tokenNew!=null&&!tokenNew.equals("token")){
//                        TokenInfo.token=tokenNew;
//                        log.info(tokenNew);
//                    }
//                }

                //断言响应json内容中name和job是不是期待结果
                String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "utf-8");
                JSONObject responseJson = JSON.parseObject(responseString);
                int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

                //每次获取新的token传递给请求头
                Header[] allHeaders = closeableHttpResponse.getAllHeaders();
                if(allHeaders != null && allHeaders.length > 0){
                    for(Header header : allHeaders){
                        if(header.getName().equals("token")){
                            TokenInfo.token = header.getValue();
                            log.info(header.getValue());
                        }
                    }
                }


                if (yl1.equals("空参数")) {
                    //空参数都返回404
//                    Assert.assertEquals(statusCode, 404);
                    Assert.assertEquals("非0", yuqijieguo);
                } else {
                    if (yl2.equals("返回code")) {
//                        Assert.assertEquals(statusCode, 200);
                        String code = responseJson.getString("code");
                        if (code.equals("0")) {
                            Assert.assertEquals(code, yuqijieguo);
                        } else {
                            Assert.assertEquals("非0", yuqijieguo);
                        }
                    } else {
//                        Assert.assertEquals(statusCode, 200);
                        Assert.assertEquals(responseString, yuqijieguo, "接口请求失败......." + "请求URL:" + url + "序号:" + xuhao + "接口名称:" + apiname + "用例标题:" + yongliname + "请求方法" + requestmtd + "请求参数" + param + "预期结果" + yuqijieguo + "实际结果" + responseString);
                    }
                }



//        String buyTotalAmount = responseJson.getJSONObject("data").getString("buyTotalAmount");

                log.info(responseString);
//        log.info("---------");
//        log.info(responseJson);
            }



        }
        Thread.sleep(2000);
    }


}
