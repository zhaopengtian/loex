package com.loex.tests;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.loex.base.TestBase;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.testng.annotations.Test;


public class ExcelData {
    public Workbook workbook;
    public Sheet sheet;
    public Cell cell;
    int rows;
    int columns;
    public String fileName;
    public String caseName;
    public ArrayList<String> arrkey = new ArrayList<String>();
    String sourceFile;

    /**
     * @param fileName   excel文件名
     * @param caseName   sheet名
     */
    public ExcelData(String fileName, String caseName) {
        super();
        this.fileName = fileName;
        this.caseName = caseName;
    }

    /**
     * 获得excel表中的数据
     */
    public Object[][] getExcelData() throws BiffException, IOException {

        workbook = Workbook.getWorkbook(new File(getPath()));
        sheet = workbook.getSheet(caseName);
        rows = sheet.getRows();
        columns = sheet.getColumns();
        // 为了返回值是Object[][],定义一个多行单列的二维数组
        HashMap<String, String>[][] arrmap = new HashMap[rows - 1][1];
        // 对数组中所有元素hashmap进行初始化
        if (rows > 1) {
            for (int i = 0; i < rows - 1; i++) {
                arrmap[i][0] = new HashMap<>();
            }
        } else {
            System.out.println("excel中没有数据");
        }

        // 获得首行的列名，作为hashmap的key值
        for (int c = 0; c < columns; c++) {
            String cellvalue = sheet.getCell(c, 0).getContents();
            arrkey.add(cellvalue);
        }
        // 遍历所有的单元格的值添加到hashmap中
        for (int r = 1; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                String cellvalue = sheet.getCell(c, r).getContents();
                arrmap[r - 1][0].put(arrkey.get(c), cellvalue);
            }
        }
        return arrmap;
    }

    /**
     * 获得excel文件的路径
     * @return
     * @throws IOException
     */
    public String getPath() throws IOException {
        File directory = new File(".");
        sourceFile = directory.getCanonicalPath() + "\\src\\resources\\"
                + fileName + ".xls";
        return sourceFile;
    }

//    public static void main(String[] args) {
//
//    }


    /**
     * 获得excel表中的数据
     */

    public void ss() throws BiffException, IOException{

//            workbook = Workbook.getWorkbook(new File(getPath()));
//            FileInputStream fs = new FileInputStream(new File(getPath()));
        FileInputStream fs = new FileInputStream("E:\\ideaWorkPlace\\loex\\src\\resources\\testdata.xls");

            POIFSFileSystem ps = new POIFSFileSystem(fs);
            HSSFWorkbook wb = new HSSFWorkbook(ps);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = sheet.getRow(0);
            System.out.println(sheet.getLastRowNum()+"......"+row.getLastCellNum());

    }

    public static void main(String[] args) throws IOException, BiffException, WriteException {
//        FileInputStream fs = new FileInputStream("E:\\ideaWorkPlace\\loex\\src\\resources\\testdata.xls");
//
//        POIFSFileSystem ps = new POIFSFileSystem(fs);
//        HSSFWorkbook wb = new HSSFWorkbook(ps);
//        HSSFSheet sheet = wb.getSheetAt(0);
//        HSSFRow row = sheet.getRow(0);
//        int hang=0;
//        if("".equals(row)||row==null){
//            hang=0;
//        }else{
//            hang=sheet.getLastRowNum();
//            hang=hang-1;
//        }
//        System.out.println(sheet.getLastRowNum()+"......"+row.getLastCellNum());
//
//       FileOutputStream out = new FileOutputStream("E:\\ideaWorkPlace\\loex\\src\\resources\\testdata.xls");
//       row = sheet.createRow((short)(hang));
//        row.createCell(1).setCellValue("2");
//        row.createCell(2).setCellValue("3");
//        row.createCell(3).setCellValue("3");
//        row.createCell(4).setCellValue("4");
//        row.createCell(5).setCellValue("5");
//        row.createCell(6).setCellValue("6");
//       row.createCell(7).setCellValue("abcd");

//       out.flush();
//       wb.write(out);
//       out.close();
//       System.out.println(row.getPhysicalNumberOfCells()+"....."+row.getLastCellNum());




//        File exportFile = null;
//        try {
//
//            exportFile = new File("E:\\ideaWorkPlace\\loex\\src\\resources\\resout.xls");
//            //新建一个excel文件
////            exportFile.createNewFile();
//            //新建excel文件
//            WritableWorkbook book = Workbook.createWorkbook(exportFile);
//            WritableSheet sheet = book.createSheet("测试报告1", 1); //新建一个sheet
//
//            //3.添加数据
////            int rowNum = 0;
////            int colNum = 0;
////            for(List<String> row:xlsRows){
////                colNum=0;
////                for(String value:row){
////
////                   try {
////            int s =0;
//            for (int i = 0; i < 5; i++) {
//                for (int j = 0; j < 7; j++) {
//
//                    Label rowDataLabel = new Label(i, j,"aaa");
//                    sheet.addCell(rowDataLabel);
//
//                }
//
//            }
////                    } catch (Exception e) {
////                        System.out.println("生成excel 数据  错误! 行数:"+rowNum + " 列数: "+colNum+e);
////                    }
////                    colNum++;
////                }
////                rowNum++;
////            }
//            book.write();
//            book.close();
//        } catch (IOException e) {
//            System.out.println("生成excel错误!"+e);
//        }

        int i = 1;
        int s =2;
       String si = String.valueOf(i);
       String ss = String.valueOf(s);

        System.out.println(si+ss);




    }
//
}