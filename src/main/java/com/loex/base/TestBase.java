package com.loex.base;



import org.testng.annotations.Test;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class TestBase {
    /**
     * 获取指定properties文件中的值
     * @param propertiesPath
     * @param key
     * @return
     */
    public static String readProperties(String propertiesPath,String key){
        String re=null;
        Properties prop = new Properties();    //创建Properties对象
        InputStream in = null;
        try {
//            in = new FileInputStream(propertiesPath);   //创建输入流文件对象
//            prop.load(in);
            prop.load(new InputStreamReader(new FileInputStream(propertiesPath), "UTF-8"));
            re=prop.getProperty(key);//加载输入流
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                in.close();                         //关闭输入流
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return re;
    }


    /**
     * 写入properties
     * @param propertiesPath
     * @param key
     * @param value
     */
    public static void writeProperties(String propertiesPath, String key, String value) {
        Properties prop = new Properties();    //创建Properties对象
        InputStream in = null;
        FileOutputStream oFile = null;

        try {
            in = new FileInputStream(propertiesPath);   //创建输入流文件对象
            prop.load(in);
            //加载输入流

                     //修改值
            prop.setProperty(key, value);
            oFile = new FileOutputStream(propertiesPath);   //创建输出流文件对象
           PrintWriter printWriter =new PrintWriter(new OutputStreamWriter(oFile,"UTF-8"));
            prop.store(printWriter, "");                  //将Properties对象的属性保存到输出流指定文件
           // prop.store(oFile, "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oFile.close();                      //关闭输出流
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                in.close();                         //关闭输入流
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void writeProperties(String propertiesPath, Map<String,String> map) {
        Properties prop = new Properties();    //创建Properties对象
        InputStream in = null;
        FileOutputStream oFile = null;

        try {
            in = new FileInputStream(propertiesPath);   //创建输入流文件对象
            prop.load(in);
            //加载输入流
            for(String d : map.keySet()){
                prop.setProperty(d, map.get(d));
            }
            //修改值
            for(String key : map.keySet()){
                prop.setProperty(key, map.get(key));
            }
            oFile = new FileOutputStream(propertiesPath);   //创建输出流文件对象
            PrintWriter printWriter =new PrintWriter(new OutputStreamWriter(oFile,"UTF-8"));
            prop.store(printWriter, "");                  //将Properties对象的属性保存到输出流指定文件
            // prop.store(oFile, "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oFile.close();                      //关闭输出流
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                in.close();                         //关闭输入流
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
