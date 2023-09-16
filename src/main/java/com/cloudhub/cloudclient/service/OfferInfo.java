package com.cloudhub.cloudclient.service;

import com.cloudhub.cloudclient.memory.Cache;
import com.cloudhub.cloudclient.model.storage.MiniInfo;

import java.util.List;

public class OfferInfo {
    public static List<MiniInfo> getListBypid(long pid){
        List<MiniInfo>list= Cache.getInstance().get(pid);
        if(list==null){
            list=ShowInfoService.getInfo(pid);
        }
        return list;
    }
}
