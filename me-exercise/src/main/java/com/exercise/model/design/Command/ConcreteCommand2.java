package com.exercise.model.design.Command;

class ConcreteCommand2 extends Command{

    private Receiver receiver;

    public ConcreteCommand2(Receiver receiver) {
        System.out.println("执行命令2");
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.doSomething();
    }
}
