package com.loex.tests;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jxl.read.biff.BiffException;


public class ExcelTest {
//    Calculator cal=new Calculator();

    @DataProvider(name="num")
    public Object[][] Numbers() throws BiffException, IOException{
        ExcelData e=new ExcelData("testdata", "calculator");
        return e.getExcelData();
    }
    @Test(dataProvider="num")
    public void testAdd(HashMap<String, String> data){
        System.out.println(data.toString());
//        float num1=Float.parseFloat(data.get("请求URL"));
//        float num2=Float.parseFloat(data.get("请求参数"));
//        float expectedResult=Float.parseFloat(data.get("C"));
        String num1=data.get("请求URL");
        String num2=data.get("请求参数");
//        float expectedResult=Float.parseFloat(data.get("C"));
        System.out.println(num1);
        System.out.println(num2);
        String regex="=(.*?)&";
        Pattern p= Pattern.compile(regex);
        Matcher m=p.matcher(num2);
        while(m.find()){
//            System.out.println("截取"+m.group(1));
//            String[] strArray = num2.split("&");
//            for (int i = 0; i < strArray.length; i++) {
//                System.out.println(i+":" + strArray[i]);
//                //其他操作
//                if(strArray[i].contains("type")&&strArray[i].contains(m.group(1))){
//                     System.out.println(m.group(1)+"属于type");
//                }else if(strArray[i].contains("symbol")&&strArray[i].contains(m.group(1))){
//                    System.out.println(m.group(1)+"属于symbol");
//                }else if(strArray[i].contains("a")&&strArray[i].contains(m.group(1))){
//                    System.out.println(m.group(1)+"属于a");
//                }
//            }
        }
//        String[] str = num2.split("=");
//        String strend = str[str.length-1].toString();
//        System.out.println(strend);
//
//
//
//        try {
//            Map maps = (Map) JSON.parse(num2);
//            System.out.println("这个是用JSON类来解析JSON字符串!!!");
//            for (Object map : maps.entrySet()){
//                System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
//                System.out.println(((Map.Entry)map).getKey().getClass().getName()+"........"+((Map.Entry)map).getValue().getClass().getName());
//            }
//
//        }catch (Exception e){}

//        try {
//            Map mapTypes = JSON.parseObject(num2);
//            System.out.println("这个是用JSON类的parseObject来解析JSON字符串!!!");
//            for (Object obj : mapTypes.keySet()){
//                System.out.println("key为："+obj+"值为："+mapTypes.get(obj));
//            }
//        }catch (){
//
//        }

//        Map mapType = JSON.parseObject(num2,Map.class);
//        System.out.println("这个是用JSON类,指定解析类型，来解析JSON字符串!!!");
//        for (Object obj : mapType.keySet()){
//            System.out.println("key为："+obj+"值为："+mapType.get(obj));
//        }

//        Map json = (Map) JSONObject.parse(num2);
//        System.out.println("这个是用JSONObject类的parse方法来解析JSON字符串!!!");
//        for (Object map : json.entrySet()){
//            System.out.println(((Map.Entry)map).getKey()+"  "+((Map.Entry)map).getValue());
//        }






            //其他操作
//            if(strArray[i].equals("H")){
////                    strB = strB.append(strArray[i]);
//            }else if(strArray[i].equals("I")){
////                    strB = strB.append(strArray[i]);
//            }
        }

//        System.out.println(expectedResult);
//        Float actual=cal.add(num1, num2);
//        Assert.assertEquals(actual, expectedResult);
    }


