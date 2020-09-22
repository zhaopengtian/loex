package com.loex.tests;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loex.base.TestBase;
import com.loex.data.LoginUser;
import com.loex.data.TokenUser;
import com.loex.restclient.RestClient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class LoginTest extends TestBase {
    TestBase testBase;
    String host;
    String userConfigProperties_Path=System.getProperty("user.dir")+ "/src/main/java/com/loex/config/config.properties";
    String loginUrl;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

//    private static Log log = LogFactory.getLog(Test.class.getClass());
    private static Logger log = Logger.getLogger(Test.class.getClass());

    /**
     * 登录
     * @param null
     */
    @BeforeSuite
    public void loginTest() throws IOException {
        testBase = new TestBase();
        host =TestBase.readProperties(userConfigProperties_Path,"HOST");
        loginUrl = host + "/v1/auth/login";
    //登录，获取token
    restClient = new RestClient();
    //准备请求头信息
    HashMap<String,String> headermap = new HashMap<String,String>();
        headermap.put("Accept", "application/json, text/plain, */*"); //这个在postman中可以查询到
        headermap.put("Accept-Encoding", "gzip, deflate, br");
        headermap.put("Accept-Language", "zh-CN,zh;q=0.9");
        headermap.put("Proxy-Connection", "keep-alive");
        headermap.put("Content-Type", "application/json;charset=UTF-8");
        headermap.put("exchange-client", "pc");
        headermap.put("Cache-Control", "no-cache");
        headermap.put("exchange-language", "zh_CN");
        headermap.put("exchange-token", "");
        headermap.put("Host", "webapi.loex.abc");
        headermap.put("Origin", "https://www.loex.abc");
        headermap.put("Pragma", "no-cache");
        headermap.put("Referer", "https://www.loex.abc/login");
        headermap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");

    //对象转换成Json字符串
    LoginUser user = new LoginUser("14000000002","a123456789");
    String userJsonString = JSON.toJSONString(user);

    closeableHttpResponse = restClient.post(loginUrl, userJsonString, headermap);

    //验证状态码是不是200
    int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        log.info("登录成功");

//        Assert.assertEquals(statusCode, 200,"status code is not 201");


    //断言响应json内容中name和job是不是期待结果
    String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"utf-8");
    JSONObject responseJson = JSON.parseObject(responseString);


    //获取token
    JSONObject jsonObject = JSONObject.parseObject(responseString);
    String getdata = jsonObject.getString("data");
    JSONObject jsonObject1 = JSONObject.parseObject(getdata);
    String gettoken = jsonObject1.getString("token");
    if (gettoken==null) {
        log.info("token为null");
    }else if(gettoken==""){
        log.info("token为空");
    }else {
        log.info("成功获取token--" + gettoken);
    }
    //把token写入properties文件
        String userConfigProperties_Path=System.getProperty("user.dir")+ "/src/main/java/com/loex/config/config.properties";
        TestBase.writeProperties(userConfigProperties_Path,"Token",gettoken);
        log.info("token写入properties文件成功");

    //获取data中是集合的字段
    //取里面的id
//        List<Test> list = JSONObject.parseArray(r,Test.class);
//        int id2 = list.get(0).getId();
//        System.out.println(id2);

 }

}
