package com.loex.dirtodir;

import org.apache.commons.io.FileUtils;
import java.io.*;


public class CopyDirToDir {

    /**
     * 将测试报告移动到tomcat中
     *
     * @param
     */

    public void copyDirToDir() throws IOException, InterruptedException {
//        FileUtils.deleteDirectory(new File("D:\\soft\\software2\\apache-tomcat-8.5.40-windows-x64\\apache-tomcat-8.5.40\\webapps\\ROOT\\test-output"));
//        Thread.sleep(10000);
        FileUtils.copyDirectoryToDirectory(new File("E:\\ideaWorkPlace\\loex\\test-output"), new File("D:\\soft\\software2\\apache-tomcat-8.5.40-windows-x64\\apache-tomcat-8.5.40\\webapps\\ROOT"));

    }
}
