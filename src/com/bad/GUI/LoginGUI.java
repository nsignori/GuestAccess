package com.bad.GUI;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginGUI extends GUI {
	private TextField txtUsername = new TextField();
	private PasswordField pfPass = new PasswordField();
	
	public LoginGUI() {
		super("Login");
		Button btnLogin = new Button("Login");
		
		txtUsername.setPromptText("Username");
		pfPass.setPromptText("Password");
		
		gpRoot.add(txtUsername, 1, 0);
		gpRoot.add(pfPass, 1, 1);
		gpRoot.add(btnLogin, 0, 2, 2, 1);
		
		btnLogin.setOnMouseClicked(e -> login());
	}
	
	private void login() {
		if(Main.login(txtUsername.getText().toLowerCase(), pfPass.getText())) {
			Main.setScene("home");
		} else {
			lblError.setText("Incorrect username or password.");
		}
	}
}
