package com.exercise.model.design.builder;

import java.util.ArrayList;
import java.util.List;

public abstract class CarModel {
    private List<String> sequence=new ArrayList<>();

    /**
    * 启动
    */
    protected abstract void start();

    /**
    * 停止
    */
    protected abstract void stop();

    /**
    * 喇叭
    */
    protected abstract void alarm();

    /**
    * 引擎
    */
    protected abstract void engineBoom();

    final public void run(){
        for (int i = 0; i < sequence.size(); i++) {
            String actionName=this.sequence.get(i);
            switch (actionName){
                case "start":{
                    this.start();
                    break;
                }
                case "stop":{
                    this.stop();
                    break;
                }
                case "alarm":{
                    this.alarm();
                    break;
                }
                case "boom":{
                    this.engineBoom();
                    break;
                }
                default:break;
            }
        }
    }

    /**
    * 建造-模板钩子传参,设置顺序
    */
    final public void setSequence(List<String> sequence){
        this.sequence=sequence;
    }
}
