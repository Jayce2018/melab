package com.jayce.jcweb.controller.book;

import com.jayce.jcweb.common.book.entity.LibraryBook;
import com.jayce.jcweb.common.book.service.LibraryBookService;
import com.jayce.jcweb.common.usual.vo.request.UsualRequest;
import com.melab.common.utils.excel.ExcelOutputWithTemplateUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "/book")
@RestController
public class BookController {
    @Autowired
    private LibraryBookService libraryBookService;

    @RequestMapping(value = "/bookList", method = RequestMethod.POST)
    @ApiOperation(value = "bookList", notes = "获取所有图书", produces = "application/json")
    public List<LibraryBook> bookList(){
        return libraryBookService.selectListAll();
    }


    @RequestMapping(value = "/outputExcel", method = RequestMethod.POST)
    @ApiOperation(value = "outputExcel", notes = "导出Excel", produces = "application/json")
    public void outputExcel(@Validated(UsualRequest.OutputExcel.class)@RequestBody UsualRequest usualRequest, HttpServletResponse response) throws Exception {
        //参数
        Integer type = usualRequest.getType();
        Integer fileType = usualRequest.getFileType();
        String fileTempUrl="";
        String fileLocalSaveUrl="d:/excel";
        //数据源
        HashMap<Integer, String[][]> dateMap = new HashMap<>();
        String[][] stringDate = new String[4][13];
        stringDate[0][0] = "书籍导出标题";
        stringDate[3][0] = "0";
        stringDate[3][1] = "哈姆雷特";
        stringDate[3][2] = "jayce";
        stringDate[3][3] = "2018-9";
        stringDate[3][4] = "sydfdasd-sadsb";
        stringDate[3][5] = "3014";

        dateMap.put(0, stringDate);
        //类型判断
        switch (fileType){
            //xlsx
            case 1:{
                fileTempUrl="d:/excel/mod/测试模板.xlsx";
                break;
            }
            //xls
            case 2:{
                fileTempUrl="d:/excel/mod/测试模板.xls";
                break;
            }
        }
        //解析模板
        ExcelOutputWithTemplateUtils.parseExcelResource(fileTempUrl);
        switch (type){
            //本地
            case 1:{
                ExcelOutputWithTemplateUtils.exportLocal("模板结果测试", fileTempUrl, dateMap, fileLocalSaveUrl);
                break;
            }
            //web
            case 2:{
                ExcelOutputWithTemplateUtils.exportResponse("模板结果测试", fileTempUrl, dateMap, response);
                break;
            }
            default:{
                break;
            }
        }
    }

}
