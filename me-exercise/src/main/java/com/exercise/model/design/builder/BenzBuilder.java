package com.exercise.model.design.builder;

import java.util.List;

public class BenzBuilder extends CarBuilder{
    private BenzModel benz=new BenzModel();

    @Override
    public void serSequence(List<String> sequence) {
        this.benz.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.benz;
    }
}
