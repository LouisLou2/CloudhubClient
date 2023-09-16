package com.cloudhub.cloudclient.service;

import com.alibaba.fastjson.JSONObject;
import com.cloudhub.cloudclient.common.enums.RequestEnum;
import com.cloudhub.cloudclient.pojo.OBSDownloadReq;
import com.cloudhub.cloudclient.pojo.ProgressBarController;
import com.cloudhub.cloudclient.service.request.RequestMaker;
import com.cloudhub.cloudclient.service.request.RequestSender;
import com.obs.services.ObsClient;
import com.obs.services.model.GetObjectRequest;
import com.obs.services.model.ObsObject;
import com.obs.services.model.ProgressListener;
import com.obs.services.model.ProgressStatus;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FIleDownloadService {
    public static void Download(long id, String localFilePath){
        OBSDownloadReq req = getDownloadInfo(id);
        downloadFromInfo(req, localFilePath);
    }
    public static void DownloadWithProgress(long id, String localFilePath){
        OBSDownloadReq req = getDownloadInfo(id);
        downloadFromInfoWithProgress(req, localFilePath);
    }

    public static OBSDownloadReq getDownloadInfo(long id) {
        String url=RequestMaker.makeRequestURL(RequestEnum.DOWNLOAD, String.valueOf(id));
        String responseBody = RequestSender.getResponseBody(url);
        return JSONObject.parseObject(responseBody, OBSDownloadReq.class);
    }
    public static void downloadFromInfo(OBSDownloadReq req, String localFilePath){

        final ObsClient Client = new ObsClient(req.getAk(), req.getSk(), req.getEndpoint());
        ObsObject Obj=Client.getObject(req.getBucketname(), req.getObjectKey());
        try(InputStream input= Obj.getObjectContent();
            BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream(localFilePath)))
        {
            byte[]buffer=new byte[1024];
            int bytesRead;
            while((bytesRead=input.read(buffer))!=-1){
                output.write(buffer,0,bytesRead);
            }
            System.out.println("文件已成功保存到: " + localFilePath);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void downloadFromInfoWithProgress(OBSDownloadReq req, String localFilePath){
        final ObsClient Client = new ObsClient(req.getAk(), req.getSk(), req.getEndpoint());
        GetObjectRequest request = new GetObjectRequest(req.getBucketname(), req.getObjectKey());
        request.setProgressListener(new ProgressListener() {
            @Override
            public void progressChanged(ProgressStatus status) {
                ProgressBarController.UpdateProgress(status.getAverageSpeed(), status.getTransferPercentage());
            }
        });
        // 设置每次每下载1M数据反馈一次进度
        request.setProgressInterval(1024*1024L);
        ObsObject Obj=Client.getObject(request);
        try(InputStream input= Obj.getObjectContent();
            BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream(localFilePath)))
        {
            byte[]buffer=new byte[100];
            int bytesRead;
            while((bytesRead=input.read(buffer))!=-1){
                output.write(buffer,0,bytesRead);
            }
            System.out.println("文件已成功保存到: " + localFilePath);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
