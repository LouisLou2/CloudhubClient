package com.cloudhub.cloudclient.service.request;

import com.cloudhub.cloudclient.common.AppProperties;
import com.cloudhub.cloudclient.common.enums.GeneralReq;
import com.cloudhub.cloudclient.common.enums.RequestEnum;

public class RequestMaker {
    public static String makeRequestURL(RequestEnum type, String... params){
        switch(type) {
            case SIGN_UP:return makeSignUpURL(params);
            case SIGN_IN:return makeSignInURL(params);
            case DOWNLOAD:return makeDownloadURL(params);
            case UPLOAD:return makeUploadURL(params);
            case LIST:return makeListURL(params);
            case DELETE:return makeDeleteURL(params);
            case COPY:return makeCopyURL(params);
            case MOVE:return makeMoveURL(params);
            case CREATE_FOLDER:return makeCreateFolderURL(params);
            case RENAME:return makeRenameURL(params);
            case UPDATE_USER_INFO:return makeUpdateUserInfoURL(params);
            case PREVIEW:return makePreviewURL(params);
        }
        return null;
    }
    //如果是注册请求，params[0]是用户名，params[1]是密码
    public static String makeSignUpURL(String...params) {
        return AppProperties.ADDRESS+ GeneralReq.USER.getType() +"/signup?name="+params[0]+"&password="+params[1];
    }
    //如果是登录请求，params[0]是用户名，params[1]是密码,params[2]是重复密码
    public static String makeSignInURL(String...params) {
        return AppProperties.ADDRESS+GeneralReq.USER.getType()+"/signin?name="+params[0]+"&password="+params[1];
    }
    //如果是下载请求，params[0]是文件id
    public static String makeDownloadURL(String...params) {
        return AppProperties.ADDRESS+GeneralReq.FILE.getType()+"/download?id="+params[0];
    }
    //如果是上传请求，params依次是userId, name, hash, size, type, pid
    public static String makeUploadURL(String...params) {
        return AppProperties.ADDRESS+GeneralReq.FILE.getType()+"/check_upload?userId="+params[0]+"&name="+params[1]+"&hash="+params[2]+"&size="+params[3]+"&type="+params[4]+"&pid="+params[5];
    }
    //如果是列表请求，params[0]是文件夹id
    public static String makeListURL(String...params) {
        return AppProperties.ADDRESS+GeneralReq.FILE.getType()+"/showinfo?folderId="+params[0];
    }
    //如果是删除请求，params[0]是文件一个List<MiniInfo> list的JSON字符串
    public static String makeDeleteURL(String...params) {
        return AppProperties.ADDRESS+GeneralReq.FILE.getType()+"/batch_delete?pairList="+params[0];
    }
    //如果是复制请求，params[0]是文件一个List<MiniInfo> list的JSON字符串，params[1]是目标文件夹id, params[2]是用户id
    public static String makeCopyURL(String...params) {
        return AppProperties.ADDRESS+GeneralReq.FILE.getType()+"/batch_copy?pairList="+params[0]+"&distId="+params[1]+ "&userId="+params[2];
    }
    //如果是移动请求，params[0]是文件一个List<MiniInfo> list的JSON字符串，params[1]是目标文件夹id
    public static String makeMoveURL(String...params) {
        return AppProperties.ADDRESS+GeneralReq.FILE.getType()+"/batch_move?pairList="+params[0]+"&distId="+params[1];
    }
    //如果是创建文件夹请求，params[0]是文件夹名，params[1]是父文件夹id
    public static String makeCreateFolderURL(String...params) {
        return AppProperties.ADDRESS+GeneralReq.FILE.getType()+"/createfolder?name="+params[0]+"&pid="+params[1];
    }
    //如果是重命名请求，params[0]是类型(文件/文件夹)，params[1]是id，params[2]是新名
    public static String makeRenameURL(String...params) {
        return AppProperties.ADDRESS+GeneralReq.FILE.getType()+"/rename?baseType="+params[0]+"&id="+params[1]+"&newName="+params[2];
    }
    //如果是更新用户信息请求，params[0]是用更新类型，params[1]是用户id，params[2]是更新信息
    public static String makeUpdateUserInfoURL(String...params) {
        return AppProperties.ADDRESS+GeneralReq.USER.getType()+"/update?ukey="+params[0]+"&id="+params[1]+"&value="+params[2];
    }

    public static String makePreviewURL(String...params) {
        return AppProperties.ADDRESS+GeneralReq.FILE.getType()+"/onlinepreview?id="+params[0];
    }
}
