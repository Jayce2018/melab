package com.exercise.model.design.state;

public class Context {
    public final static OpenningState openningState=new OpenningState();

    public final static CloseingState closeingState=new CloseingState();

    public final static RunningState runningState=new RunningState();

    public final static StoppingState stoppingState=new StoppingState();

    //当前状态
    private LiftState liftState;

    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        this.liftState.setContext(this);
    }

    public void open() {
        this.liftState.open();
    }

    public void close(){
        this.liftState.close();
    }

    public void run(){
        this.liftState.run();
    }

    public void stop(){
        this.liftState.stop();
    }
}
