package com.exercise.model.design.state;
//状态模式
public class Client {
    public static void main(String[] args) {
        Context context=new Context();
        context.setLiftState(new CloseingState());
        context.open();
        context.close();
        context.run();
        context.stop();
        context.open();
    }
}
