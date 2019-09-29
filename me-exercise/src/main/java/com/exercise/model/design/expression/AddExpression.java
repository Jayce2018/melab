package com.exercise.model.design.expression;

import java.util.HashMap;

public class AddExpression extends SysbolExpression{

    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var)+super.left.interpreter(var);
    }

}
