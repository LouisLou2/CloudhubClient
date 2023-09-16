package com.cloudhub.cloudclient.handler;

import com.cloudhub.cloudclient.HomeController;
import com.cloudhub.cloudclient.common.enums.BaseTypeEnum;
import com.cloudhub.cloudclient.common.result.AppResultCode;
import com.cloudhub.cloudclient.service.FileUpdateService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RenameHandler {
    public static void rename(long id, int baseType) {
        Stage inputStage = new Stage();
        inputStage.setTitle("输入窗口");

        // 创建一个TextArea用于输入文本
        TextField input = new TextField();

        // 创建一个保存按钮，用于保存输入的文本
        Button inputSaveButton = new Button("保存文件名");
        inputSaveButton.setOnAction(e -> {
            // 获取用户输入的文本
            String fileName = input.getText();
            int code = FileUpdateService.rename(BaseTypeEnum.FILE, id, fileName);
            boolean isgood = code == AppResultCode.FILE_FOLDER_UPDATE.SUCCESS.getCode();
            String msg = AppResultCode.FILE_FOLDER_UPDATE.getMsgByCode(code);
            HomeController.showWarnLabel(msg, isgood);
            // 关闭输入窗口
            inputStage.close();
        });
    }
    public static EventHandler<ActionEvent> MenuRenameHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            // 获取事件源（被点击的）
            Node clicked = (Node) event.getSource();
            long id = (long) clicked.getUserData();
            Stage inputStage = new Stage();
            inputStage.setTitle("输入窗口");

            // 创建一个TextArea用于输入文本
            TextField input = new TextField();

            // 创建一个保存按钮，用于保存输入的文本
            Button inputSaveButton = new Button("保存文件名");
            inputSaveButton.setOnAction(e -> {
                // 获取用户输入的文本
                String fileName = input.getText();
                int code = FileUpdateService.rename(BaseTypeEnum.FOLDER, id, fileName);
                boolean isgood = code == AppResultCode.FILE_FOLDER_UPDATE.SUCCESS.getCode();
                String msg = AppResultCode.FILE_FOLDER_UPDATE.getMsgByCode(code);
                HomeController.showWarnLabel(msg, isgood);
                // 关闭输入窗口
                inputStage.close();
            });
        }
    };
}
