package org.example.utils;

public enum OPERATIONTYPE{
    LOGIN("/login"),
    LOGOUT("/logout"),
    SIGNUP("/signup"),
    DOWNLOAD("/download"),
    UPLOAD("/upload"),
    DELETE("/delete"),
    RENAME("/rename"),
    MOVE("/move"),
    COPY("/copy"),
    CREATEFOLDER("/createFolder"),
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
