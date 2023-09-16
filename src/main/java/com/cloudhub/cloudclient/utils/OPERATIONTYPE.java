package com.cloudhub.cloudclient.utils;

public enum OPERATIONTYPE{
    DOWNLOAD("/download"),
    UPLOAD("/upload"),
    SHOWINFO("/showInfo"),
    LOGIN("/login"),
    SIGNUP("/signup"),
    DELETEFILE("/deleteFile"),
    CREATEFOLDER("/addNewFolder"),
    RENAMEFILE("/renameFile"),
    RENAMEFOLDER("/renameFolder"),

    RENAME("/rename"),
    MOVE("/move"),
    COPY("/copy"),
    GETFILELIST("/getFileList"),
    GETFILEINFO("/getFileInfo"),
    GETFILEINFOLIST("/getFileInfoList"),
    GETFILEINFOLISTBYPATH("/getFileInfoListByPath");

    private final String operationType;
    OPERATIONTYPE(String s) {
        this.operationType = s;
    }
    public String get(){
        return this.operationType;
    }
}
