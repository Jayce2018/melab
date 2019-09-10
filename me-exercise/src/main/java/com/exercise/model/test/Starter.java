package com.exercise.model.test;

public class Starter {
    public static void main(String[] args) {
        BaseObject baseObject=new ConcreteObject();
        baseObject=new ConcreteDecorator1(baseObject);
        baseObject=new ConcreteDecorator2(baseObject);
        baseObject.printf();
    }
}
