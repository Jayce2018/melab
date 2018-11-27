package com.exercise.model.generic.otherTestApp;

public class GenericTest {
    private static <T> void printfArr(T[] arr) {
        System.out.print("result:");
        for (T obj : arr) {
            System.out.print(obj + " ");
        }
    }

    private static void printfArrTest() {
        Integer[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] str = {"a", "b", "c", "d", "e"};
        System.out.println("Integer数组：");
        printfArr(b);
        System.out.println("\nString数组：");
        printfArr(str);
    }

    private static <T extends Comparable<T>> void max(T[] arr) {
        T max = arr[0];
        for (T obj : arr) {
            max = max.compareTo(obj) > 0 ? max : obj;
        }
        System.out.println("最大对象为：" + max);
    }

    private static void maxTest() {
        Integer[] a = {1, 0, 0, 8, 6, 9, 5, 2, 7};
        String[] str = {"h", "e", "l", "l", "o", "j", "a", "y", "c", "e"};
        max(str);
    }

    public static void main(String[] args) {
        maxTest();
    }
}
