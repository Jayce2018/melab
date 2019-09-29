package com.exercise.model.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        //定义一个玩家
        IGamePlayer gamePlayer=new GamePlayer("张三");
        //定义一个handler
        InvocationHandler handler=new GamePlayIH(gamePlayer);
        //开始时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("开始时间："+ simpleDateFormat.format(new Date()));
        //获得class loader
        ClassLoader classLoader=gamePlayer.getClass().getClassLoader();
        //动态产生一个代理者
        IGamePlayer proxy= (IGamePlayer) Proxy.newProxyInstance(classLoader,new Class[]{IGamePlayer.class},handler);
        //登陆
        proxy.login("zhangSan","password");
        Thread.sleep(1000);
        //开始杀怪
        proxy.killBoss();
        //升级
        proxy.upgrade();
        //结束时间
        System.out.println("结束时间："+simpleDateFormat.format(new Date()));
    }
}
