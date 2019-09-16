package com.exercise.model.design.iterator;

import java.util.Vector;

public class ConcreteIterator implements Iterator {
    private Vector vector = new Vector();

    //游标
    public int cursor = 0;

    public ConcreteIterator(Vector vector) {
        this.vector = vector;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return vector.get(cursor++);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        if (cursor == vector.size()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean remove() {
        this.vector.remove(this.cursor);
        return true;
    }
}
