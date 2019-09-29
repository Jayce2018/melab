package com.exercise.model.design.observer;
/**
* 观察者（订阅）场景
*/
public class Client {
    public static void main(String[] args) throws InterruptedException {
        LiSi liSi=new LiSi();

        HanFeiZi hanFeiZi=new HanFeiZi();




        Watch breakfast=new Watch(hanFeiZi,liSi,"breakfast");
        hanFeiZi.haveBreakfast();
        breakfast.start();
        Thread.sleep(3000);

        Watch fun=new Watch(hanFeiZi,liSi,"fun");
        hanFeiZi.haveFun();
        fun.start();


    }
}
