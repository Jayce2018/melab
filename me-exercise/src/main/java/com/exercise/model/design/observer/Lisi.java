package com.exercise.model.design.observer;

public class LiSi implements ILiSi{

    @Override
    public void update(String str) {
        System.out.println("李斯：发现韩非子活动，开始向嬴政汇报~");
        this.report(str);
        System.out.println("李斯：汇报完毕\n");
    }

    private void report(String str) {
        System.out.println("李斯：报告，韩非子活动->"+str);
    }
}
