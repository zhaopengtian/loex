package com.loex.tests;



import com.loex.dirtodir.CopyDirToDir;
import com.loex.email.SendEmail;
import com.loex.util.HttpUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Test1 {



    public static void main(String[] args) throws Exception {
        //long start = System.currentTimeMillis();
        //递归实现文件夹的拷贝
        //dirCopy("src", "src2");
        //System.out.println(System.currentTimeMillis()-start);
        //start = System.currentTimeMillis();
        //Commons-IO实现文件夹拷贝
//        FileUtils.copyDirectoryToDirectory(new File("E:\\ideaWorkPlace\\loex\\test-output"), new File("D:\\BaiduNetdiskDownload"));
        //System.out.println(System.currentTimeMillis()-start);
//        FileUtils.deleteDirectory(new File("D:\\soft\\software2\\apache-tomcat-8.5.40-windows-x64\\apache-tomcat-8.5.40\\webapps\\ROOT\\test-output"));
//        Thread.sleep(10000);
//        FileUtils.copyDirectoryToDirectory(new File("E:\\ideaWorkPlace\\loex\\test-output"), new File("D:\\soft\\software2\\apache-tomcat-8.5.40-windows-x64\\apache-tomcat-8.5.40\\webapps\\ROOT"));

        new CopyDirToDir().copyDirToDir();
        System.out.println("测试报告移动到tomcat成功");

        SendEmail se1 = new SendEmail();
//        HttpUtils hu = new HttpUtils();
//        String en = hu.getProperties("EMAILNAME");
        se1.testSend("zhaopengtian2015@163.com");

        SendEmail se2 = new SendEmail();
        se2.testSend("3407135351@qq.com");
        System.out.println("发送测试报告到邮箱成功");

    }
}
