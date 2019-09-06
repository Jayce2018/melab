package com.exercise.model.design.template;

import com.alibaba.fastjson.JSONObject;

/**
 * 容易扩展。一般来说，抽象类中的模版方法是不易反生改变的部分，而抽象方法是容易反生变化的部分，因此通过增加实现类一般可以很容易实现功能的扩展，符合开闭原则。
 * <p>
 * 便于维护。对于模版方法模式来说，正是由于他们的主要逻辑相同，才使用了模版方法，假如不使用模版方法，任由这些相同的代码散乱的分布在不同的类中，维护起来是非常不方便的。
 * <p>
 * 比较灵活。因为有钩子方法，因此，子类的实现也可以影响父类中主逻辑的运行。但是，在灵活的同时，由于子类影响到了父类，违反了里氏替换原则，也会给程序带来风险。这就对抽象类的设计有了更高的要求。
 * <p>
 * 在多个子类拥有相同的方法，并且这些方法逻辑相同时，可以考虑使用模版方法模式。在程序的主框架相同，细节不同的场合下，也比较适合使用这种模式。
 */
abstract class AbstractSort {
    abstract void sort(int[] ints, int type);

    void showResult(int[] ints,int type) {
        sort(ints, type);
        System.out.println("排序结果：" + JSONObject.toJSON(ints));
    }
}

class ConcreteSort extends AbstractSort {
    private Sort sort=new Sort();
    @Override
    void sort(int[] ints, int type) {
        switch (type) {
            case 1: {
                sort.selectionSort(ints);
                break;
            }
            case 2: {
                sort.bubbleSort(ints);
                break;
            }
            default: {
                break;
            }
        }
    }
}

public class Client {
    public static void main(String[] args) {
        AbstractSort sort = new ConcreteSort();
        int[] ints = {7, 6, 5, 4, 1, 0, 3, 4, 654, 8, 2, 5, 489, 46, 21, 45, 8, 45, 5, 6, 4, 2, 4, 8, 6, 989, 54, 48, 8, 45, 45, 8, 484, 545, 4, 4, 48, 48, 4, 4, 84, 84, 84, 55, 654, 8, 98, 1, 2, 6, 2, 8, 97, 654, 21, 8789, 5, 12, 32, 65, 98, 78, 45, 12, 123, 654, 987};
        //选择排序:1,冒泡排序:2
        sort.showResult(ints,1);
    }
}
