package com.cloudhub.cloudclient.handler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MaterialAlertExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Material Alert Example");

        // 创建根布局
        StackPane root = new StackPane();

        // 创建按钮
        JFXButton showAlertButton = new JFXButton("显示提醒");
        showAlertButton.getStyleClass().add("button-raised");

        // 创建弹窗内容
        Label alertLabel = new Label("这是一个Material风格的提醒弹窗。");

        // 创建弹窗
        JFXDialog dialog = new JFXDialog();
        dialog.setContent(alertLabel);
        dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);

        // 设置关闭按钮
        JFXButton closeButton = new JFXButton("关闭");
        closeButton.setOnAction(event -> dialog.close());
        VBox dialogContent = new VBox(alertLabel, closeButton);
        dialogContent.setSpacing(10.0);

        dialog.setDialogContainer(root);
        dialog.setContent(dialogContent);

        // 设置按钮点击事件
        showAlertButton.setOnAction(event -> dialog.show(root));

        // 创建场景
        Scene scene = new Scene(root, 400, 200);

        // 加载CSS样式
       // scene.getStylesheets().add(getClass().getResource("material_alert.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}