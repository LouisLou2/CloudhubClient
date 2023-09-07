package org.example.model;

import com.alibaba.fastjson.annotation.JSONField;

public class OBSUploadReq {
    @JSONField(name = "demand",ordinal = 0)
    private boolean demand;
    @JSONField(name = "endpoint",ordinal = 1)
    private String Endpoint=null;
    @JSONField(name="ak", ordinal = 2)
    private String ak=null;
    @JSONField(name="sk", ordinal = 3)
    private String sk=null;
    @JSONField(name="bucketName", ordinal = 4)
    private String bucketName =null;
    @JSONField(name="objectKey", ordinal = 5)
    private String objectKey=null;
    //setters and getters
    public boolean getDemand() {
        return demand;
    }
    public void setDemand(boolean demand) {
        this.demand = demand;
    }
    public String getEndpoint() {
        return Endpoint;
    }
    public void setEndpoint(String endpoint) {
        Endpoint = endpoint;
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
