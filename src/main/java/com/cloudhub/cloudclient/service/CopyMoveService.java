package com.cloudhub.cloudclient.service;

import com.alibaba.fastjson.JSONObject;
import com.cloudhub.cloudclient.common.enums.RequestEnum;
import com.cloudhub.cloudclient.model.storage.MiniInfo;
import com.cloudhub.cloudclient.service.request.RequestMaker;
import com.cloudhub.cloudclient.service.request.RequestSender;

import java.util.List;

public class CopyMoveService {
    public int batchCopy(String pairList,String distId,String userId){
        String url= RequestMaker.makeRequestURL(RequestEnum.COPY,pairList,distId,userId);
        String result= RequestSender.getResponseBody(url);
        JSONObject jsonObject=JSONObject.parseObject(result);
        int resultCode=jsonObject.getIntValue("resultCode");
        return resultCode;
    }
    public int batchCopy(List<MiniInfo> list,String distId,String userId){
        String pairList=JSONObject.toJSONString(list);
        return batchCopy(pairList,distId,userId);
    }
    public int batchMove(String pairList,String distId,String userId){
        String url= RequestMaker.makeRequestURL(RequestEnum.MOVE,pairList,distId,userId);
        String result= RequestSender.getResponseBody(url);
        JSONObject jsonObject=JSONObject.parseObject(result);
        int resultCode=jsonObject.getIntValue("resultCode");
        return resultCode;
    }
    public int batchMove(List<MiniInfo>list,String distId,String userId){
        String pairList=JSONObject.toJSONString(list);
        return batchMove(pairList,distId,userId);
    }
}
