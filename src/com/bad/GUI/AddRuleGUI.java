package com.bad.GUI;

import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddRuleGUI extends GUI {
	private TextField txtGuestName = new TextField();
	private TextField txtGuestNumber = new TextField();
	private DatePicker dpStartDate = new DatePicker();
	private TextField txtStartTime = new TextField();
	private DatePicker dpStopDate = new DatePicker();
	private TextField txtStopTime = new TextField();

	public AddRuleGUI() {
		super("Add Access Rule");
		Button btnAdd = new Button("Add Rule");

		Separator sepVert = new Separator();
		sepVert.setOrientation(Orientation.VERTICAL);
		sepVert.setValignment(VPos.CENTER);
		sepVert.setPrefHeight(80);

		txtGuestName.setPromptText("Guest Name");
		txtGuestNumber.setPromptText("Guest Number");

		dpStartDate.setPromptText("Start Date");
		dpStopDate.setPromptText("Stop Date");

		txtStartTime.setPromptText("Start Time");
		txtStopTime.setPromptText("Stop Time");

		GridPane.setConstraints(sepVert, 1, 2);
		GridPane.setRowSpan(sepVert, 2);

		gpRoot.add(txtGuestName, 0, 0, 3, 1);
		gpRoot.add(txtGuestNumber, 0, 1, 3, 1);
		gpRoot.getChildren().add(sepVert);
		gpRoot.add(dpStartDate, 0, 2);
		gpRoot.add(dpStopDate, 2, 2);
		gpRoot.add(txtStartTime, 0, 3);
		gpRoot.add(txtStopTime, 2, 3);
		gpRoot.add(btnAdd, 0, 4, 3, 1);

		btnAdd.setOnMouseClicked(e -> addRule());
	}

	private void addRule() {
		if(txtGuestName.getText().isEmpty() || txtGuestNumber.getText().isEmpty() || dpStartDate.getValue() == null || dpStopDate.getValue() == null || txtStartTime.getText().isEmpty() || txtStopTime.getText().isEmpty()) {
			lblError.setText("Make sure all fields are filled.");
		} else {
			String start =  dpStartDate.getValue().toString() + " " + (txtStartTime.getText().length() < 5 ? "0" + txtStartTime.getText() : txtStartTime.getText());
			String end =  dpStopDate.getValue().toString() + " " + (txtStopTime.getText().length() < 5 ? "0" + txtStopTime.getText() : txtStopTime.getText());
			try {
				AccessRule rule = new AccessRule(Main.getUsername(), txtGuestName.getText(), "" + Long.parseLong(txtGuestNumber.getText()), start, end);
				rule = Main.addRule(rule);

				if(rule != null) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Rule Added");
					alert.setHeaderText("Access Rule Added");
					alert.setContentText("ID: " + rule.getId() + "\nGuest: " + rule.getGuest() + "\nGuest Number: " + rule.getGuestNumber() + "\nPin: " + rule.getPin() + "\nStart: " + rule.getStartTime() + "\nStop: " + rule.getEndTime());
					alert.showAndWait();
				} else {
					lblError.setText("Error adding access rule.");
				}
			} catch (NumberFormatException e) {
				lblError.setText("Make sure guest number is purely numeric.");
				e.printStackTrace();
			}
		}
	}
}
