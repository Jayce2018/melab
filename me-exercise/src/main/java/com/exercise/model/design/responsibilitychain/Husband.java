package com.exercise.model.design.responsibilitychain;

public class Husband extends Handler{
    public Husband() {
        super(TypeEnum.TYPE_ENUM_HUSBAND.getCode());
    }

    @Override
    void response(IWomen women) {
        System.out.println("-------妻子向丈夫请示-------");
        System.out.println(women.getRequest());
        System.out.println("丈夫的答复是：同意");
        System.out.println("");
    }
}
