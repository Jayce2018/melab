package com.exercise.model.design.director;


import lombok.Data;

/**
 * 首先，建造者模式的封装性很好。使用建造者模式可以有效的封装变化，在使用建造者模式的场景中，一般产品类和建造者类是比较稳定的
 * ，因此，将主要的业务逻辑封装在导演类中对整体而言可以取得比较好的稳定性。
 * <p>
 * 其次，建造者模式很容易进行扩展。如果有新的需求，通过实现一个新的建造者类就可以完成
 * ，基本上不用修改之前已经测试通过的代码，因此也就不会对原有功能引入风险。
 */
@Data
class Product {

    private Integer code;

    private String value;

    public void show() {
        System.out.println("code:" + code+",value:" + value);
    }
}

abstract class Builder {
    public abstract void setPart(Integer code, String value);

    public abstract Product getProduct();
}

class ConcreteBuilder extends Builder {
    private Product product = new Product();

    @Override
    public void setPart(Integer code, String value) {
        product.setCode(code);
        product.setValue(value);
    }

    @Override
    public Product getProduct() {
        return product;
    }
}

class Derector{

    public Product getProduct1(){
        Builder builder=new ConcreteBuilder();
        builder.setPart(1,"A");
        return builder.getProduct();
    }

    public Product getProduct2(){
        Builder builder=new ConcreteBuilder();
        builder.setPart(2,"B");
        return builder.getProduct();
    }
}

public class Client {
    public static void main(String[] args) {
        Derector derector=new Derector();
        Product product1 = derector.getProduct1();
        product1.show();

        Product product2 = derector.getProduct2();
        product2.show();
    }
}
