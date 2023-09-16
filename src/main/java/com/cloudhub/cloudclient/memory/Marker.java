package com.cloudhub.cloudclient.memory;

import com.cloudhub.cloudclient.model.storage.MiniInfo;

public class Marker {
    MiniInfo miniInfo;
    //单例模式
    private static Marker instance = new Marker();
    private Marker(){
        miniInfo=new MiniInfo(-1,-1);
    }
    public static Marker getInstance(){
        return instance;
    }
    public void set(MiniInfo miniInfo){
        this.miniInfo = miniInfo;
    }
    public  void set(long id,int baseType){
        miniInfo.setId(id);
        miniInfo.setBaseType(baseType);
    }
    public MiniInfo get(){
        return miniInfo;
    }
    public long getId(){
        return miniInfo.getId();
    }
    public int getBaseType(){
        return miniInfo.getBaseType();
    }
}
