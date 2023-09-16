package com.cloudhub.cloudclient.service;

import com.alibaba.fastjson.JSONObject;
import com.cloudhub.cloudclient.common.enums.RequestEnum;
import com.cloudhub.cloudclient.service.request.RequestMaker;
import com.cloudhub.cloudclient.service.request.RequestSender;

public class FileUpdateService {
    public static int createFolder(String name,long pid) {
        String url= RequestMaker.makeRequestURL(RequestEnum.CREATE_FOLDER,name,String.valueOf(pid));
        String result= RequestSender.getResponseBody(url);
        JSONObject jsonObject=JSONObject.parseObject(result);
        int resultCode=jsonObject.getIntValue("resultCode");
        return resultCode;
    }
    public static int rename(int baseType,long id,String newName) {
        String url= RequestMaker.makeRequestURL(RequestEnum.RENAME,String.valueOf(baseType),String.valueOf(id),newName);
        String result= RequestSender.getResponseBody(url);
        JSONObject jsonObject=JSONObject.parseObject(result);
        int resultCode=jsonObject.getIntValue("resultCode");
        return resultCode;
    }
}
