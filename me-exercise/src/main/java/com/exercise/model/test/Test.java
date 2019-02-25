package com.exercise.model.test;

import io.swagger.annotations.ApiModel;

import java.io.UnsupportedEncodingException;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str="token不能为空";
        str = new String(str.getBytes("gb2312"),"utf-8");
        System.out.println(str);
    }
}
