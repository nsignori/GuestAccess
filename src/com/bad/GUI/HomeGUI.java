package com.bad.GUI;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HomeGUI extends GUI {
	private Label lblTitle = new Label("Weight Tracker");

	public HomeGUI() {
		super("Tracker", 700, 500);
		lblTitle.setFont(new Font("Times New Roman", 24));
	}
}
