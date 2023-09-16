package com.cloudhub.cloudclient;

import cn.hutool.core.date.DateUtil;
import com.cloudhub.cloudclient.common.enums.FIleTypeMapper;
import com.cloudhub.cloudclient.model.storage.FileInfo;
import com.cloudhub.cloudclient.model.storage.FolderInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    @FXML
    private HBox itemC;
    @FXML
    private ImageView imageView;

    @FXML
    private Label fileName;

    @FXML
    private Label createTime;

    @FXML
    private Label fileType;

    @FXML
    private Label fileSize;

    @FXML
    private Button button;

    public void setInfo(FileInfo file){
        this.fileName.setText(file.getName());
        this.createTime.setText(DateUtil.date(file.getTimeStamp()).toString());
        this.fileSize.setText(file.getSize()+"");
        this.fileType.setText(FIleTypeMapper.getInstance().get(file.getType()));
    }
    public void setInfo(FolderInfo folder){
        this.fileName.setText(folder.getName());
        this.fileType.setText("Directory");
        this.createTime.setText(DateUtil.date(folder.getTimeStamp()).toString());
        this.fileSize.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
