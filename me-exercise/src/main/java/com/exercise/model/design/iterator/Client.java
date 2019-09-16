package com.exercise.model.design.iterator;

public class Client {


    public static void main(String[] args) {
        Aggregate aggregate=new ConcreteAggregate();
        aggregate.add(1);
        aggregate.add(2);
        aggregate.add(3);
        aggregate.add(4);
        Iterator iterator = aggregate.iterator();
        while (iterator.hasNext()){

            System.out.println(iterator.next());
        }
    }
}
