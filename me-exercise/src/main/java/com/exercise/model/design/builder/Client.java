package com.exercise.model.design.builder;
//创建者模式
public class Client {
    public static void main(String[] args) {
        Director director=new Director();
        director.getABenzModel().run();
    }
}
