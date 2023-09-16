package com.cloudhub.cloudclient.service.request;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

public class RequestSender {
    public static String getResponseBody(String url){
        try(CloseableHttpClient httpclient = HttpClients.createDefault()){
            // 准备 HTTP GET 请求
            ClassicHttpRequest httpGet = ClassicRequestBuilder.get(url)
                    .build();
            // 定义回调函数并传递给 execute 方法
            ResponseCallback callback = new ResponseCallback();
            String responseBody = httpclient.execute(httpGet, callback);
            //System.out.println("Response Body: " + responseBody);
            return responseBody;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
