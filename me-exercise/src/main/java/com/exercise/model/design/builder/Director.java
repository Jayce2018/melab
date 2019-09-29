package com.exercise.model.design.builder;

import java.util.ArrayList;
import java.util.List;

public class Director {
    private List<String> sequence=new ArrayList<>();
    private BenzBuilder benzBuilder=new BenzBuilder();
    private BMWBuilder bmwBuilder=new BMWBuilder();

    /**
    * A Benz
    */
    public BenzModel getABenzModel(){
        this.sequence.clear();
        this.sequence.add("start");
        this.sequence.add("stop");
        this.sequence.add("boom");
        this.benzBuilder.serSequence(sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }
}
