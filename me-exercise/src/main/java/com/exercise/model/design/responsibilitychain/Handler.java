package com.exercise.model.design.responsibilitychain;

public abstract class Handler {

    //自身处理级别
    private int level=0;

    //责任链传递
    private Handler nextHandler;

    //构造自己的类型
    public Handler(int level) {
        this.level = level;
    }

    //处理方法
    public final void handleMessage(IWomen women){
        if(women.getType()==this.level){
            this.response(women);
        }else{
            if(null!=this.nextHandler){
                System.out.println(TypeEnum.getValue(this.level)+":不归我管，向下传递。下一级别："+TypeEnum.getValue(this.nextHandler.level));
                this.nextHandler.handleMessage(women);
            }else{
                System.out.println("没人负责，你放飞自我了（驳回）~");
            }
        }
    }

    //设置责任链
    public void setNextHandler(Handler handler){
        this.nextHandler=handler;
    }

    //响应请求
    abstract void response(IWomen women);
}
