package com.melab.common.utils.excel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelOutputWithTemplateUtils {
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    @ApiOperation(value = "本地导出出口", notes = "exportLocal")
    public static void exportLocal(String fileName, String excelTemplateUrl, HashMap<Integer, String[][]> dateMap, String exportUrl) throws Exception {
        ExcelVO excelVO = transform(fileName, excelTemplateUrl, dateMap);
        export(excelVO.getWorkbook(), fileName, 1, excelVO.getSuffix(), exportUrl +"/"+ fileName + excelVO.getSuffix(), null);
    }

    @ApiOperation(value = "网络流导出出口", notes = "exportResponse")
    public static void exportResponse(String fileName, String excelTemplateUrl, HashMap<Integer, String[][]> dateMap, HttpServletResponse response) throws Exception {
        ExcelVO excelVO = transform(fileName, excelTemplateUrl, dateMap);
        export(excelVO.getWorkbook(), fileName, 2, excelVO.getSuffix(), null, response);
    }

    @ApiOperation(value = "getWorkBook", notes = "Workbook工作薄对象")
    public static Workbook getWorkBook(String uri) throws Exception {
        String[] strings = uri.split("\\.");
        String originalFilename = strings[strings.length - 1];

        File excelFile = new File(uri);
        // 获取文件输入流
        FileInputStream inputStream = new FileInputStream(excelFile);
        //创建一个webbook，对应一个Excel文件
        Workbook workbook;
        if (originalFilename.equals(xls)) {
            workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
        } else {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    @ApiOperation(value = "parseExcelResource", notes = "解析模板")
    public static void parseExcelResource(String uri) throws Exception {
        HashMap<Integer, Integer> columnSizeMap = new HashMap();
        Workbook workbook = getWorkBook(uri);
        //原始结果
        List<JSONObject> objectList = new ArrayList<>();
        //获取一共有多少sheet，然后遍历
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            //获取sheet中一共有多少行，遍历行（注意第一行是标题）
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int j = 0; j < physicalNumberOfRows; j++) {
                //获取每一行有多少单元格，遍历单元格
                Row row = sheet.getRow(j);
                int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                columnSizeMap.put(i, physicalNumberOfCells);
                for (int k = 0; k < physicalNumberOfCells; k++) {
                    Cell cell = row.getCell(k);
                    //纪录数据
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("sheet", i);
                    jsonObject.put("index", "(" + j + "," + k + ")");
                    jsonObject.put("value", cell.getStringCellValue());
                    objectList.add(jsonObject);

                }
            }
        }
        System.out.println("sheet列数：" + columnSizeMap);
        System.out.println("原始模板数据:" + JSONArray.toJSON(objectList));
    }

    @ApiOperation(value = "transform", notes = "数据转换")
    public static ExcelVO transform(String fileName, String excelUrl, HashMap<Integer, String[][]> dateMap) throws Exception {
        ExcelVO excelVO = new ExcelVO();
        //文件后缀
        String[] strings = excelUrl.split("\\.");
        String suffix = "." + strings[strings.length - 1];

        // 第一步，创建一个webbook，对应一个Excel文件
        Workbook wb = getWorkBook(excelUrl);

        //预设cell创建样式
        // 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
        CellStyle cellStyle = wb.createCellStyle();
        // 设置自动换行
        cellStyle.setWrapText(true);
        // 创建一个上下居中格式
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 左右居中
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置边框
        cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

        int numberOfSheets = wb.getNumberOfSheets();
        //sheet级别
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = wb.getSheetAt(i);
            //取得sheet渲染数据
            String[][] sheetData = dateMap.get(i);
            //sheet无数据跳过
            if (null == sheetData) {
                continue;
            }
            //row级别
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            for (int j = 0; j < sheetData.length; j++) {
                //1、模板行直接写值
                if (j < numberOfRows) {
                    Row row = sheet.getRow(j);
                    for (int k = 0; k < sheetData[j].length; k++) {
                        Cell cell = row.getCell(k);
                        if (StringUtils.isNotBlank(sheetData[j][k])) {
                            cell.setCellValue(sheetData[j][k]);
                        }
                    }
                } else {
                    //2、数据行新建并写值
                    Row row = sheet.createRow(j);
                    row.setHeightInPoints(25);
                    for (int k = 0; k < sheetData[j].length; k++) {
                        Cell cell = row.createCell(k);
                        if (StringUtils.isNotBlank(sheetData[j][k])) {
                            cell.setCellValue(sheetData[j][k]);
                            cell.setCellStyle(cellStyle);
                        }
                    }
                }
            }

            //冻结
            //ａ表示要冻结的列数；
            //ｂ表示要冻结的行数；
            //ｃ表示右边区域[可见]的首列序号；
            //ｄ表示下边区域[可见]的首行序号；
            //sheet.createFreezePane(1, 3, 1, 3);
        }

        excelVO.setWorkbook(wb);
        excelVO.setFileName(fileName);
        excelVO.setSuffix(suffix);
        return excelVO;
    }

    @ApiOperation(value = "export", notes = "workBook导出")
    public static void export(Workbook wb, String fileName, Integer type, String suffix, String exportUrl, HttpServletResponse response) throws IOException {
        switch (type) {
            //本地导出
            case 1: {
                try {
                    FileOutputStream fout = new FileOutputStream("d:/excel/" + fileName + suffix);
                    wb.write(fout);
                    String strSuccess = "导出" + fileName + "成功！";
                    System.out.println(strSuccess + ",路径：" + "d:/excel/" + fileName + suffix);
                    fout.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    String strFaile = "导出" + fileName + "失败！";
                    System.out.println(strFaile);
                }
                break;
            }
            //网页输出
            case 2: {
                response.setContentType("application/ms-excel;charset=UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
                OutputStream out = response.getOutputStream();
                try {
                    // 将数据写出去
                    wb.write(out);
                    String strSuccess = "导出" + fileName + suffix + "成功！";
                    System.out.println(strSuccess);
                } catch (Exception e) {
                    e.printStackTrace();
                    String strFaile = "导出" + fileName + suffix + "失败！";
                    System.out.println(strFaile);
                } finally {
                    out.close();
                }
                break;
            }
            default: {
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        HashMap<Integer, String[][]> dateMap = new HashMap<>();
        String[][] stringDate = new String[4][13];
        stringDate[0][0] = "书籍导出标题";
        stringDate[3][0] = "0";
        stringDate[3][1] = "哈姆雷特";
        stringDate[3][2] = "jayce";
        stringDate[3][3] = "2018-9";
        stringDate[3][4] = "sydfdasd-sadsb";
        stringDate[3][5] = "3014";
        /*for (int i = 0; i < stringDate.length; i++) {
            System.out.println(i + "->" + stringDate[i].length);
        }*/
        dateMap.put(1, stringDate);
        //parseExcelResource("d:/excel/测试模板.xlsx");
        exportLocal("模板结果测试", "d:/excel/mod/测试模板.xlsx", dateMap, "d:/excel");
    }

}
