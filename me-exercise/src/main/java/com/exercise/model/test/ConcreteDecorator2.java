package com.exercise.model.test;

public class ConcreteDecorator2 extends Decorator{
    private BaseObject baseObject;

    public ConcreteDecorator2(BaseObject baseObject) {
        super(baseObject);
        this.baseObject = baseObject;
    }

    private void method2(){
        System.out.println("装饰实现2");
    }

    @Override
    public void printf() {
        this.method2();
        this.baseObject.printf();
    }
}
