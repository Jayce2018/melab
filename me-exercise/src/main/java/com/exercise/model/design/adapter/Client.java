package com.exercise.model.design.adapter;

public class Client {
    public static void main(String[] args) {
        //原来的业务逻辑
        Target target=new ConcreteTarget();
        target.request();

        //外部逻辑适配
        Target target2=new Adapter();
        target2.request();
    }
}
