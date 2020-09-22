package com.loex.listener;

import com.vimalselvam.testng.SystemInfo;
import org.testng.collections.Maps;

import java.util.Map;

public class MySystemInfo implements SystemInfo {

    @Override
    public Map<String, String> getSystemInfo() {
        Map<String,String> systeminfo = Maps.newHashMap();
        systeminfo.put("Test Env","");
        systeminfo.put("测试执行人","zhaopentian");

        return systeminfo;
    }
}
