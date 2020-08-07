package com.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringDemo {
    private int i = 100;
    private char[] c = {'a', '2', '4', '3', 'b'};
    private int[] array = {1, 4, 53, 76, 12, 43};
    private List<Test> list = new ArrayList<Test>(){{
        add(new Test("zhangsan", 18));
        add(new Test("lisi", 19));
        add(new Test("wangwu", 20));
    }};

    public static void main(String[] args) {
        StringDemo sd = new StringDemo();
        System.out.println(sd.i);
        System.out.println(sd.c);
        System.out.println(sd.array);
        System.out.println(sd.array.toString());
        System.out.println(String.valueOf(sd.array));
        System.out.println(Arrays.toString(sd.array));
        System.out.println(sd.array.length);
        System.out.println(sd.list.toString());
        System.out.println(sd.list);
        System.out.println(sd.list.size());
    }
}

class Test{
    public String name;
    public int age;

    public Test(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

