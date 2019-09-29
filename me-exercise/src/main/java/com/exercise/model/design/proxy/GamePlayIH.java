package com.exercise.model.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GamePlayIH implements InvocationHandler{
    //被代理者
    Class cls=null;

    //被代理的实例
    Object object=null;

    //代理谁
    public GamePlayIH(Object object){
        this.object=object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=method.invoke(this.object,args);
        //如果调用登陆，则发送通知
        if(method.getName().equals("login")){
            System.out.println("有人用我的账号在登录");
        }
        return result;
    }
}
