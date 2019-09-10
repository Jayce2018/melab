package com.exercise.model.test;

public class ConcreteDecorator1 extends Decorator{
    private BaseObject baseObject;

    public ConcreteDecorator1(BaseObject baseObject) {
        super(baseObject);
        this.baseObject = baseObject;
    }

    private void method1(){
        System.out.println("装饰实现1");
    }

    @Override
    public void printf() {
        this.method1();
        this.baseObject.printf();
    }
}
