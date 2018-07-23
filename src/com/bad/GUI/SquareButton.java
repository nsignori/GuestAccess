package com.bad.GUI;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SquareButton extends Button {
	public SquareButton(String text, TextField modify) {
		super(text);
		this.setWidth(this.getHeight());
		this.setOnAction(e -> modify.setText(modify.getText() + ((SquareButton)(e.getSource())).getText()));
	}
}
