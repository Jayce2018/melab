package com.exercise.model.design.command;

class ConcreteReciver2 extends Receiver{

    @Override
    public void doSomething() {
        System.out.println("2号接收者在做事");
    }
}
