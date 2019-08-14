package com.exercise.model.test;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {


    public static void main(String[] args) {
        System.out.println(Test.class.getResource("Test.class"));

    }


}
