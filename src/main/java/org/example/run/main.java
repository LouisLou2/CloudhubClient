package org.example.run;

import org.example.model.FileInfo;
import org.example.service.Uploader;
import org.example.utils.HashTools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class main {
    public static void main(String[] args) throws IOException {
        //创建一个java.sql.Timestamp对象，表示现在的时间
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        FileInfo fileInfo = new FileInfo(123L, HashTools.getFileHashCode("./Clean.mp3"),"Clean.mp3", Files.size(Paths.get("Clean.mp3")),timestamp,"audio",0L,"./Clean.mp3");
        Uploader.uploadWithProgress(fileInfo);
    }
}
