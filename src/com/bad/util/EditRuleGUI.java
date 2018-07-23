package com.bad.util;

import java.time.LocalDate;

import com.bad.GUI.GUI;

import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class EditRuleGUI extends GUI {
	private TextField txtGuestName = new TextField();
	private TextField txtGuestNumber = new TextField();
	private DatePicker dpStartDate = new DatePicker();
	private TextField txtStartTime = new TextField();
	private DatePicker dpStopDate = new DatePicker();
	private TextField txtStopTime = new TextField();

	public EditRuleGUI(AccessRule rule) {
		super("Edit Access Rule");
		Button btnEdit = new Button("Change Rule");
		Button btnDelete = new Button("Delete Rule");

		Separator sepVert = new Separator();
		sepVert.setOrientation(Orientation.VERTICAL);
		sepVert.setValignment(VPos.CENTER);
		sepVert.setPrefHeight(80);

		txtGuestName.setPromptText("Guest Name");
		txtGuestNumber.setPromptText("Guest Number");
		txtGuestName.setText(rule.getGuest());
		txtGuestNumber.setText(rule.getGuestNumber());

		dpStartDate.setPromptText("Start Date");
		dpStopDate.setPromptText("Stop Date");
		dpStartDate.setValue(LocalDate.parse(rule.getStartTime().substring(0, rule.getStartTime().indexOf(" "))));
		dpStopDate.setValue(LocalDate.parse(rule.getEndTime().substring(0, rule.getEndTime().indexOf(" "))));

		txtStartTime.setPromptText("Start Time");
		txtStopTime.setPromptText("Stop Time");
		txtStartTime.setText(rule.getStartTime().substring(rule.getStartTime().indexOf(" ") + 1));
		txtStopTime.setText(rule.getEndTime().substring(rule.getEndTime().indexOf(" ") + 1));

		GridPane.setConstraints(sepVert, 1, 2);
		GridPane.setRowSpan(sepVert, 2);

		gpRoot.add(txtGuestName, 0, 0, 3, 1);
		gpRoot.add(txtGuestNumber, 0, 1, 3, 1);
		gpRoot.getChildren().add(sepVert);
		gpRoot.add(dpStartDate, 0, 2);
		gpRoot.add(dpStopDate, 2, 2);
		gpRoot.add(txtStartTime, 0, 3);
		gpRoot.add(txtStopTime, 2, 3);
		gpRoot.add(btnEdit, 0, 4);
		gpRoot.add(btnDelete, 3, 4);

		btnEdit.setOnMouseClicked(e -> editRule(rule));
		btnDelete.setOnMouseClicked(e -> deleteRule(rule));
	}

	private void deleteRule(AccessRule rule) {
		GuestAccessMain.deleteRule(rule);
		EmailSend.sendMail(rule.getGuestNumber() + "@mms.att.net", "ACCESS RULE DELETED. THE FOLLOWING RULE WAS CANCELLED.\n\nHello " + rule.getGuest() + ". You have been given access to " + rule.getHomeOwner() + "'s house from " + rule.getStartTime() + " to " + rule.getEndTime() + ". Use " + rule.getPin() + " to disable the alarm.");

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Rule Deleted");
		alert.setHeaderText("Access Rule Deleted");
		alert.setContentText("A text message has been sent to the guest.\nID: " + rule.getId() + "\nGuest: " + rule.getGuest() + "\nGuest Number: " + rule.getGuestNumber() + "\nPin: " + rule.getPin() + "\nStart: " + rule.getStartTime() + "\nStop: " + rule.getEndTime());
		alert.showAndWait();

		GuestAccessMain.setScene("home");
	}

	private void editRule(AccessRule org) {
		if(txtGuestName.getText().isEmpty() || txtGuestNumber.getText().isEmpty() || dpStartDate.getValue() == null || dpStopDate.getValue() == null || txtStartTime.getText().isEmpty() || txtStopTime.getText().isEmpty()) {
			lblError.setText("Make sure all fields are filled.");
		} else {
			String start =  dpStartDate.getValue().toString() + " " + (txtStartTime.getText().length() < 5 ? "0" + txtStartTime.getText() : txtStartTime.getText());
			String end =  dpStopDate.getValue().toString() + " " + (txtStopTime.getText().length() < 5 ? "0" + txtStopTime.getText() : txtStopTime.getText());
			try {
				AccessRule rule = new AccessRule(org.getId(), GuestAccessMain.getUsername(), txtGuestName.getText(), "" + Long.parseLong(txtGuestNumber.getText()), org.getPin(), start, end);
				final AccessRule commit = GuestAccessMain.editRule(rule);

				if(commit != null) {
					EmailSend.sendMail(commit.getGuestNumber() + "@mms.att.net", "ACCESS RULE CHANGED. UPDATE FOLLOWS.\n\nHello " + commit.getGuest() + ". You have been given access to " + commit.getHomeOwner() + "'s house from " + commit.getStartTime() + " to " + commit.getEndTime() + ". Use " + commit.getPin() + " to disable the alarm.");

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Rule Edited");
					alert.setHeaderText("Access Rule Edited");
					alert.setContentText("A text message has been sent to the guest.\nID: " + commit.getId() + "\nGuest: " + commit.getGuest() + "\nGuest Number: " + commit.getGuestNumber() + "\nPin: " + commit.getPin() + "\nStart: " + commit.getStartTime() + "\nStop: " + commit.getEndTime());
					alert.showAndWait();

					GuestAccessMain.setScene("home");
				} else {
					lblError.setText("Error editing access rule.");
				}
			} catch (NumberFormatException e) {
				lblError.setText("Make sure guest number is purely numeric.");
				e.printStackTrace();
			}
		}
	}
}
