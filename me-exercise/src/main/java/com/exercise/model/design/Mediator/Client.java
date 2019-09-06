package com.exercise.model.design.mediator;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 适当地使用中介者模式可以避免同事类之间的过度耦合，使得各同事类之间可以相对独立地使用。
 * 使用中介者模式可以将对象间一对多的关联转变为一对一的关联，使对象间的关系易于理解和维护。
 * 使用中介者模式可以将对象的行为和协作进行抽象，能够比较灵活的处理对象间的相互作用。
 */
@Data
abstract class AbstractColleague{
    Integer num;

    public abstract void transformNum(Integer num,AbstractMediator mediator);
}


class ColleagueA extends AbstractColleague{
    @Override
    public void transformNum(Integer num, AbstractMediator mediator) {
        this.num=num;
        mediator.aTob();
    }
}

class ColleagueB extends AbstractColleague{

    @Override
    public void transformNum(Integer num, AbstractMediator mediator) {
        this.num=num;
        mediator.bToa();
    }
}

abstract class AbstractMediator{
    AbstractColleague a;
    AbstractColleague b;

    abstract void aTob();

    abstract void bToa();

    public AbstractMediator(AbstractColleague a, AbstractColleague b) {
        this.a = a;
        this.b = b;
    }
}

class Mediator extends AbstractMediator{

    public Mediator(AbstractColleague a, AbstractColleague b) {
        super(a, b);
    }

    @Override
    void aTob() {
        b.setNum(a.num*1000);
    }

    @Override
    void bToa() {
        a.setNum(b.num/1000);
    }
}

public class Client {
    public static void main(String[] args) {
        AbstractColleague colleagueA=new ColleagueA();
        AbstractColleague colleagueB=new ColleagueB();

        AbstractMediator abstractMediator=new Mediator(colleagueA,colleagueB);
        colleagueB.transformNum(1000,abstractMediator);

        System.out.println("colleagueA："+JSONObject.toJSON(colleagueA));
        System.out.println("colleagueB："+JSONObject.toJSON(colleagueB));
    }
}
