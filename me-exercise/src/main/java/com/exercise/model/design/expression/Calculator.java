package com.exercise.model.design.expression;

import io.swagger.models.auth.In;

import java.util.HashMap;
import java.util.Stack;

public class Calculator {
    private Expression expression;

    public Calculator(String expStr) {
        Stack<Expression> stack=new Stack<Expression>();

        char[] charArray=expStr.toCharArray();
        //运算
        Expression left;
        Expression right;

        for (int i = 0; i <charArray.length ; i++) {
            switch (charArray[i]){
                case '+':{
                    left=stack.pop();
                    right=new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new AddExpression(left,right));
                    break;
                }
                case '-':{
                    left=stack.pop();
                    right=new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new SubExpression(left,right));
                    break;
                }
                default:{
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
                }
            }
        }
        this.expression=stack.pop();
    }
    //开始运算
    public int run(HashMap<String,Integer> var){
        return this.expression.interpreter(var);
    }

}
