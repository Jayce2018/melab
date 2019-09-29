package com.exercise.model.design.builder;

import java.util.List;

public class BMWBuilder extends CarBuilder{
    private BMWModel bmwModel=new BMWModel();

    @Override
    public void serSequence(List<String> sequence) {
        this.bmwModel.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.bmwModel;
    }
}
