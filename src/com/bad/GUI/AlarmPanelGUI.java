package com.bad.GUI;

import com.bad.util.AlarmControlMain;

import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AlarmPanelGUI extends GUI {
	private Button btnArm = new Button("Arm");
	private TextField txtPin = new TextField();
	private GridPane gpNums = new GridPane();

	public AlarmPanelGUI() {
		super("Alarm Panel");
		Button btnOne = new SquareButton("1", txtPin);
		Button btnTwo = new SquareButton("2", txtPin);
		Button btnThree = new SquareButton("3", txtPin);
		Button btnFour = new SquareButton("4", txtPin);
		Button btnFive = new SquareButton("5", txtPin);
		Button btnSix = new SquareButton("6", txtPin);
		Button btnSeven = new SquareButton("7", txtPin);
		Button btnEight = new SquareButton("8", txtPin);
		Button btnNine = new SquareButton("9", txtPin);
		Button btnZero = new SquareButton("0", txtPin);
		gpNums.setHgap(15);
		gpNums.setVgap(15);
		gpNums.add(btnOne, 0, 0);
		gpNums.add(btnTwo, 1, 0);
		gpNums.add(btnThree, 2, 0);
		gpNums.add(btnFour, 0, 1);
		gpNums.add(btnFive, 1, 1);
		gpNums.add(btnSix, 2, 1);
		gpNums.add(btnSeven, 0, 2);
		gpNums.add(btnEight, 1, 2);
		gpNums.add(btnNine, 2, 2);
		gpNums.add(btnZero, 1, 3);

		gpRoot.add(btnArm, 0, 0);
		GridPane.setHalignment(btnArm, HPos.CENTER);

		txtPin.setVisible(false);
		txtPin.setPrefWidth(80);
		txtPin.setEditable(false);
		gpRoot.add(txtPin, 0, 0);
		gpNums.setVisible(false);
		gpRoot.add(gpNums, 0, 1);

		btnArm.setOnMouseClicked(e -> arm());

		txtPin.textProperty().addListener(e -> checkPin());
	}

	private void checkPin() {
		if(txtPin.getText().length() == 4) {
			int id = AlarmControlMain.checkPin(txtPin.getText());
			txtPin.setText("");
			
			if(id != -1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Pin Correct");
				alert.setHeaderText("Pin Verified");
				alert.setContentText("Pin has been verified against access rule #" + id + ".");
				alert.showAndWait();
				
				AlarmControlMain.setScene("alarmPanel");
			}
		}
	}

	private void arm() {
		btnArm.setVisible(false);
		gpNums.setVisible(true);
		txtPin.setVisible(true);
	}
}
