package com.exercise.model.design.expression;

import java.util.HashMap;

public class SubExpression extends SysbolExpression{

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var)-super.right.interpreter(var);
    }
}