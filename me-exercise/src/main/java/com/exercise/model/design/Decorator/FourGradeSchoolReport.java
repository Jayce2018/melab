package com.exercise.model.design.decorator;

public class FourGradeSchoolReport extends SchoolReport{
    //成绩单
    @Override
    public void report() {
        System.out.println("敬爱的家长：");
        System.out.println("....");
        System.out.println("语文60 数学100 体育60");
        System.out.println("....");
        System.out.println("              家长签名：          ");
    }

    //签名
    @Override
    public void sign(String name) {
        System.out.println("家长签名："+name);
    }
}
