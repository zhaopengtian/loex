package com.loex.util;

import com.loex.base.TestBase;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class HttpUtils {

//    private static Logger log = Logger.getLogger(Test.class.getClass());
     String userConfigProperties_Path = System.getProperty("user.dir") + "/src/main/java/com/loex/config/config.properties";
//    public static void main(String[] args) {
//       System.out.println(get);
//    }

    //获取1-4随机数
    public String randoms() {
//            String random = String.format("%.17f", Math.random()).substring(0, 4);
            double a=1+Math.random()*4;
            DecimalFormat df = new DecimalFormat( "0.00" );
            String str=df.format( a );

            return str;

    }

//    public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
//
//        if (oriMap == null || oriMap.isEmpty()) {
//            return null;
//        }
//
//        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
//        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(oriMap.entrySet());
//        Collections.sort(entryList, new MapValueComparator());
//        Iterator<Map.Entry<String, String>> iter = entryList.iterator();
//        Map.Entry<String, String> tmpEntry = null;
//        while (iter.hasNext()) {
//            tmpEntry = iter.next();
//            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
//        }
//        return sortedMap;
//    }


    public String getTime(String time){
        long time1 = Long.parseLong(time);
        String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time1 * 1000));
        return result1;

    }

    /**
     * 获取tProperties文件配置
     *
     * @param
     */
    public String getProperties(String value) {
//        TestBase testBase = new TestBase();
        String host = TestBase.readProperties(userConfigProperties_Path, value);
        return host;
    }

    /**
     * 写入tProperties文件配置
     *
     * @param
     */
    public void wrProperties(String key,String value) {
//        TestBase testBase = new TestBase();
          TestBase.writeProperties(userConfigProperties_Path,key,value);
    }


}
