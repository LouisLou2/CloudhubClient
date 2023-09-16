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

public class LoginController {
	@FXML
	private ImageView closeBtn;
	@FXML
	private Button signInBtn;
	@FXML
	private TextField userTextF;
	@FXML
	private PasswordField passTextF;
	@FXML
	private Label warnLabel;
	@FXML
	private Label warnLabelClose;
	@FXML
	private Label changeState;

	private Stage loginStage=null;
	private boolean state=true;

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
	@FXML
	private void signInBtnAction(ActionEvent e ) throws IOException
	{
		logInFunction(e);
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
	private void textFieldMouseClicked(MouseEvent e)
	{
		if( warnLabel.isVisible() )
		{
			warnLabel.setVisible(false);
			warnLabelClose.setVisible( false );
		}
	}
	public void changeState(){
		//false代表注册，true代表登入
		state = state ?false:true;
		if(state){
			this.signInBtn.setText("Login in");
			this.changeState.setText("Please sign up");
		}else {
			this.signInBtn.setText("Sign up");
			this.changeState.setText("Please login in");
		}
		this.userTextF.clear();
		this.passTextF.clear();
	}
	public void openSignUpPage(MouseEvent e) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../signup-ui.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../signup-style.css").toExternalForm());
		scene.setFill(Color.TRANSPARENT);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setTitle("Sign Up");
		stage.setScene(scene);
		stage.show();
	}
	private void logInFunction(ActionEvent e ) throws IOException {
		String username = userTextF.getText();
		String password = passTextF.getText();
		int resultCode = UserService.login(username,password);
		if(resultCode== AppResultCode.SignIn.SUCCESS.getCode())
		{
			warnLabel.setVisible(true);
			warnLabelClose.setVisible(true);
			warnLabel.setStyle("-fx-text-fill: white;-fx-background-color: #42A5F5;-fx-padding: 10 10 10 10;");
			warnLabel.setText("登入成功");
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Home.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}else if (resultCode==AppResultCode.SignIn.FAILURE.getCode()){
			warnLabel.setVisible(true);
			warnLabelClose.setVisible(true);
			warnLabel.setStyle("-fx-text-fill: white;-fx-background-color: #FF6978;-fx-padding: 10 10 10 10;");
			warnLabel.setText("账号或密码错误");
		}else if(resultCode ==AppResultCode.SignIn.NONEXISTENT.getCode()){
			warnLabel.setVisible(true);
			warnLabelClose.setVisible(true);
			warnLabel.setStyle("-fx-text-fill: white;-fx-background-color: #FF6978;-fx-padding: 10 10 10 10;");
			warnLabel.setText("用户未注册");
		}
	}
}
