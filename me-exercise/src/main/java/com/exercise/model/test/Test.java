package com.exercise.model.test;

import io.swagger.annotations.ApiModel;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {


    public static void main(String[] args) {
        long m=5;
        int n=5;
        System.out.println(m==n);
    }


}
