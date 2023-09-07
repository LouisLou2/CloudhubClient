package org.example.utils;

import org.example.model.BaseFileInfo;
import org.example.model.FileInfo;

public class URLTools {
    //利用BaseFileInfo类构造url
    public static String makeRequestURL(BaseFileInfo bfileinfo, OPERATIONTYPE operationtype) {
        return Constant.ADDRESS + operationtype.get() + bfileinfo.toString();
    }
    public static String makeRequestURL(BaseFileInfo bfileinfo1, BaseFileInfo bfileinfo2,OPERATIONTYPE operationtype) {
        return Constant.ADDRESS + operationtype.get() + bfileinfo1.toString() + "&" + bfileinfo2.toString();
    }
    //利用FileInfo类构造url
    public static String makeRequestURL(FileInfo fileinfo, OPERATIONTYPE operationtype) {
        return Constant.ADDRESS + operationtype.get() +fileinfo.toString();
    }
}
