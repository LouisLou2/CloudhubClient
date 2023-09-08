package org.example.service;

import com.alibaba.fastjson.JSONObject;
import com.obs.services.ObsClient;
import com.obs.services.model.ObjectMetadata;
import com.obs.services.model.ProgressListener;
import com.obs.services.model.ProgressStatus;
import com.obs.services.model.PutObjectRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.example.model.FileInfo;
import org.example.model.OBSUploadReq;
import org.example.utils.OPERATIONTYPE;
import org.example.utils.URLTools;
import org.example.view.ProgressBarController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Uploader {
    public static void upload(FileInfo fileInfo){
        OBSUploadReq req = getUploadInfo(fileInfo);
        if(!req.getDemand()){
            //说明与此文件完全相同的文件已经存在，此客户端不用传输字节了，并且数据库已经更新好了：该账号云端拥有了此文件
            System.out.println("文件上传完成！");
        }else{
            uploadFromInfo(req, fileInfo.getFileAbsolutePath());
        }
    }

    public static void uploadWithProgress(FileInfo fileInfo){
        OBSUploadReq req = getUploadInfo(fileInfo);
        if(!req.getDemand()){
            //说明与此文件完全相同的文件已经存在，此客户端不用传输字节了，并且数据库已经更新好了：该账号云端拥有了此文件
            System.out.println("文件上传完成！");
        }else{
            uploadFromInfoWithProgress(req, fileInfo.getFileAbsolutePath(),fileInfo.getFileSize());
        }
    }

    public static OBSUploadReq getUploadInfo(FileInfo fileInfo) {
        try(CloseableHttpClient httpclient = HttpClients.createDefault()){
            String url = URLTools.makeRequestURL(fileInfo, OPERATIONTYPE.UPLOAD);
            // 准备 HTTP GET 请求
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url)
                    .build();
            // 定义回调函数并传递给 execute 方法
            ResponseCallback callback = new ResponseCallback();
            String responseBody = httpclient.execute(httpGet, callback);
            //System.out.println("Response Body: " + responseBody);
            return JSONObject.parseObject(responseBody, OBSUploadReq.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static void uploadFromInfo(OBSUploadReq req, String localFilePath){
        //说明云端没有此文件，需要上传
        final ObsClient client=new ObsClient(req.getAk(),req.getSk(),req.getEndpoint());
        FileInputStream fis=null;
        try{
            fis=new FileInputStream(new File(localFilePath));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String bucketName=req.getBucketname();
        String objectKey=req.getObjectKey();
        PutObjectRequest obsReq=new PutObjectRequest();
        obsReq.setBucketName(req.getBucketname());
        obsReq.setObjectKey(req.getObjectKey());
        obsReq.setInput(fis);
        client.putObject(obsReq);
    }
    public static void uploadFromInfoWithProgress(OBSUploadReq req, String localFilePath,long fileSize){
        //说明云端没有此文件，需要上传
        final ObsClient client=new ObsClient(req.getAk(),req.getSk(),req.getEndpoint());
        FileInputStream fis=null;
        try{
            fis=new FileInputStream(new File(localFilePath));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PutObjectRequest obsReq=new PutObjectRequest();
        //流式上传时，必须设置content-length才能正确上传
        ObjectMetadata data=new ObjectMetadata();
        data.setContentLength(fileSize);
        obsReq.setMetadata(data);
        //设置基本信息
        obsReq.setInput(fis);
        obsReq.setBucketName(req.getBucketname());
        obsReq.setObjectKey(req.getObjectKey());
        //设置进度监听
        obsReq.setProgressListener(new ProgressListener() {
            @Override
            public void progressChanged(ProgressStatus status) {
                ProgressBarController.UpdateProgress(status.getAverageSpeed(), status.getTransferPercentage());
            }
        });
        //设置进度监听的回调间隔，单位为字节，这里是1M，后期可以根据文件大小动态调整(unfinished)
        obsReq.setProgressInterval(1024*1024L);
        //execute
        client.putObject(obsReq);
    }
}
