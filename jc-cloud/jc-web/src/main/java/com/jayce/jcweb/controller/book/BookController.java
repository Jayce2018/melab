package com.jayce.jcweb.controller.book;

import com.alibaba.fastjson.JSONObject;
import com.jayce.jcweb.common.book.entity.LibraryBook;
import com.jayce.jcweb.common.book.service.LibraryBookService;
import com.jayce.jcweb.common.usual.vo.request.UsualRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping(value = "/book")
@RestController
public class BookController {
    @Autowired
    private LibraryBookService libraryBookService;

    @RequestMapping(value = "/bookOperate", method = {RequestMethod.GET})
    @ApiOperation(value = "getBooks", notes = "获取图书", produces = "application/json")
    public LibraryBook getBooks(@RequestParam Long bookId){
        return libraryBookService.selectById(bookId);
    }

    @RequestMapping(value = "/bookOperate", method = {RequestMethod.POST})
    @ApiOperation(value = "postBooks", notes = "修改图书", produces = "application/json")
    public void postBooks(@RequestBody LibraryBook bookRequest){
        bookRequest.setUpdateTime(new Date());
        libraryBookService.updateSelectiveById(bookRequest);
    }

    @RequestMapping(value = "/bookOperate", method = {RequestMethod.PUT})
    @ApiOperation(value = "putBooks", notes = "创建图书", produces = "application/json")
    public void putBooks(@RequestBody UsualRequest bookRequest){
        LibraryBook libraryBook = JSONObject.parseObject(JSONObject.toJSON(bookRequest).toString(), LibraryBook.class);
        libraryBook.setCreateTime(new Date());
        libraryBook.setStatus(1);
        libraryBookService.insertSelective(libraryBook);
    }

    @RequestMapping(value = "/bookOperate", method = {RequestMethod.DELETE})
    @ApiOperation(value = "deleteBooks", notes = "删除图书", produces = "application/json")
    public void deleteBooks(@RequestParam Long bookId){
        LibraryBook libraryBook=new LibraryBook();
        libraryBook.setBookId(bookId);
        libraryBook.setStatus(-1);
        libraryBook.setUpdateTime(new Date());
        libraryBookService.updateSelectiveById(libraryBook);
    }

    @RequestMapping(value = "/bookOperate", method = {RequestMethod.HEAD})
    @ApiOperation(value = "headBooks", notes = "校验信息", produces = "application/json")
    public void headBooks() throws Exception {
        throw new Exception("校验不通过");
    }

    @RequestMapping(value = "/bookList", method = RequestMethod.POST)
    @ApiOperation(value = "bookList", notes = "获取所有图书", produces = "application/json")
    public List<LibraryBook> bookList(){
        return libraryBookService.selectListAll();
    }


    /*@RequestMapping(value = "/outputExcel", method = RequestMethod.POST)
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
    }*/

}
