package org.example.service;

import com.alibaba.fastjson.JSONObject;
import com.obs.services.ObsClient;
import com.obs.services.model.GetObjectRequest;
import com.obs.services.model.ObsObject;
import com.obs.services.model.ProgressListener;
import com.obs.services.model.ProgressStatus;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.example.model.BaseFileInfo;
import org.example.model.OBSDownloadReq;
import org.example.utils.OPERATIONTYPE;
import org.example.utils.URLTools;
import org.example.view.ProgressBarController;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Downloader {
    private String filepath2="";

    public static void Download(BaseFileInfo bfileinfo, String localFilePath){
        OBSDownloadReq req = getDownloadInfo(bfileinfo);
        downloadFromInfo(req, localFilePath);
    }
    public static void DownloadWithProgress(BaseFileInfo bfileinfo, String localFilePath){
        OBSDownloadReq req = getDownloadInfo(bfileinfo);
        downloadFromInfoWithProgress(req, localFilePath);
    }

    public static OBSDownloadReq getDownloadInfo(BaseFileInfo bfileinfo) {
        try(CloseableHttpClient httpclient = HttpClients.createDefault()){
            String url = URLTools.makeRequestURL(bfileinfo, OPERATIONTYPE.DOWNLOAD);
            // 准备 HTTP GET 请求
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url)
                    .build();
            // 定义回调函数并传递给 execute 方法
            ResponseCallback callback = new ResponseCallback();
            String responseBody = httpclient.execute(httpGet, callback);
            //System.out.println("Response Body: " + responseBody);
            return JSONObject.parseObject(responseBody, OBSDownloadReq.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
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