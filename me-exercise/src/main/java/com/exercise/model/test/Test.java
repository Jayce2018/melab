package com.exercise.model.test;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {


    public static void main(String[] args) {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("1","2019-04-23 14:07:18");
        System.out.println(jsonObject.toJSONString());
        String str=jsonObject.toJSONString();
        JSONObject.parseObject(str);
    }


}
