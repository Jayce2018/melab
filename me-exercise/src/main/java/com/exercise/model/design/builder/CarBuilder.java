package com.exercise.model.design.builder;

import java.util.List;

public abstract class CarBuilder {
    public abstract void serSequence(List<String> sequence);

    public abstract CarModel getCarModel();
}
