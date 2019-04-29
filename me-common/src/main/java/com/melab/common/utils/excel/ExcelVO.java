package com.melab.common.utils.excel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.poi.ss.usermodel.Workbook;

@Data
public class ExcelVO {
    @ApiModelProperty(value = "Workbook", name = "Excel工作簿")
    private Workbook workbook;

    @ApiModelProperty(value = "fileName", name = "输出文件名")
    private String fileName;

    @ApiModelProperty(value = "suffix", name = "文件后缀标识")
    private  String suffix;
}
