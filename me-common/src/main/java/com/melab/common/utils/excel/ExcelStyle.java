package com.melab.common.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * author: sun jie
 * date: 2018-07-29 23:27
 */

public class ExcelStyle {
    /**
     * 居中_描边_40度灰_字体样式
     * author: sun jie
     * date: 2018-07-29 23:30
     * param: [wb]
     * return: org.apache.poi.hssf.usermodel.HSSFCellStyle
     */

    public HSSFCellStyle styleCenterBoderColorGREY_40(HSSFWorkbook wb) {
        HSSFCellStyle stylese = wb.createCellStyle();
        stylese.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylese.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        stylese.setWrapText(true);// 设置自动换行
        stylese.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        stylese.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式
        //style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        stylese.setBottomBorderColor(HSSFColor.BLACK.index);
        stylese.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderTop(HSSFCellStyle.BORDER_THIN);

        HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
        headerFont.setFontName("黑体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
        stylese.setFont(headerFont); // 为标题样式设置字体样式
        return stylese;
    }

    /**
     * 居中_描边_25度灰_字体样式
     * author: sun jie
     * date: 2018-07-29 23:31
     * param: [wb]
     * return: org.apache.poi.hssf.usermodel.HSSFCellStyle
     */


    public HSSFCellStyle styleCenterBoderColorGREY_25(HSSFWorkbook wb) {
        HSSFCellStyle stylese = wb.createCellStyle();
        stylese.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylese.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylese.setWrapText(true);// 设置自动换行
        stylese.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        stylese.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式
        //style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        stylese.setBottomBorderColor(HSSFColor.BLACK.index);
        stylese.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderTop(HSSFCellStyle.BORDER_THIN);

        HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
        headerFont.setFontName("黑体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
        stylese.setFont(headerFont); // 为标题样式设置字体样式
        return stylese;
    }

    /**
     * 设置属性值的字体特殊色
     * author: sun jie
     * date: 2018-07-30 9:17
     * param: [wb]
     * return: org.apache.poi.hssf.usermodel.HSSFCellStyle
     */

    public HSSFCellStyle styleFontColorRed(HSSFWorkbook wb) {
        HSSFCellStyle stylese = wb.createCellStyle();
        stylese.setWrapText(true);// 设置自动换行
        stylese.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个上下居中格式
        stylese.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        stylese.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylese.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);

        // 设置边框
        stylese.setBottomBorderColor(HSSFColor.BLACK.index);
        stylese.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderTop(HSSFCellStyle.BORDER_THIN);

        HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
        headerFont.setFontName("黑体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
        headerFont.setColor(HSSFColor.RED.index);
        stylese.setFont(headerFont); // 为标题样式设置字体样式
        return stylese;
    }

    /**
     * 设置正文颜色，特殊处理为黄色
     * author: sun jie
     * date: 2018-07-30 9:37
     * param: [wb]
     * return: org.apache.poi.hssf.usermodel.HSSFCellStyle
     */

    public HSSFCellStyle styleContentColorYellow(HSSFWorkbook wb) {
        HSSFCellStyle stylese = wb.createCellStyle();
        stylese.setWrapText(true);// 设置自动换行
        stylese.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个上下居中格式
        stylese.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中

        stylese.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylese.setFillForegroundColor(HSSFColor.YELLOW.index);

        // 设置边框
        stylese.setBottomBorderColor(HSSFColor.BLACK.index);
        stylese.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderTop(HSSFCellStyle.BORDER_THIN);
        return stylese;
    }


    /**
     * 设置正文,居中描边
     * author: sun jie
     * date: 2018-07-30 9:37
     * param: [wb]
     * return: org.apache.poi.hssf.usermodel.HSSFCellStyle
     */

    public HSSFCellStyle styleContent(HSSFWorkbook wb) {
        // 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
        HSSFCellStyle stylese = wb.createCellStyle();
        stylese.setWrapText(true);// 设置自动换行
        stylese.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个上下居中格式
        stylese.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中

        // 设置边框
        stylese.setBottomBorderColor(HSSFColor.BLACK.index);
        stylese.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylese.setBorderTop(HSSFCellStyle.BORDER_THIN);
        return stylese;
    }


    /**
     * 标题样式
     * author: sun jie
     * date: 2018-07-30 9:37
     * param: [wb]
     * return: org.apache.poi.hssf.usermodel.HSSFCellStyle
     */

    public HSSFCellStyle styleTitle(HSSFWorkbook wb) {
        // 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
        HSSFCellStyle stylese = wb.createCellStyle();
        stylese.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        stylese.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        stylese.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        stylese.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont headerFont1 = (HSSFFont) wb.createFont(); // 创建字体样式
        //headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
        headerFont1.setFontName("黑体"); // 设置字体类型
        headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
        stylese.setFont(headerFont1); // 为标题样式设置字体样式
        return stylese;
    }


}
