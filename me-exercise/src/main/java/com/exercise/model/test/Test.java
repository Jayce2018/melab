package com.exercise.model.test;

import io.swagger.annotations.ApiModel;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {

    public static void main(String[] args) {
        int a=0,b=0;
        for (int i = 0; i <100 ; i++) {
            a=b++;
        }
        System.out.println(a);
    }


}
