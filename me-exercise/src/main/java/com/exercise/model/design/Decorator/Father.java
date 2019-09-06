package com.exercise.model.design.decorator;

public class Father {
    public static void main(String[] args) {
        //成绩单
        SchoolReport schoolReport;
        //原成绩单
        schoolReport=new FourGradeSchoolReport();
        //修饰分数
        schoolReport=new HignScoreDecorator(schoolReport);
        //修饰排名
        schoolReport=new SortDecorator(schoolReport);

        schoolReport.report();
        schoolReport.sign("dad");
    }
}
