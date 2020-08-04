package com.test.threaddemo;

import java.util.HashMap;
import java.util.Map;

public class EventType {
//    public static int TYPE_1 = 1;
//    public static int TYPE_2 = 2;
//    public static int TYPE_3 = 3;
//    public static int TYPE_4 = 4;
//    public static int TYPE_5 = 5;

    private static Map<Integer, Integer> map = new HashMap<>();
    static{
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,4);
        map.put(5,5);
    }

    public static int getType(int key){
        return map.get(key);
    }

}
