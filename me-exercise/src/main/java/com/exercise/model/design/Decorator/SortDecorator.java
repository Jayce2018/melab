package com.exercise.model.design.decorator;

import io.swagger.annotations.ApiOperation;

public class SortDecorator extends Decorator{
    public SortDecorator(SchoolReport schoolReport) {
        super(schoolReport);
    }

    @ApiOperation(value = "reportSort", notes = "发送消息")
    private void reportSort(){
        System.out.println("我的排名是06名...");
    }

    @ApiOperation(value = "report", notes = "修饰排名后的成绩单")
    @Override
    public void report() {
        this.reportSort();
        super.report();
    }
}
