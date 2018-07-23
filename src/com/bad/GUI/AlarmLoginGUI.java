package com.bad.GUI;

import com.bad.util.AlarmControlMain;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AlarmLoginGUI extends GUI {
	private TextField txtUsername = new TextField();
	private PasswordField pfPass = new PasswordField();
	
	public AlarmLoginGUI() {
		super("Login");
		Button btnLogin = new Button("Login");
		
		txtUsername.setPromptText("Username");
		pfPass.setPromptText("Password");
		
		txtUsername.setText("badInterns"); // FIXME
		pfPass.setText("password");
		
		gpRoot.add(txtUsername, 1, 0);
		gpRoot.add(pfPass, 1, 1);
		gpRoot.add(btnLogin, 0, 2, 2, 1);
		GridPane.setHalignment(btnLogin, HPos.CENTER);
		
		btnLogin.setOnMouseClicked(e -> login());
	}
	
	private void login() {
		if(AlarmControlMain.login(txtUsername.getText().toLowerCase(), pfPass.getText())) {
			AlarmControlMain.setScene("alarmPanel");
		} else {
			lblError.setText("Incorrect username or password.");
		}
	}
}
