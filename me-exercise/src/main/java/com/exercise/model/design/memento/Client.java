package com.exercise.model.design.memento;

//备忘录模式
public class Client {
    public static void main(String[] args) {
        Originator originator=new Originator();
        originator.setState("嗨皮");
        Caretaker caretaker=new Caretaker();
        caretaker.setMemento(originator.createMemento());
        originator.setState("sad");
        originator.restoreMemento(caretaker.getMemento());
        System.out.println(originator);
    }
}
