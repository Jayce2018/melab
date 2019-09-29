package com.exercise.model.design.observer;

import java.util.Observable;

public class HanFeiZi extends Observable implements IHanFeiZi{
    private boolean isHavingBreakfast=false;

    private boolean isHavingFun=false;

    @Override
    public void haveBreakfast() {
        System.out.println("韩非子：开始吃饭了");
        this.isHavingBreakfast=true;
    }

    @Override
    public void haveFun() {
        System.out.println("韩非子：开始嗨皮了");
        this.isHavingFun=true;
    }

    public boolean isHavingBreakfast() {
        return isHavingBreakfast;
    }

    public void setHavingBreakfast(boolean havingBreakfast) {
        isHavingBreakfast = havingBreakfast;
    }

    public boolean isHavingFun() {
        return isHavingFun;
    }

    public void setHavingFun(boolean havingFun) {
        isHavingFun = havingFun;
    }
}
