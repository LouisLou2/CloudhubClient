package org.example.justfortest;

public class parseStr {
    public static void main(String[] args) {
        String Getstr="?userId=1&fileId=2";
        String sub=Getstr.substring(Getstr.indexOf("?")+1);
        String[] arr=sub.split("&");
        for (String a : arr) {

        }
    }
}
