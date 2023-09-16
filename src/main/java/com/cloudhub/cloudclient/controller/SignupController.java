package com.cloudhub.cloudclient.controller;

import com.cloudhub.cloudclient.common.result.AppResultCode;
import com.cloudhub.cloudclient.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupController {
    @FXML
    private ImageView closeBtn;
    @FXML
    private Button signInBtn;
    @FXML
    private TextField userTextF;
    @FXML
    private PasswordField passTextF;
    @FXML
    private PasswordField passTextF2;
    @FXML
    private Label warnLabel;
    @FXML
    private Label warnLabelClose;
    @FXML
    private Label changeState;
    @FXML
    private void textFieldMouseClicked(MouseEvent e)
    {
        if( warnLabel.isVisible() )
        {
            warnLabel.setVisible(false);
            warnLabelClose.setVisible( false );
        }
    }
    @FXML
    private void signInBtnAction(ActionEvent e ) throws IOException
    {

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
    @FXML
    private void signUpFunction(ActionEvent e ) throws IOException {
        String username = userTextF.getText();
        String password = passTextF.getText();
        String password2 = passTextF2.getText();
        int resultCode = UserService.signupCheck(username, password);
        if(resultCode== AppResultCode.SignUp.SUCCESS.getCode())
        {
            warnLabel.setVisible(true);
            warnLabelClose.setVisible(true);
            warnLabel.setStyle("-fx-text-fill: white;-fx-background-color: #42A5F5;-fx-padding: 10 10 10 10;");
            warnLabel.setText("注册成功");
        }else if (resultCode==AppResultCode.SignUp.OCCUPATION_FAILURE.getCode()){
            warnLabel.setVisible(true);
            warnLabelClose.setVisible(true);
            warnLabel.setStyle("-fx-text-fill: white;-fx-background-color: #FF6978;-fx-padding: 10 10 10 10;");
            warnLabel.setText("用户名已注册");
        }

    }
    @FXML
    private void goToLogin(MouseEvent e ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../login-ui.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../login-style.css").toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle("Sign In");
        stage.setScene(scene);
        stage.show();
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
