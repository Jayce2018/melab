package com.exercise.model.design.corp;

import java.util.ArrayList;
import java.util.List;

public class Branch extends Corp{
    List<Corp> subordinateList=new ArrayList<Corp>();

    public Branch(String name, String position) {
        super(name, position);
    }

    public void addSubordinate(Corp corp){
        corp.setParent(this);
        this.subordinateList.add(corp);
    }

    public List<Corp> getSubordinate(){
        return this.subordinateList;
    }
}
