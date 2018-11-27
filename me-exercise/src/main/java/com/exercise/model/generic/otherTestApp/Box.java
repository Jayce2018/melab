package com.exercise.model.generic.otherTestApp;

public class Box<T> {
    private T t;

    private static void printTest() {
        Box<Integer[]> box = new Box<>();
        Integer[] a = {1, 0, 0, 8, 6};
        box.add(a);
        Integer[] s = box.get();
        for (Integer obj : s) {
            System.out.print(obj + " ");
        }
    }

    public static void main(String[] args) {
        printTest();
    }

    private void add(T t) {
        this.t = t;
    }

    private T get() {
        return t;
    }
}
