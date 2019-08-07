package com.exercise.model.design.Template;

class Sort {
    //选择排序:1
    void selectionSort(int[] ints) {
        long start = System.nanoTime();
        int index = 0;
        int min, tmp;
        for (int i = 0; i < ints.length; i++) {
            min = ints[i];
            boolean flag = false;
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[j] < min) {
                    min = ints[j];
                    index = j;
                    flag = true;
                }
            }
            if (flag) {
                tmp = ints[index];
                ints[index] = ints[i];
                ints[i] = tmp;
            }
        }
        long end = System.nanoTime();
        System.out.println("选择排序耗时(ns):" + (end - start));
    }

    //选择排序:1
    private void selectionSort1(int[] ints) {
        long start = System.nanoTime();
        int tmp;
        for (int i = 0; i < ints.length; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[j] < ints[i]) {
                    tmp = ints[i];
                    ints[i] = ints[j];
                    ints[j] = tmp;
                }
            }
        }
        long end = System.nanoTime();
        System.out.println("循环选择排序耗时(ns):" + (end - start));
    }

    //冒泡排序:2
    void bubbleSort(int[] ints) {
        long start = System.nanoTime();
        int size = ints.length;
        int tmp;
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size-i-1; j++) {
                if (ints[j+1] < ints[j]) {
                    tmp = ints[j + 1];
                    ints[j + 1] = ints[j];
                    ints[j] = tmp;
                }
            }
        }
        long end = System.nanoTime();
        System.out.println("冒泡排序耗时(ns):" + (end - start));
    }
}
