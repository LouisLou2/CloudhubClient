package org.example.justfortest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientDown3 {
    public static void main(String[] args) {
        String serverUrl = "http://localhost:8080/downloadFromObs?user_id=123&filename=afile.jpg"; // 替换为实际的服务器 URL
        try {
            // 创建 URL 对象并打开连接
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 GET
            connection.setRequestMethod("GET");

            // 获取响应状态码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 打开输入流以获取响应数据
                InputStream inputStream = connection.getInputStream();

                // 保存响应数据到本地文件，这里假设是保存到当前目录下的 "downloadedFile.jpg"
                String localFilePath = "downloadedFile.json";
                FileOutputStream outputStream = new FileOutputStream(localFilePath);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.close();

                // 关闭输入流
                inputStream.close();

                System.out.println("文件已下载到: " + localFilePath);
            } else {
                System.out.println("HTTP请求失败，状态码: " + responseCode);
            }

            // 关闭连接
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

