package com.exercise.model.design.visitor;

import lombok.Data;

@Data
public abstract class Employee {
    public final static String MALE="男";

    public final static String FEMALE="女";

    private String name;

    private int salary;

    private String sex;

    /**
    * 我允许一个访问者访问
    */
    public abstract void accept(IVisitor visitor);
}
