package com.exercise.model.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.exercise.model.generic.vo.Book;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.checkerframework.checker.units.UnitsTools.s;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {


    public static void main(String[] args) {
        List<Book> bookList=new ArrayList<>();
        Book book1=new Book();
        book1.setAuthor("author1");
        book1.setName("name1");
        bookList.add(book1);
        Book book2=new Book();
        book2.setAuthor("author2");
        book2.setName("name2");
        bookList.add(book2);
        System.out.println(JSONArray.toJSON(bookList).toString());
        String str="[{\"author\":\"author1\",\"name\":\"name1\"},{\"author\":\"author2\",\"name\":\"name2\"}]";
        String str2="[\"{\\\"capTime\\\":1567570131299,\\\"cardNo\\\":\\\"330124196001010037\\\",\\\"channelCode\\\":\\\"19ecd98f3ead4d15b6cb85a17c828d80\\\",\\\"channelId\\\":\\\"19ecd98f3ead4d15b6cb85a17c828d80\\\",\\\"controlCode\\\":\\\"42010a0662544f6b8d6095d5ff1bfa0d\\\",\\\"gpsX\\\":120.689422906444,\\\"gpsY\\\":30.101491235916,\\\"idType\\\":\\\"111\\\",\\\"imgUrl1\\\":\\\"http://10.35.104.122:9529/image/carRecord/car.jpg\\\",\\\"imgUrl2\\\":\\\"http://10.35.104.122:9529/image/carRecord/carNo.jpg\\\",\\\"name\\\":\\\"裘华\\\",\\\"objId\\\":\\\"浙A7DS18_02\\\",\\\"objType\\\":1,\\\"recordId\\\":\\\"4d1400a691d84333a727e32e1942b77b\\\"}\",\"{\\\"capTime\\\":1567570126282,\\\"cardNo\\\":\\\"420222199101017991\\\",\\\"channelCode\\\":\\\"19ecd98f3ead4d15b6cb85a17c828d80\\\",\\\"channelId\\\":\\\"19ecd98f3ead4d15b6cb85a17c828d80\\\",\\\"controlCode\\\":\\\"42010a0662544f6b8d6095d5ff1bfa0d\\\",\\\"gpsX\\\":120.689422906444,\\\"gpsY\\\":30.101491235916,\\\"idType\\\":\\\"111\\\",\\\"imgUrl1\\\":\\\"http://10.35.104.122:9529/image/carRecord/car.jpg\\\",\\\"imgUrl2\\\":\\\"http://10.35.104.122:9529/image/carRecord/carNo.jpg\\\",\\\"name\\\":\\\"游州\\\",\\\"objId\\\":\\\"沪C0FG22_02\\\",\\\"objType\\\":1,\\\"recordId\\\":\\\"33b143604c824575b75cf781feb2c639\\\"}\",\"{\\\"capTime\\\":1567570141329,\\\"cardNo\\\":\\\"320722197401015438\\\",\\\"channelCode\\\":\\\"19ecd98f3ead4d15b6cb85a17c828d80\\\",\\\"channelId\\\":\\\"19ecd98f3ead4d15b6cb85a17c828d80\\\",\\\"controlCode\\\":\\\"42010a0662544f6b8d6095d5ff1bfa0d\\\",\\\"gpsX\\\":120.689422906444,\\\"gpsY\\\":30.101491235916,\\\"idType\\\":\\\"111\\\",\\\"imgUrl1\\\":\\\"http://10.35.104.122:9529/image/carRecord/car.jpg\\\",\\\"imgUrl2\\\":\\\"http://10.35.104.122:9529/image/carRecord/carNo.jpg\\\",\\\"name\\\":\\\"石岭\\\",\\\"objId\\\":\\\"浙A579C0_02\\\",\\\"objType\\\":1,\\\"recordId\\\":\\\"b4c26813302d4cdf8df7319588d4f6af\\\"}\"]";
        List<Book> sss = JSONArray.parseArray(str, Book.class);
        System.out.println(sss);
    }


}
