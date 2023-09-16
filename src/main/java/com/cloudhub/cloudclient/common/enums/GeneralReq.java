package com.cloudhub.cloudclient.common.enums;

public enum GeneralReq {
    USER("/user"),
    FILE("/storage");
    final String type;
    //constructor
    GeneralReq(String type){
        this.type=type;
    }
    public String getType(){
        return type;
    }
}
