package com.exercise.model.design.srategy;

public class Context {
    private IStrategy strategy;
    public Context(IStrategy strategy){
        this.strategy=strategy;
    }
    //使用策略
    public void operate(){
        this.strategy.operate();
    }
}
