package com.cloudhub.cloudclient.pojo;
import com.alibaba.fastjson.annotation.JSONField;

public class OBSDownloadReq {
    @JSONField(name = "endpoint",ordinal = 1)
    private String endpoint;
    @JSONField(name="ak", ordinal = 2)
    private String ak;
    @JSONField(name="sk", ordinal = 3)
    private String sk;
    @JSONField(name="bucketName", ordinal = 4)
    private String bucketName;
    @JSONField(name="objectKey", ordinal = 5)
    private String objectKey;
    //setters and getters
    public String getEndpoint() {
        return endpoint;
    }
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    public String getAk() {
        return ak;
    }
    public void setAk(String ak) {
        this.ak = ak;
    }
    public String getSk() {
        return sk;
    }
    public void setSk(String sk) {
        this.sk = sk;
    }
    public String getBucketname() {
        return bucketName;
    }
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
    public String getObjectKey() {
        return objectKey;
    }
    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }
}
