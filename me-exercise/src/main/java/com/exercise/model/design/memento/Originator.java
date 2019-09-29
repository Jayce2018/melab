package com.exercise.model.design.memento;

import lombok.Data;

@Data
public class Originator {
    private String state;

    public IMemento createMemento(){
        return  new Memento(this.state);
    }

    public void restoreMemento(IMemento memento){
        this.setState(((Memento)memento).getState());
    }

    @Data
    class Memento implements IMemento{
        private String state;

        public Memento(String state) {
            this.state = state;
        }
    }
}
