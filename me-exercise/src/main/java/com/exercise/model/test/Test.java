package com.exercise.model.test;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {


    public static void main(String[] args) {

        /*BlockingQueue<Integer> integerBlockingQueue=new ArrayBlockingQueue<>(3);
        integerBlockingQueue.add(1);
        integerBlockingQueue.add(2);
        integerBlockingQueue.add(3);
        integerBlockingQueue.add(4);
        System.out.println(integerBlockingQueue);*/
        List<Integer> integerBlockingQueue= new ArrayList<Integer>(3);
        integerBlockingQueue.add(1);
        integerBlockingQueue.add(2);
        integerBlockingQueue.add(3);
        integerBlockingQueue.add(4);
        System.out.println(integerBlockingQueue);

    }


}
