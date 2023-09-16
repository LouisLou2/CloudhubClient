package com.cloudhub.cloudclient.pojo;


import com.cloudhub.cloudclient.utils.SizeTools;

public class ProgressBarController {
    public static void UpdateProgress(double averageSpeed, int CurrentProgress){
        System.out.println("\33[36;4m"+"当前速度："+ SizeTools.formatFileSize(averageSpeed));
        System.out.println("\33[36;4m"+"当前进度："+ CurrentProgress+"%");
    }
}
