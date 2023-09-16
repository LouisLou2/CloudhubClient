package com.cloudhub.cloudclient.common.enums;

public enum RequestEnum {
    SIGN_UP(1, "Sign up"),
    SIGN_IN(2, "Sign in"),
    SIGN_OUT(3, "Sign out"),
    UPLOAD(4, "Upload"),
    DOWNLOAD(5, "Download"),
    DELETE(6, "Delete"),
    CREATE_FOLDER(7, "Create folder"),
    RENAME(8, "Rename"),
    MOVE(9, "Move"),
    COPY(10, "Copy"),
    PREVIEW(11, "Preview"),
    SHARE(12, "Share"),
    CANCEL_SHARE(13, "Cancel share"),
    LIST(14, "List"),
    UPDATE_USER_INFO(15, "Update user info");
    final int code;
    final String msg;
    public static RequestEnum getEnumByCode(int code){
        for(RequestEnum e:RequestEnum.values()){
            if(e.getCode()==code){
                return e;
            }
        }
        return null;
    }
    RequestEnum(int code){
        this.code=code;
        this.msg="";
    }
    RequestEnum(int code, String msg){
        this.code=code;
        this.msg=msg;
    }
    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
    public enum USER_INFO{
        NAME(1,"name"),
        PHONE(2,"phone"),
        PASSWORD(3,"password"),
        BIRTHDAY(4,"birthday");
        final int code;
        final String msg;
        //constructor
        USER_INFO(int code, String msg){
            this.code=code;
            this.msg=msg;
        }
        //getters
        public int getCode(){
            return code;
        }
        public String getMsg(){
            return msg;
        }
        public static USER_INFO getEnumByCode(int code){
            for(USER_INFO e:USER_INFO.values()){
                if(e.getCode()==code){
                    return e;
                }
            }
            return null;
        }
        USER_INFO(int code){
            this.code=code;
            this.msg="";
        }
    }
}
