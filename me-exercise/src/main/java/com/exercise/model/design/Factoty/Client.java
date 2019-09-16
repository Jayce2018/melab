
package com.exercise.model.design.factoty;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 工厂方法模式、以及抽象工厂模式。工厂模式是编程中经常用到的一种模式。它的主要优点有：
 *
 * 可以使代码结构清晰，有效地封装变化。在编程中，产品类的实例化有时候是比较复杂和多变的，通过工厂模式，将产品的实例化封装起来，使得调用者根本无需关心产品的实例化过程，只需依赖工厂即可得到自己想要的产品。
 * 对调用者屏蔽具体的产品类。如果使用工厂模式，调用者只关心产品的接口就可以了，至于具体的实现，调用者根本无需关心。即使变更了具体的实现，对调用者来说没有任何影响。
 * 降低耦合度。产品类的实例化通常来说是很复杂的，它需要依赖很多的类，而这些类对于调用者来说根本无需知道，如果使用了工厂方法，我们需要做的仅仅是实例化好产品类，然后交给调用者使用。对调用者来说，产品所依赖的类都是透明的。
 */
interface IFactory{
    Product createProduct();
}

class Factory implements IFactory{

    @Override
    public Product createProduct() {
        return new Product();
    }
}

interface IProduct{
    Product method();
}

@Data
class Product implements IProduct{

    private Integer code;

    private String value;

    @Override
    public Product method() {
        Product product=new Product();
        product.setCode(20);
        product.setValue("value");
        System.out.println(JSONObject.toJSON(product));
        return product;
    }
}

@ApiModel(description = "工厂模式",value ="Client")
public class Client {
    public static void main(String[] args) {
        IFactory factory = new Factory();
        IProduct product = factory.createProduct();
        product.method();
    }
}