package com.exercise.model.test;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {


    public static void main(String[] args) {
        int[] ints={1,2,3};
        int index=0;
        System.out.println(index++);
        System.out.println(index);
    }


}
