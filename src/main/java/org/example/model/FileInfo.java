package org.example.model;

import com.alibaba.fastjson.annotation.JSONField;

public class FileInfo extends BaseFileInfo {
    @JSONField(name="fileName")
    private String fileName;
    @JSONField(name="fileSize")
    private long fileSize;
    @JSONField(name="createTime")
    private java.sql.Timestamp createTime;
    @JSONField(name="fileType")//.mp3和.wav都是audio
    private String fileType;
    @JSONField(name="folderId")
    private long folderId;
    //此字段不作为Get请求参数，仅用于上传文件时指定文件本地绝对路径
    private String fileAbsolutePath;

    //constructor
    public FileInfo() {
    }
    public FileInfo(long userId,String fileId,String fileName, long fileSize, java.sql.Timestamp createTime, String fileType, long folderId,String fileAbsolutePath) {
        super(userId,fileId);
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.createTime = createTime;
        this.fileType = fileType;
        this.folderId = folderId;
        this.fileAbsolutePath = fileAbsolutePath;
    }


    public String getFileAbsolutePath() {
        return fileAbsolutePath;
    }

    public void setFileAbsolutePath(String fileAbsolutePath) {
        this.fileAbsolutePath = fileAbsolutePath;
    }
    public String getFileId() {
        return super.getFileId();
    }

    public void setFileId(String fileId) {
        super.setFileId(fileId);
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public long getFolderId() {
        return folderId;
    }

    public void setFolderId(long folderId) {
        this.folderId = folderId;
    }

    public long getUserId() {
        return super.getUserId();
    }

    public void setUserId(long userId) {
        super.setUserId(userId);
    }
    //toString方法生成Get请求参数形式的字符串
    public String toString() {
        //String timeStr=this.getCreateTime().toString().replace(" ","%20");
        String timeStr="hello";
        return super.toString()+"&"+"fileName=" + this.getFileName() + "&fileSize=" + this.getFileSize() + "&createTime=" + timeStr + "&fileType=" + this.getFileType() + "&folderId=" + this.getFolderId();
    }
}