package com.exercise.model.design.expression;

public abstract class SysbolExpression extends Expression {
    protected Expression left;

    protected Expression right;

    public SysbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
