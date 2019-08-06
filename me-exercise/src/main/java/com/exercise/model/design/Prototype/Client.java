package com.exercise.model.design.Prototype;

import lombok.Data;

/**
 * 使用原型模式创建对象比直接new一个对象在性能上要好的多，因为Object类的clone方法是一个本地方法，它直接操作内存中的二进制流，特别是复制大对象时，性能的差别非常明显。
 *
 * 使用原型模式的另一个好处是简化对象的创建，使得创建对象就像我们在编辑文档时的复制粘贴一样简单。
 *
 * 因为以上优点，所以在需要重复地创建相似对象时可以考虑使用原型模式。比如需要在一个循环体内创建对象，假如对象创建过程比较复杂或者循环次数很多的话，使用原型模式不但可以简化创建过程，而且可以使系统的整体性能提高很多。
 * */
class Prototype implements Cloneable{
    @Override
    protected Prototype clone() {
        try {
            return (Prototype)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

@Data
class ConcreteProtortpe extends Prototype{
    private Integer code;

    private String value;

    public void show(){
        System.out.println("code:"+code+",value:"+value);
    }
}

public class Client {
    public static void main(String[] args) {
        ConcreteProtortpe prototype=new ConcreteProtortpe();
        prototype.setCode(20);
        prototype.setValue("ok");
        prototype.show();

        ConcreteProtortpe prototype1 = (ConcreteProtortpe) prototype.clone();
        prototype.setValue("ko");
        prototype1.clone();
        prototype1.show();
        prototype.show();
    }
}
