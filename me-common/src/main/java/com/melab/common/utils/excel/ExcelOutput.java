package com.melab.common.utils.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author sun jie
 * @date 2018-07-28 23:53
 */

public class ExcelOutput {
    /**
     * 导出到前端
     *
     * @param 【sheetName, titleName, fileName, columnNumber, columnWidth, columnName, dataList, response】
     * @return void
     * @author sun jie
     * @date 2018-07-28 23:53
     */

    public void exportWithResponse(String sheetName, String titleName,
                                   String fileName, int columnNumber, int[] columnWidth,
                                   String[] columnName, String[][] dataList,
                                   HttpServletResponse response) throws Exception {
        if (columnNumber == columnWidth.length && columnWidth.length == columnName.length) {
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            /*自定义样式表*/
            ExcelStyle excelStyle = new ExcelStyle();
            //标题样式
            HSSFCellStyle titleStyle = excelStyle.styleTitle(wb);
            //正文样式
            HSSFCellStyle contentStyle = excelStyle.styleContent(wb);
            //属性行样式
            HSSFCellStyle attributeStyle = excelStyle.styleCenterBoderColorGREY_40(wb);
            //首列样式
            HSSFCellStyle firstColumnStyle = excelStyle.styleCenterBoderColorGREY_25(wb);
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            // sheet.setDefaultColumnWidth(15); //统一设置列宽
            for (int i = 0; i < columnNumber; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i == j) {
                        // 单独设置每列的宽
                        sheet.setColumnWidth(i, columnWidth[j] * 256);
                    }
                }
            }
            // 创建第0行 也就是标题
            HSSFRow row1 = sheet.createRow((int) 0);
            // 设备标题的高度
            row1.setHeightInPoints(50);
            // 第三步创建标题的单元格样式style2以及字体样式headerFont1
            // 创建标题第一列
            HSSFCell cell1 = row1.createCell(0);
            // 合并第0到第17列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,columnNumber - 1));
            // 设置值标题
            cell1.setCellValue(titleName);
            // 设置标题样式
            cell1.setCellStyle(titleStyle);

            // 创建第1行 也就是表头
            HSSFRow row = sheet.createRow((int) 1);
            // 设置表头高度
            row.setHeightInPoints(37);

            // 第四步，创建表头单元格样式 以及表头的字体样式
            //后面追加样式
            // 第四.一步，创建表头的列
            for (int i = 0; i < columnNumber; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(columnName[i]);
            }

            // 第五步，创建单元格，并设置值
            for (int i = 0; i < dataList.length; i++) {

                row = sheet.createRow((int) i + 2);
                // 设置高度
                row.setHeightInPoints(25);

                // 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
                HSSFCell datacell = null;
                for (int j = 0; j < columnNumber; j++) {
                    datacell = row.createCell(j);
                    datacell.setCellValue(dataList[i][j]);
                    datacell.setCellStyle(contentStyle);
                }
            }
            /*冻结*/
            sheet.createFreezePane(1, 2, 1, 2);
            /*追加样式——填色*/
            HSSFRow rowT = sheet.getRow(1);
            HSSFCell cell = null;
            for (int i = 0; i < columnNumber; i++) {
                cell = rowT.getCell(i);
                cell.setCellStyle(attributeStyle);
            }
            //获得总行数
            int rowNum = sheet.getLastRowNum();
            //避开标题
            for (int i = 1; i <= rowNum; i++) {
                HSSFRow rowC = sheet.getRow(i);
                cell = rowC.getCell(0);
                cell.setCellStyle(firstColumnStyle);
            }
            // 第六步，将文件存到浏览器设置的下载位置
            String filename = fileName + ".xls";
            response.setContentType("application/ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
            OutputStream out = response.getOutputStream();
            try {
                // 将数据写出去
                wb.write(out);
                String str = "导出" + fileName + "成功！";
                System.out.println(str);
            } catch (Exception e) {
                e.printStackTrace();
                String str1 = "导出" + fileName + "失败！";
                System.out.println(str1);
            } finally {
                out.close();
            }

        } else {
            System.out.println("列数目长度名称三个数组长度要一致");
        }

    }

    /**
     * 导出到指定本地盘符
     *
     * @param 【sheetName, titleName, fileName, columnNumber, columnWidth, columnName, dataList】
     * @return void
     * @author sun jie
     * @date 2018-07-28 23:54
     */

    public void exportNoResponse(String sheetName, String titleName,
                                 String fileName, int columnNumber, int[] columnWidth,
                                 String[] columnName, String[][] dataList) throws Exception {
        if (columnNumber == columnWidth.length && columnWidth.length == columnName.length) {
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            /*自定义样式表*/
            ExcelStyle excelStyle = new ExcelStyle();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            // sheet.setDefaultColumnWidth(15); //统一设置列宽
            for (int i = 0; i < columnNumber; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i == j) {
                        // 单独设置每列的宽
                        sheet.setColumnWidth(i, columnWidth[j] * 256);
                    }
                }
            }
            // 创建第0行 也就是标题
            HSSFRow row1 = sheet.createRow((int) 0);
            // 设备标题的高度
            row1.setHeightInPoints(50);
            // 第三步创建标题的单元格样式style2以及字体样式headerFont1
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
            style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            // 创建字体样式
            HSSFFont headerFont1 = (HSSFFont) wb.createFont();
            //headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            // 设置字体类型
            headerFont1.setFontName("黑体");
            // 设置字体大小
            headerFont1.setFontHeightInPoints((short) 15);
            // 为标题样式设置字体样式
            style2.setFont(headerFont1);

            // 创建标题第一列
            HSSFCell cell1 = row1.createCell(0);
            // 合并第0到第17列
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0,columnNumber - 1));
            // 设置值标题
            cell1.setCellValue(titleName);
            // 设置标题样式
            cell1.setCellStyle(style2);

            // 创建第1行 也就是表头
            HSSFRow row = sheet.createRow((int) 1);
            // 设置表头高度
            row.setHeightInPoints(37);

            // 第四步，创建表头单元格样式 以及表头的字体样式
            //后面追加样式
            // 第四.一步，创建表头的列
            for (int i = 0; i < columnNumber; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(columnName[i]);
            }

            // 第五步，创建单元格，并设置值
            for (int i = 0; i < dataList.length; i++) {
                HSSFCellStyle shouliese = wb.createCellStyle();
                shouliese.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);

                row = sheet.createRow((int) i + 2);
                // 设置高度
                row.setHeightInPoints(25);
                // 为数据内容设置特点新单元格样式1 自动换行 上下居中
                HSSFCellStyle zidonghuanhang = wb.createCellStyle();
                // 设置自动换行
                zidonghuanhang.setWrapText(true);
                // 创建一个居中格式
                zidonghuanhang.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

                // 设置边框
                zidonghuanhang.setBottomBorderColor(HSSFColor.BLACK.index);
                zidonghuanhang.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang.setBorderRight(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang.setBorderTop(HSSFCellStyle.BORDER_THIN);

                // 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
                HSSFCellStyle zidonghuanhang2 = wb.createCellStyle();
                // 设置自动换行
                zidonghuanhang2.setWrapText(true);
                // 创建一个上下居中格式
                zidonghuanhang2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                // 左右居中
                zidonghuanhang2.setAlignment(HSSFCellStyle.ALIGN_CENTER);

                // 设置边框
                zidonghuanhang2.setBottomBorderColor(HSSFColor.BLACK.index);
                zidonghuanhang2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang2.setBorderRight(HSSFCellStyle.BORDER_THIN);
                zidonghuanhang2.setBorderTop(HSSFCellStyle.BORDER_THIN);
                HSSFCell datacell = null;
                for (int j = 0; j < columnNumber; j++) {
                    datacell = row.createCell(j);
                    datacell.setCellValue(dataList[i][j]);
                    datacell.setCellStyle(zidonghuanhang2);
                }
            }
            /*冻结*/
            sheet.createFreezePane(1, 2, 1, 2);
            /*追加样式——填色*/
            HSSFRow rowT = sheet.getRow(1);
            HSSFCell cell = null;
            for (int i = 0; i < columnNumber; i++) {
                cell = rowT.getCell(i);
                HSSFCellStyle stylese = excelStyle.styleCenterBoderColorGREY_40(wb);
                cell.setCellStyle(stylese);
            }
            //获得总行数
            int rowNum = sheet.getLastRowNum();
            //避开标题
            for (int i = 1; i <= rowNum; i++) {
                HSSFRow rowC = sheet.getRow(i);
                cell = rowC.getCell(0);
                HSSFCellStyle stylese = excelStyle.styleCenterBoderColorGREY_25(wb);
                cell.setCellStyle(stylese);
            }
            /*特殊字体颜色处理*/
            HSSFRow rowCl = null;
            for (int i = 0; i <= rowNum; i++) {
                rowCl = sheet.getRow(i);
                for (int j = 0; j <= columnNumber; j++) {
                    if (null != rowCl.getCell(j)) {
                        cell = rowCl.getCell(j);
                        if (StringUtils.isNotBlank(cell.getStringCellValue())) {
                            String cellValue = cell.getStringCellValue();

                        }
                    }
                }

            }


            // 第六步，将文件存到指定位置
            try {
                FileOutputStream fout = new FileOutputStream("E:" + fileName + ".xls");
                wb.write(fout);
                String str = "导出" + fileName + "成功！";
                System.out.println(str + ",路径：" + "E:" + fileName + ".xls");
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
                String str1 = "导出" + fileName + "失败！";
                System.out.println(str1);
            }
        } else {
            System.out.println("列数目长度名称三个数组长度要一致");
        }

    }
}
