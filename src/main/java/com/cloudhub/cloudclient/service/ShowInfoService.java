package com.cloudhub.cloudclient.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudhub.cloudclient.common.enums.BaseTypeEnum;
import com.cloudhub.cloudclient.common.enums.RequestEnum;
import com.cloudhub.cloudclient.common.result.AppResultCode;
import com.cloudhub.cloudclient.model.storage.FileInfo;
import com.cloudhub.cloudclient.model.storage.FolderInfo;
import com.cloudhub.cloudclient.model.storage.MiniInfo;
import com.cloudhub.cloudclient.service.request.RequestMaker;
import com.cloudhub.cloudclient.service.request.RequestSender;

import java.util.ArrayList;
import java.util.List;

public class ShowInfoService {
    public static List<MiniInfo> getInfo(long folerId){
        //返回folderId对应的文件夹下的所有文件和文件夹
        List<MiniInfo> list = null;
        String url = RequestMaker.makeRequestURL(RequestEnum.LIST, String.valueOf(folerId));
        String responseBody = RequestSender.getResponseBody(url);
        JSONObject jsonObject = JSON.parseObject(responseBody);
        int resultCode = jsonObject.getIntValue("resultCode");
        if (resultCode != AppResultCode.OPERATE.SUCCESS.getCode()){
            //!!!!!!!!!!!!!!!!!弹窗提示失败，正在重试
        }
        else{
            list=new ArrayList<MiniInfo>();
            JSONArray childList = jsonObject.getJSONArray("childList");
            for (int i = 0; i < childList.size(); i++) {
                JSONObject item = childList.getJSONObject(i);
                int baseType = item.getIntValue("baseType");
                if (baseType == BaseTypeEnum.FOLDER) {
                    // 文件夹
                    FolderInfo folder = item.toJavaObject(FolderInfo.class);
                    list.add(folder);
                } else if (baseType == BaseTypeEnum.FILE) {
                    // 文件
                    FileInfo fileInfo = item.toJavaObject(FileInfo.class);
                    list.add(fileInfo);
                }
            }
        }
        return list;
    }
}
