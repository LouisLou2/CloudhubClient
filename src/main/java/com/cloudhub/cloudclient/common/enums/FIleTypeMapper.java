package com.cloudhub.cloudclient.common.enums;

import java.util.concurrent.ConcurrentHashMap;

public class FIleTypeMapper {
    ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();
    //单例模式
    private static FIleTypeMapper instance = new FIleTypeMapper();
    private FIleTypeMapper(){
        map.put(1,"Picture");
        map.put(2,"Audio");
        map.put(3,"Video");
        map.put(4,"Document");
        map.put(5,"Other");
    }
    public static FIleTypeMapper getInstance(){
        return instance;
    }
    public String get(int type){
        return map.get(type);
    }
}
