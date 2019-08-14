package com.exercise.model.file;
import com.alibaba.fastjson.JSONObject;
import com.exercise.model.generic.vo.Book;

import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    public static void main(String args[]) {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            // 读入TXT文件
            String pathname = "me-exercise/resource/output.txt"; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String str;
            List<String> resultList=new ArrayList<>();
            while (br.readLine()!= null) {
                resultList.add(br.readLine());
                //System.out.println(br.readLine()); // 一次读入一行数据
            }
            for (String result:resultList) {
                System.out.println(result);
            }

            /* 写入Txt文件 */
            /*File writeName = new File("me-exercise/resource/output.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writeName));
            Book book=new Book();
            book.setBookId(10086L);
            book.setName("哈利波特");
            book.setAuthor("mina");
            book.setType(1);
            book.setTypeDictionary("魔幻书");
            out.write(JSONObject.toJSON(book).toString()); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            System.out.println("把缓存区内容压入文件");
            out.close(); // 最后记得关闭文件*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
