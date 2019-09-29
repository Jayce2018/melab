package com.exercise.model.design.srategy;
//策略模式
public class ZhaoYun {
    public static void main(String[] args) {
        Context context;
        //刚到吴国，拆看第一个
        System.out.println("--刚到吴国，拆看第一个--");
        context=new Context(new BackDoor());
        context.operate();
        //刘备乐不思蜀，拆看第二个
        System.out.println("--刘备乐不思蜀，拆看第二个--");
        context=new Context(new GiveGreenLight());
        context.operate();
        //孙权的追兵来了，拆看第三个
        System.out.println("--孙权的追兵来了，拆看第三个--");
        context=new Context(new BlockEnemy());
        context.operate();
    }
}
