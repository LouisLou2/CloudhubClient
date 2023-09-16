package com.cloudhub.cloudclient;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PaneAbout implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button giteeLink;

    @FXML
    private Button githubLink;

    @FXML
    private Button bilibiliLink;

    @FXML
    void openGithub(ActionEvent event) {
        getHostService().showDocument(githubLink.getText());
    }


    @FXML
    void openGitee(ActionEvent event) {
        getHostService().showDocument(giteeLink.getText());
    }


    @FXML
    void openQQ(ActionEvent event) {
        getHostService().showDocument("http://wpa.qq.com/msgrd?v=3&uin=9670453&site=qq&menu=yes");
    }

    @FXML
    void addGroup(ActionEvent event) {
        getHostService().showDocument("https://qm.qq.com/cgi-bin/qm/qr?k=NiUwB2ugZew91ErCWC7uBw7CaEcBb2_o&jump_from=webapi");
    }

    @FXML
    void openBilibili(ActionEvent event) {
        getHostService().showDocument(bilibiliLink.getText());
    }

    private HostServices getHostService(){
        return  (HostServices) githubLink.getScene().getUserData();
    }

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
