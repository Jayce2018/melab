package com.exercise.model.design.responsibilitychain;

public class Son extends Handler{
    public Son() {
        super(TypeEnum.TYPE_ENUM_SON.getCode());
    }

    @Override
    void response(IWomen women) {
        System.out.println("-------母亲向儿子请示-------");
        System.out.println(women.getRequest());
        System.out.println("儿子的答复是：同意");
        System.out.println("");
    }
}
