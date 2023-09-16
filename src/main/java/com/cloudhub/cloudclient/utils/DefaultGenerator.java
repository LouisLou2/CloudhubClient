package com.cloudhub.cloudclient.utils;

public class DefaultGenerator {
    public static String RandomGravatar(String userName) {
        //设置图片大小32px
        String avatar = "http://cn.gravatar.com/avatar/" + userName + "?s=128&d=identicon&r=PG";
        return avatar;
    }
}
