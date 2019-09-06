package com.exercise.model.design.responsibilitychain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    public static void main(String[] args) {
        Random random=new Random();
        List<IWomen> womenList=new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            IWomen women=new Women(random.nextInt(3)+1,"外出请求");
            womenList.add(women);
        }
        //责任链配置
        Handler father=new Father();
        Handler husband=new Husband();
        Handler son=new Son();
        father.setNextHandler(husband);
        husband.setNextHandler(son);
        //运行数据
        for (IWomen women:womenList) {
            System.out.println("-------当前依赖类型:"+TypeEnum.getValue(women.getType()));
            father.handleMessage(women);
        }
    }
}
