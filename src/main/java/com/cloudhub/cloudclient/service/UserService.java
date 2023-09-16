package com.cloudhub.cloudclient.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudhub.cloudclient.common.enums.BaseTypeEnum;
import com.cloudhub.cloudclient.common.enums.RequestEnum;
import com.cloudhub.cloudclient.memory.Cache;
import com.cloudhub.cloudclient.memory.History;
import com.cloudhub.cloudclient.model.storage.FileInfo;
import com.cloudhub.cloudclient.model.storage.FolderInfo;
import com.cloudhub.cloudclient.model.storage.MiniInfo;
import com.cloudhub.cloudclient.service.request.RequestMaker;
import com.cloudhub.cloudclient.service.request.RequestSender;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    //发送请求，处理返回内容，返回resultCode
    public static int login(String username, String password) {
        String url = RequestMaker.makeRequestURL(RequestEnum.SIGN_IN, username, password);
        String result = RequestSender.getResponseBody(url);
        // 解析 JSON 字符串
        JSONObject jsonObject = JSON.parseObject(result);
        // 提取 resultCode 的值
        int resultCode = jsonObject.getIntValue("resultCode");
        JSONArray rootList = jsonObject.getJSONArray("rootList");
        // 将根目录的 id 压入历史记录栈
        History.getInstance().push(0L);
        List<MiniInfo>list=new ArrayList<>();
        for (int i = 0; i < rootList.size(); i++) {
            JSONObject item = rootList.getJSONObject(i);
            int baseType = item.getIntValue("baseType");
            if (baseType ==BaseTypeEnum.FOLDER) {
                // 文件夹
                FolderInfo folder = JSON.parseObject(item.toJSONString(), FolderInfo.class);
                folder.setBaseType(BaseTypeEnum.FOLDER);
                list.add(folder);
            } else if (baseType == BaseTypeEnum.FILE) {
                // 文件
                FileInfo fileInfo = JSON.parseObject(item.toJSONString(), FileInfo.class);
                fileInfo.setBaseType(BaseTypeEnum.FILE);
                list.add(fileInfo);
            }
        }
        Cache.getInstance().put(0L, list);
        return resultCode;
    }
    //注册功能
    public static int signupCheck(String username, String password) {
        String url = RequestMaker.makeRequestURL(RequestEnum.SIGN_IN, username, password);
        String result = RequestSender.getResponseBody(url);
        // 解析 JSON 字符串
        JSONObject jsonObject = JSON.parseObject(result);
        // 提取 resultCode 的值
        int resultCode = jsonObject.getIntValue("resultCode");
        return resultCode;
    }
    //发送请求，处理返回内容，返回resultCode
    public static int update(String key, String id, String value) {
        String url = RequestMaker.makeRequestURL(RequestEnum.UPDATE_USER_INFO, key, id, value);
        String result = RequestSender.getResponseBody(url);
        // 解析 JSON 字符串
        JSONObject jsonObject = JSON.parseObject(result);
        // 提取 resultCode 的值
        int resultCode = jsonObject.getIntValue("resultCode");
        return resultCode;
    }
}
