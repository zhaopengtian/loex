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
import com.loex.util.HttpUtils;
import jdk.nashorn.internal.ir.LiteralNode;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.testng.Assert;
import org.testng.annotations.*;
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

public class PostApiTest extends TestBase {
    HashMap<String, String> map = new HashMap<String, String>();


    TestBase testBase;
    String host;
    String token;
    String bibibuyurl;
    String userConfigProperties_Path = System.getProperty("user.dir") + "/src/main/java/com/loex/config/config.properties";
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    JSONArray jsonArray = null;
    //    String gettoken;
    private static Logger log = Logger.getLogger(Test.class.getClass());

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
    public void apiTest(HashMap<String, String> data) throws ClientProtocolException, IOException {
        String url = data.get("请求URL");
        String xuhao = data.get("序号");
        String apiname = data.get("接口名称");
        String yongliname = data.get("用例标题");
        String requestmtd = data.get("请求方法");
        String param = data.get("请求参数");
        String yuqijieguo = data.get("预期结果");

        testBase = new TestBase();
        host = TestBase.readProperties(userConfigProperties_Path, "HOST");
        bibibuyurl = host + url;

//        RestAssured.baseURI = host;
//        RestAssured.basePath = url;

        //获取token
        token = TestBase.readProperties(userConfigProperties_Path, "Token");
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
        headermap.put("exchange-token", "480cc2f4568503414832d1af07595990b04c1342c4df4e898d4db47fb2ad7397");
//        headermap.put("Host", "webapi.loex.abc");
//        headermap.put("Origin", "https://www.loex.abc");
//        headermap.put("Referer", "https://www.loex.abc/trade");
//        headermap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        //获取price随机数
//       HttpUtils httputilspr = new HttpUtils();
//       String price = httputilspr.randoms();
//       //获取volume随机数
//        HttpUtils httputilsvo = new HttpUtils();
//        String volume = httputilsvo.randoms();
        //对象转换成Json字符串
//        BibiBuyUsers user = new BibiBuyUsers(1,"BUY",price,volume,"ethlcny");
//        String regex="=(.*?)&";
//        Pattern p= Pattern.compile(regex);
//        Matcher m=p.matcher(param);
//        while(m.find()){
//            System.out.println("截取"+m.group(1));
//        }
//        String[] str = param.split("=");
//        String strend = str[str.length-1].toString();
//        System.out.println(strend);
//        BibiBuyUsers user = new BibiBuyUsers();
//        try {
//            Map maps = (Map) JSON.parse(param);
//            System.out.println("这个是用JSON类来解析JSON字符串!!!");
//            for (Object map : maps.entrySet()){
//                System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
//
//                if(((Map.Entry)map).getKey()=="type"){
//                    user.setType((String) ((Map.Entry)map).getValue());
//                }else if (((Map.Entry)map).getKey()=="price"){
//                    user.setPrice((String) ((Map.Entry)map).getValue());
//                }else if (((Map.Entry)map).getKey()=="side"){
//                    user.setSide((String) ((Map.Entry)map).getValue());
//                }else if (((Map.Entry)map).getKey()=="symbol"){
//                    user.setSymbol((String) ((Map.Entry)map).getValue());
//                }else if (((Map.Entry)map).getKey()=="volume"){
//                    user.setVolume((String) ((Map.Entry)map).getValue());
//                }
//            }
//
//        }catch (Exception e){}


//        user.setPrice("1");
//        user.setSide("BUY");
//        user.setType("1");
//        user.setSymbol("ethlcny");
//        user.setVolume("1");
//        String userJsonString = JSON.toJSONString(user);
        if(requestmtd.equals("post")){
            closeableHttpResponse = restClient.post(bibibuyurl, param, headermap);
        }else{
            closeableHttpResponse = restClient.get(bibibuyurl+param, headermap);
        }

//        Response response = given()
//                .contentType("application/json;charset=UTF-8")
//                .header("exchange-token","480cc2f4568503414832d1af07595990b04c1342c4df4e898d4db47fb2ad7397")      //要加上exchange-token
//                .body(param)
//                .when().log().all().post();
//        int responseCode = response.statusCode();
//        String responseContent = response.getBody().print();
//        System.out.println(responseCode);
//        System.out.println(responseContent.toString());
//        System.out.println(yuqijieguo);

        map.put("11", "序号");
        map.put("12", "接口名称");
        map.put("13", "用例标题");
        map.put("14", "请求URL");
        map.put("15", "请求方法");
        map.put("16", "请求参数");
        map.put("17", "预期结果");
        map.put("18", "测试结果");


        String sysxuhao = xuhao + 1;
        String sysjiekoumingcheng = xuhao + 2;
        String sysyonglibiaoti = xuhao + 3;
        String sysqingqiuurl = xuhao + 4;
        String sysqingqiufangfa = xuhao + 5;
        String sysqingqiucanshu = xuhao + 6;
        String sysyuqijieguo = xuhao + 7;
        String ceshijieguo = xuhao + 8;

        System.out.println(sysxuhao);


        map.put(sysxuhao, xuhao);
        map.put(sysjiekoumingcheng, apiname);
        map.put(sysyonglibiaoti, yongliname);
        map.put(sysqingqiuurl, url);
        map.put(sysqingqiufangfa, requestmtd);
        map.put(sysqingqiucanshu, param);
        map.put(sysyuqijieguo, yuqijieguo);



        //把最后一个行数写入properties文件
        String userConfigProperties_Path=System.getProperty("user.dir")+ "/src/main/java/com/loex/config/config.properties";
        TestBase.writeProperties(userConfigProperties_Path,"Rows",xuhao);
        log.info("行数"+xuhao+"写入properties文件成功");


//        Map<String, String> resultMap = sortMapByValue(map); //按Value进行排序

//        //验证状态码是不是200
//        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
//        log.info(statusCode);
//        //断言响应json内容中name和job是不是期待结果
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"utf-8");
        JSONObject responseJson = JSON.parseObject(responseString);
        if (responseString.equals(yuqijieguo)) {
            System.out.println("对");
            map.put(ceshijieguo, "PASS");

        } else {
            System.out.println("错");
            map.put(ceshijieguo, "Fail");
        }

//        String getdata = jsonObject.getString("data");
//        JSONObject jsonObject1 = JSONObject.parseObject(getdata);
//        String gettoken = jsonObject1.getString("token");
        log.info(responseString);
        log.info("---------");
        log.info(responseJson);
//        log.info(xuhao + apiname + yongliname + url + requestmtd + param + yuqijieguo);
//        Assert.assertEquals(statusCode, 200,"状态码不正确");

//        String name = TestUtil.getValueByJPath(responseJson, "name");
//        String job = TestUtil.getValueByJPath(responseJson, "job");
//        Assert.assertEquals(name, "Anthony","name is not same");
//        Assert.assertEquals(job, "tester","job is not same");

//        System.out.println(data.values());


    }

    /**
     * 写入Excel
     *
     * @param
     */
    @Test
    public void writeExcel() throws IOException {
        //获取行数
        testBase = new TestBase();
        String srows = TestBase.readProperties(userConfigProperties_Path, "Rows");
//        String sheets = TestBase.readProperties(userConfigProperties_Path, "sheet");
        int rows= Integer.parseInt( srows );
//        System.out.println(map.get(12));

        Set set = map.keySet();
        Object[] arr = set.toArray();
        Arrays.sort(arr);

        for (Object key : arr) {
            //System.out.println(key + ": " + map.get(key));
            String userConfigProperties_Path=System.getProperty("user.dir")+ "/src/main/java/com/loex/config/config.properties";
           TestBase.writeProperties(userConfigProperties_Path,map);
            log.info("token写入properties文件成功");
        }





        File exportFile = null;
        try {

            exportFile = new File("E:\\ideaWorkPlace\\loex\\src\\resources\\resout.xls");
            //新建一个excel文件
//            exportFile.createNewFile();
            //新建excel文件
            WritableWorkbook book = Workbook.createWorkbook(exportFile);
            WritableSheet sheet = book.createSheet("测试结果", 1); //新建一个sheet


            //3.添加数据
//            int rowNum = 0;
//            int colNum = 0;
//            for(List<String> row:xlsRows){
//                colNum=0;
//                for(String value:row){
//
//                   try {
//            int s =0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < 8; j++) {
                    String vue = TestBase.readProperties(userConfigProperties_Path, String.valueOf(i+1)+String.valueOf(j+1));
                        Label rowDataLabel = new Label(j, i,vue);
                        sheet.addCell(rowDataLabel);


                }

            }
//                    } catch (Exception e) {
//                        System.out.println("生成excel 数据  错误! 行数:"+rowNum + " 列数: "+colNum+e);
//                    }
//                    colNum++;
//                }
//                rowNum++;
//            }
            book.write();
            book.close();
        } catch (IOException | WriteException e) {
            System.out.println("生成excel错误!"+e);
        }

    }
}
