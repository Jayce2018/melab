package com.exercise.model.design.Command;

class ConcreteCommand1 extends Command{

    private Receiver receiver;

    public ConcreteCommand1(Receiver receiver) {
        System.out.println("执行命令1");
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.doSomething();
    }
}
