package com.exercise.model.design.adapter;

public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("原来的业务逻辑");
    }
}
