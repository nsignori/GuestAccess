package com.bad.GUI;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginGUI extends GUI {
	private TextField txtUsername = new TextField();
	private PasswordField pfPass = new PasswordField();
	
	public LoginGUI() {
		super("Login");
		
		Button btnLogin = new Button("Login");
		
		gpRoot.add(new Label("Username: "), 0, 0);
		gpRoot.add(txtUsername, 1, 0);
		gpRoot.add(new Label("Password: "), 0, 1);
		gpRoot.add(pfPass, 1, 1);
		gpRoot.add(btnLogin, 0, 2, 2, 1);
		
		btnLogin.setOnMouseClicked(e -> login());
	}
	
	private void login() {
		if(Main.login(txtUsername.getText(), pfPass.getText())) {
			Main.setScene("home");
		} else {
			lblError.setText("Incorrect username or password.");
		}
	}
}
