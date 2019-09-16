package com.exercise.model.design.corp;

public abstract class Corp {
    private String name;

    private String position;

    private Corp parent;

    public Corp(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getInfo(){
        String info="姓名："+this.name;
        info=info+"\t职位:"+this.position;
        return info;
    }

    public void setParent(Corp parent){
        this.parent=parent;
    }

    public Corp getParent(){
        return this.parent;
    }
}
