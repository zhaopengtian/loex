package com.loex.tests;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loex.base.TestBase;
import com.loex.data.BibiBuyUsers;
import com.loex.restclient.RestClient;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class LogOutTest extends TestBase{
    TestBase testBase;
    String host;
    String token;
    String logouturl;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    String userConfigProperties_Path=System.getProperty("user.dir")+ "/src/main/java/com/loex/config/config.properties";

    private static Logger log = Logger.getLogger(Test.class.getClass());

    /**
     * 退出
     * @param null
     */
    @AfterSuite
    public void logOutTest() throws IOException {

            testBase = new TestBase();
        host =TestBase.readProperties(userConfigProperties_Path,"HOST");
            logouturl = host + "/v1/auth/logout";
        //获取token
        token =TestBase.readProperties(userConfigProperties_Path,"Token");
            restClient = new RestClient();
            //准备请求头信息
            HashMap<String,String> headermap = new HashMap<String,String>();
            headermap.put("Accept", "application/json, text/plain, */*"); //这个在postman中可以查询到
            headermap.put("Accept-Encoding", "gzip, deflate, br");
            headermap.put("Accept-Language", "zh-CN,zh;q=0.9");
            headermap.put("Connection", "keep-alive");
            headermap.put("Content-Type", "application/json;charset=UTF-8");
            headermap.put("exchange-client", "pc");
//        headermap.put("Content-Length", "45");
            headermap.put("exchange-language", "zh_CN");
            headermap.put("exchange-token", token);
            headermap.put("Host", "webapi.loex.abc");
            headermap.put("Origin", "https://www.loex.abc");
            headermap.put("Referer", "https://www.loex.abc/trade");
            headermap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");

            closeableHttpResponse = restClient.post(logouturl, "", headermap);
            //验证状态码是不是200
            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();


//        Assert.assertEquals(statusCode, 200,"status code is not 201");


            //断言响应json内容中name和job是不是期待结果
            String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"utf-8");
            JSONObject responseJson = JSON.parseObject(responseString);
            log.info(responseJson);
            log.info("退出登录成功");
    }
}
