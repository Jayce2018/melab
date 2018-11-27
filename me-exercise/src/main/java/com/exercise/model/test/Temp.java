package com.exercise.model.test;

public class Temp {
    public static void main(String[] args) {
        Long n = 10086L;
        Long[] longs = new Long[4];

        longs[0] = 10086L;
        longs[1] = 10086L;
        longs[2] = 10086L;
        longs[3] = 10086L;
        for (int i = 0; i < longs.length; i++) {
            boolean flag = (n.equals(longs[i]));
            System.out.println(i + "<====>" + flag);
        }
    }
}
