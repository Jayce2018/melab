package com.exercise.model.design.command;

public class Client {

    public static void main(String[] args) {
        Receiver receiver;

        Invoker invoker=new Invoker();
        invoker.setCommand(new ConcreteCommand2(new ConcreteReciver2()));
        invoker.action();
    }
}
