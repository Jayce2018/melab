package com.exercise.model.design.command;

class ConcreteReciver1 extends Receiver{

    @Override
    public void doSomething() {
        System.out.println("1号接收者在做事");
    }
}
