package com.cloudhub.cloudclient.memory;

import com.cloudhub.cloudclient.model.storage.MiniInfo;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    ConcurrentHashMap<Long, List<MiniInfo>> cache = new ConcurrentHashMap<>();
    //单例模式
    private static Cache instance = new Cache();
    private Cache(){}
    public static Cache getInstance(){
        return instance;
    }
    public void put(long key, List<MiniInfo> value){
        cache.put(key, value);
    }
    public List<MiniInfo> get(long key){
        return cache.get(key);
    }
    public void remove(long key){
        cache.remove(key);
    }
    public boolean containsKey(long key){
        return cache.containsKey(key);
    }
    public void clear(){
        cache.clear();
    }
}
