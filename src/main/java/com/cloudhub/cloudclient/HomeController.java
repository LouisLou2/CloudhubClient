package com.cloudhub.cloudclient;

import com.cloudhub.cloudclient.common.enums.BaseTypeEnum;
import com.cloudhub.cloudclient.handler.*;
import com.cloudhub.cloudclient.memory.History;
import com.cloudhub.cloudclient.memory.Marker;
import com.cloudhub.cloudclient.model.storage.FileInfo;
import com.cloudhub.cloudclient.model.storage.FolderInfo;
import com.cloudhub.cloudclient.model.storage.MiniInfo;
import com.cloudhub.cloudclient.service.OfferInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HomeController implements Initializable {

    @FXML
    private ImageView closeBtn;
    @FXML
    private VBox pnItems = null;

    @FXML
    private Button filePart;

    @FXML
    private Button progressPart;

    @FXML
    private Button sharePart;

    @FXML
    private Button userInfo;

    @FXML
    private Pane pnlShare;

    @FXML
    private Pane pnlProgress;

    @FXML
    private Pane pnlFile;

    @FXML
    private Pane pnlUser;
    @FXML
    private static Label warnLabel;
    @FXML
    private static Label warnLabelClose;
    @FXML
    private StackPane stackPane;
    @FXML
    private Button btnSignout;

    //可以重复使用的右键菜单
    private static ContextMenu sharedContextMenu;
    static {
        sharedContextMenu = new ContextMenu();
        // 根据需要动态设置菜单项的行为
        MenuItem copyItem = new MenuItem("复制");
        copyItem.setOnAction(event -> {
            CopyHandler.copy(Marker.getInstance().getId(), Marker.getInstance().getBaseType());
        });
        MenuItem moveItem = new MenuItem("移动");
        moveItem.setOnAction(event -> {
            MoveHandler.move(Marker.getInstance().getId(), Marker.getInstance().getBaseType());
        });
        MenuItem deleteItem  = new MenuItem("删除");
        deleteItem.setOnAction(event -> {
            DeleteHandler.delete(Marker.getInstance().getId(), Marker.getInstance().getBaseType());
        });

        MenuItem renameItem = new MenuItem("重命名");
        renameItem.setOnAction(event -> {
            RenameHandler.rename(Marker.getInstance().getId(), Marker.getInstance().getBaseType());
        });
        MenuItem shareItem = new MenuItem("分享");
        shareItem.setOnAction(event -> {
            ShareHandler.share(Marker.getInstance().getId(), Marker.getInstance().getBaseType());
        });
        sharedContextMenu.getItems().addAll(copyItem, moveItem, deleteItem, renameItem, shareItem);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<MiniInfo> rootFileList = OfferInfo.getListBypid(0);
        updateFilePart(rootFileList);
        this.filePart.fireEvent(new ActionEvent());
    }
    public static void showWarnLabel(String warn,boolean isgood){
        warnLabel.setVisible(true);
        warnLabelClose.setVisible(true);
        if(isgood) {
            warnLabel.setStyle("-fx-text-fill: white;-fx-background-color: #42A5F5;-fx-padding: 10 10 10 10;");
        }else {
            warnLabel.setStyle("-fx-text-fill: white;-fx-background-color: #FF0000;-fx-padding: 10 10 10 10;");
        }
        warnLabel.setText(warn);
    }
    @FXML
    private void warnLabelCloseMouseEntered(MouseEvent e)
    {
        warnLabelClose.setCursor(Cursor.HAND);
    }
    @FXML
    private void warnLabelCloseMouseClicked(MouseEvent e)
    {
        warnLabelClose.setVisible(false);
        warnLabel.setVisible(false);
    }
    //这里的list存储父引用，引用的可以是文件夹，也可以是文件
    public void updateFilePart(List<MiniInfo>list){
        ConcurrentLinkedQueue<MiniInfo> filequeue = new ConcurrentLinkedQueue<>();
        pnItems.getChildren().clear();
        int num = list.size();
        Node[] nodes = new Node[num];
        int i=0;
        for(MiniInfo info:list){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
            if(info.getBaseType()== BaseTypeEnum.FILE){
                filequeue.add(info);
                continue;
            }
            try{
                nodes[i] = loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }
            //向ui控件添加id
            nodes[i].setUserData(new MiniInfo(info.getId(),info.getBaseType()));
            //获得controller
            ItemController controller = loader.getController();
            controller.setInfo((FolderInfo) info);
            //give the items some effect
            int finalI = i;
            nodes[i].setOnMouseEntered(event -> {
                nodes[finalI].setStyle("-fx-background-color : #0A0E3F");
            });
            nodes[i].setOnMouseExited(event -> {
                nodes[finalI].setStyle("-fx-background-color : #02030A");
            });
            nodes[i].setOnMouseClicked(event ->{
                if(event.getClickCount()==2){
                    Node clicked = (Node) event.getSource();
                    long id = ((MiniInfo)(clicked.getUserData())).getId();
                    History.getInstance().push(id);
                    System.out.println("double clicked");
                    History.getInstance().show();
                    updateFilePart(OfferInfo.getListBypid(id));
                }
                if(event.getButton() == MouseButton.SECONDARY) {
                    sharedContextMenu.show(nodes[finalI], event.getScreenX(), event.getScreenY());
                }
            });
            nodes[i].setOnContextMenuRequested(event -> {
                configureContextMenu((HBox) nodes[finalI]);
            });
            pnItems.getChildren().add(nodes[i]);
            ++i;
        }
        for(MiniInfo info:filequeue){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
            try{
                nodes[i] = loader.load();
            }catch (IOException e){
                e.printStackTrace();
            }
            //向ui控件添加id
            nodes[i].setUserData(new MiniInfo(info.getId(),info.getBaseType()));
            //获得controller
            ItemController controller = loader.getController();
            controller.setInfo((FileInfo) info);
            //give the items some effect
            int finalI = i;
            nodes[i].setOnMouseEntered(event -> {
                nodes[finalI].setStyle("-fx-background-color : #0A0E3F");
            });
            nodes[i].setOnMouseExited(event -> {
                nodes[finalI].setStyle("-fx-background-color : #02030A");
            });
            nodes[i].setOnMouseClicked(event ->{
                if(event.getClickCount()==2){
                    Node clicked = (Node) event.getSource();
                    long id = ((MiniInfo)(clicked.getUserData())).getId();
                    PreviewHandler.showPreview(id);
                }
                if(event.getButton() == MouseButton.SECONDARY) {
                    sharedContextMenu.show(nodes[finalI], event.getScreenX(), event.getScreenY());
                }
            });
            nodes[i].setOnContextMenuRequested(event -> {
                configureContextMenu((HBox) nodes[finalI]);
            });
            pnItems.getChildren().add(nodes[i]);
        }
        this.filePart.fireEvent(new ActionEvent());
        ++i;
    }
    @FXML
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == sharePart) {
            pnlShare.setStyle("-fx-background-color : #1620A1");
            pnlShare.toFront();
        }
        if (actionEvent.getSource() == userInfo) {
            System.out.println("userINfo");
            pnlUser.setStyle("-fx-background-color : #e5dfd5");
            pnlUser.toFront();
        }
        if (actionEvent.getSource() == filePart) {
            pnlFile.setStyle("-fx-background-color : #02030A");
            pnlFile.toFront();
        }
        if (actionEvent.getSource() == progressPart) {
            pnlProgress.setStyle("-fx-background-color : #464F67");
            pnlProgress.toFront();
        }
    }

    public void signOutToLogIn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login-ui.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login-style.css").toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    public void addNewFolder(ActionEvent actionEvent){
        // 创建新的舞台（窗口）
        Stage inputStage = new Stage();
        inputStage.setTitle("输入窗口");

        // 创建一个TextArea用于输入文本
        TextField input = new TextField();

        // 创建一个保存按钮，用于保存输入的文本
        Button inputSaveButton = new Button("保存文件夹名");
        inputSaveButton.setOnAction(event -> {
            // 获取用户输入的文本
            String folderName = input.getText();
            // 关闭输入窗口
            inputStage.close();
        });

        // 创建一个垂直布局容器来包含输入TextArea和保存按钮
        VBox inputVBox = new VBox(input, inputSaveButton);
        inputVBox.setAlignment(Pos.CENTER);
        inputVBox.setSpacing(25);
        inputSaveButton.setStyle(" -fx-background-color: rgba(53,121,148,0.8); \n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-background-radius: 5;\n" +
                "    -fx-font-size: 9;");
        // 创建一个新的场景，将输入容器添加到场景中
        Scene inputScene = new Scene(inputVBox, 170, 90);
        inputScene.getStylesheets().add("./input-style.css"); // 指定您的CSS文件路径

        // 设置输入窗口的场景
        inputStage.setScene(inputScene);
        // 显示输入窗口
        inputStage.show();

    }

    public void download(ActionEvent actionEvent) {

    }
    public void upload(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("选择文件");

        // 显示文件对话框，并获取用户选择的文件
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        //通过获取的文件构造FileInfo的基本信息
        if (selectedFile != null) {
            FileInfo fileInfo = new FileInfo();
            String absolutePath = selectedFile.getAbsolutePath();
            //fileInfo.setFileName(selectedFile.getName());
            //fileInfo.setFileAbsolutePath(absolutePath);
            //fileInfo.setFileSize(selectedFile.length());
            //fileInfo.setUserId(userService.userId);
            //fileInfo.setFolderId(filePartService.currentId);
            //Path path = Paths.get(absolutePath);
            //try{
            //    String fileType = Files.probeContentType(path);
            //    fileInfo.setFileType(fileType);
            //    String hash = HashTools.getFileHashCode(fileInfo.getFileAbsolutePath());
            //    System.out.println(hash);
            //    fileInfo.setFileId(hash);
            //}catch (IOException e){
            //    e.printStackTrace();
            //}
            //
            //Uploader.upload(fileInfo);
            //
            //this.filePartService.currentFiles.clear();
            //this.filePartService.currentFolders.clear();
            //this.filePartService.getInfo();
            //this.updateFilePart(this.filePartService.currentFiles,this.filePartService.currentFolders);

        } else {
            System.out.println("没有选择任何文件。");
        }
    }
    public void Back(ActionEvent actionEvent){
        System.out.println("back invoked");
        History.getInstance().show();
        History.getInstance().pop();
        if(History.getInstance().isEmpty()){
            History.getInstance().push(0L);
        }
        long id= History.getInstance().peek();
        updateFilePart(OfferInfo.getListBypid(id));
    }
    // 动态设置上下文菜单的行为
    private void configureContextMenu(HBox hbox) {
        Marker.getInstance().set(((MiniInfo)(hbox.getUserData())));
    }
    @FXML
    private void closeBtnMouseEntered(MouseEvent e)
    {
        closeBtn.setCursor(Cursor.HAND);
    }
    @FXML
    private void closeBtnMouseClicked(MouseEvent e)
    {
        System.exit(0);
    }
}
