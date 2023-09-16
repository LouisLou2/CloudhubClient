package com.cloudhub.cloudclient.service;

import com.alibaba.fastjson.JSONObject;
import com.cloudhub.cloudclient.common.enums.RequestEnum;
import com.cloudhub.cloudclient.model.storage.FileInfo;
import com.cloudhub.cloudclient.pojo.OBSUploadReq;
import com.cloudhub.cloudclient.pojo.ProgressBarController;
import com.cloudhub.cloudclient.service.request.RequestMaker;
import com.cloudhub.cloudclient.service.request.RequestSender;
import com.obs.services.ObsClient;
import com.obs.services.model.ObjectMetadata;
import com.obs.services.model.ProgressListener;
import com.obs.services.model.ProgressStatus;
import com.obs.services.model.PutObjectRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileUploadService {
    public static void upload(FileInfo fileInfo,String localFilePath){
        OBSUploadReq req = getUploadInfo(fileInfo);
        if(!req.getDemand()){
            //说明与此文件完全相同的文件已经存在，此客户端不用传输字节了，并且数据库已经更新好了：该账号云端拥有了此文件
            //!!!!!!!!!!!!!!!!!!!!!!需要进度条拉满！弹窗提示！
            System.out.println("文件上传完成！");
        }else{
            uploadFromInfo(req, localFilePath);
        }
    }

    public static void uploadWithProgress(FileInfo fileInfo,String localFilePath){
        OBSUploadReq req = getUploadInfo(fileInfo);
        if(!req.getDemand()){
            //说明与此文件完全相同的文件已经存在，此客户端不用传输字节了，并且数据库已经更新好了：该账号云端拥有了此文件
            //!!!!!!!!!!!!!!!!!!!!!!需要进度条拉满！弹窗提示！
            System.out.println("文件上传完成！");
        }else{
            uploadFromInfoWithProgress(req, localFilePath,fileInfo.getSize());
        }
    }

    public static OBSUploadReq getUploadInfo(FileInfo fileInfo) {
            String url= RequestMaker.makeRequestURL(RequestEnum.UPLOAD, String.valueOf(fileInfo.getUserId()),fileInfo.getName(),fileInfo.getHash(),String.valueOf(fileInfo.getSize()),String.valueOf(fileInfo.getType()),String.valueOf(fileInfo.getPid()));
            String responseBody = RequestSender.getResponseBody(url);
            return JSONObject.parseObject(responseBody, OBSUploadReq.class);
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
        obsReq.setBucketName(req.getBucketname());
        obsReq.setObjectKey(req.getObjectKey());
        ObjectMetadata data=new ObjectMetadata();
        data.setContentLength(fileSize);
        obsReq.setMetadata(data);
        obsReq.setInput(fis);

        obsReq.setProgressListener(new ProgressListener() {
            @Override
            public void progressChanged(ProgressStatus status) {
                //!!!!!!!!!!!!!!!这里应该是更新进度条的代码
                ProgressBarController.UpdateProgress(status.getAverageSpeed(), status.getTransferPercentage());
            }
        });
        obsReq.setProgressInterval(1024*1024L);
        client.putObject(obsReq);
    }
}
