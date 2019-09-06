package com.exercise.model.design.decorator;

import io.swagger.annotations.ApiOperation;

public class HignScoreDecorator extends Decorator{
    public HignScoreDecorator(SchoolReport schoolReport) {
        super(schoolReport);
    }


    @ApiOperation(value = "reportHighScore", notes = "报高分")
    private void reportHighScore(){
        System.out.println("数学100 音乐100");
    }

    @ApiOperation(value = "report", notes = "提前汇报高分，先低个头")
    @Override
    public void report() {
        this.reportHighScore();
        super.report();
    }
}
