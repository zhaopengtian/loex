package com.loex.initialize;

import com.loex.dirtodir.CopyDirToDir;
import com.loex.email.SendEmail;
import com.loex.util.HttpUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class InitializeFile {

    @BeforeSuite
    public void setup(){

        System.out.println("开始");
    }

    @AfterSuite
    public void teadown() throws Exception {
//       CopyDirToDir cdtd = new CopyDirToDir();
//       cdtd.copyDirToDir();
//
//
//      SendEmail se = new SendEmail();
//      HttpUtils hu = new HttpUtils();
//      String en = hu.getProperties("EMAILNAME");
//      se.testSend(en);
      System.out.println("结束");
    }
}
