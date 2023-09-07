package org.example.justfortest;

import com.obs.services.ObsClient;

import java.io.File;

public class UploadFile{
    public static void main(String[] args) throws Exception {
        String endPoint = "https://obs.cn-south-1.myhuaweicloud.com";
        String ak = "LCIQZYFIA3DTO4FEYFDP";
        String sk = "UAUIsRs4vDViWsqUZpCBIU5mf6JVondkwDzkUU1o";
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);

        // localfile为待上传的本地文件路径，需要指定到具体的文件名
        obsClient.putObject("cloudhub", "music/Clean", new File("Clean.mp3"));

        // localfile2 为待上传的本地文件路径，需要指定到具体的文件名
        //PutObjectRequest request = new PutObjectRequest();
        //request.setBucketName("bucketname");
        //request.setObjectKey("objectkey2");
        //request.setFile(new File("localfile2"));
        //obsClient.putObject(request);
    }
}
