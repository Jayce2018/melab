package com.exercise.model.design.command;

/**
 * Created by 111223 on 2019/8/26.
 */
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void action(){
        this.command.execute();
    }
}
