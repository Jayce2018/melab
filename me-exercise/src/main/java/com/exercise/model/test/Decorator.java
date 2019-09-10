package com.exercise.model.test;

public abstract class Decorator extends BaseObject {
    private BaseObject baseObject;

    public Decorator(BaseObject baseObject) {
        this.baseObject = baseObject;
    }

    @Override
    public void printf() {
        this.baseObject.printf();
    }
}
