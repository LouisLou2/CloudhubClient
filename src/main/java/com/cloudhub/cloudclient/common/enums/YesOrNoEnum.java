package com.cloudhub.cloudclient.common.enums;

public enum YesOrNoEnum{
    /**
     * no
     */
    NO(0, "no"),

    /**
     * yes
     */
    YES(1, "yes");

    final Integer code;
    final String msg;
    //constructor
    YesOrNoEnum(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }
    //getters
    public Integer getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
    public static YesOrNoEnum getEnumByCode(int code){
        for(YesOrNoEnum e:YesOrNoEnum.values()){
            if(e.getCode()==code){
                return e;
            }
        }
        return null;
    }
    YesOrNoEnum(int code){
        this.code=code;
        this.msg="";
    }
}
