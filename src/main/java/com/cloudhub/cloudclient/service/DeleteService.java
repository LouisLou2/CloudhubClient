package com.cloudhub.cloudclient.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloudhub.cloudclient.common.enums.RequestEnum;
import com.cloudhub.cloudclient.model.storage.MiniInfo;
import com.cloudhub.cloudclient.service.request.RequestMaker;
import com.cloudhub.cloudclient.service.request.RequestSender;

import java.util.List;

public class DeleteService {
    public static  int batchDelete(String pairList){
        String url = RequestMaker.makeRequestURL(RequestEnum.DELETE,pairList);
        String result = RequestSender.getResponseBody(url);
        JSONObject jsonObject = JSON.parseObject(result);
        int resultCode = jsonObject.getIntValue("resultCode");
        return resultCode;
    }
    //这里的List<MiniInfo>list是真的MINiInfo，不是子类
    public static int batchDelete(List<MiniInfo>list){
        String pairList=JSON.toJSONString(list);
        String url = RequestMaker.makeRequestURL(RequestEnum.DELETE,pairList);
        String result = RequestSender.getResponseBody(url);
        JSONObject jsonObject = JSON.parseObject(result);
        int resultCode = jsonObject.getIntValue("resultCode");
        return resultCode;
    }
}
