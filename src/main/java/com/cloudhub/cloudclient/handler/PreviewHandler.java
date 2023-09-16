package com.cloudhub.cloudclient.handler;// Java Program to create a WebView and load
// a website and display it on the stage

import com.cloudhub.cloudclient.common.enums.RequestEnum;
import com.cloudhub.cloudclient.service.request.RequestMaker;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class PreviewHandler{
    private static WebView w;
    private static WebEngine engine;
    public static void showPreview(long id) {
        String url = RequestMaker.makeRequestURL(RequestEnum.PREVIEW, String.valueOf(id));
        Stage stage = new Stage();
        try {

            // set title for the stage
            stage.setTitle("文件预览");

            // create a webview object
            w = new WebView();

            // get the web engine
            engine = w.getEngine();

            // load a website
            engine.load(url);

            // create a scene
            Scene scene = new Scene(w, w.getPrefWidth(),
                    w.getPrefHeight());
            scene.addEventHandler(KeyEvent.KEY_PRESSED, (keyEvent) -> {
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    // 停止加载网页
                    simulateButtonClick();
                    w.getEngine().load(null);
                }
            });
            // set the scene
            stage.setScene(scene);

            stage.show();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    // 模拟点击按钮的方法
    private static void simulateButtonClick() {
        // 在网页中执行 JavaScript 以触发按钮点击事件
        engine.executeScript("document.querySelector('button[data-plyr=\"play\"]').click();");
    }
}
