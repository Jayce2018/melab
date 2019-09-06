package com.exercise.model.design.decorator;

import io.swagger.annotations.ApiOperation;

public abstract class SchoolReport {
    @ApiOperation(value = "report", notes = "成绩单展示成绩")
    public abstract void report();

    @ApiOperation(value = "sign", notes = "家长签字")
    public abstract void sign(String name);
}
