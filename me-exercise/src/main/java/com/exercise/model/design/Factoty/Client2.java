package com.exercise.model.design.Factoty;

import io.swagger.annotations.ApiModel;

interface IFactory{
    Product1 createProduct1();

    Product2 createProduct2();
}

class Factory implements IFactory{
    @Override
    public Product1 createProduct1() {
        return new Product1();
    }

    @Override
    public Product2 createProduct2() {
        return new Product2();
    }
}

interface IProduct1{
    void show();
}

interface IProduct2{
    void show();
}

class Product1 implements IProduct1{
    @Override
    public void show() {
        System.out.println("----product1");
    }
}

class Product2 implements IProduct2{
    @Override
    public void show() {
        System.out.println("----product2");
    }
}

@ApiModel(description = "抽象工厂模式",value = "Client2")
public class Client2 {
    public static void main(String[] args) {
        IFactory factoty=new Factory();
        factoty.createProduct1().show();
        factoty.createProduct2().show();
    }
}
