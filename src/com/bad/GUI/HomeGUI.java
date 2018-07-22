package com.bad.GUI;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class HomeGUI extends GUI {

	public HomeGUI() {
		super("Home");
		TableView<AccessRule> tvAccessRules = new TableView<AccessRule>();
		Button btnAddRule = new Button("Add Access Rule");

		TableColumn<AccessRule, Integer> tcId = new TableColumn<AccessRule, Integer>("Id");
		TableColumn<AccessRule, String> tcGuest = new TableColumn<AccessRule, String>("Guest");
		TableColumn<AccessRule, String> tcStartTime = new TableColumn<AccessRule, String>("Start");
		TableColumn<AccessRule, String> tcEndTime = new TableColumn<AccessRule, String>("Stop");
		tvAccessRules.getColumns().addAll(tcId, tcGuest, tcStartTime, tcEndTime);

		tcId.setCellValueFactory(new PropertyValueFactory<AccessRule, Integer>("id"));
		tcGuest.setCellValueFactory(new PropertyValueFactory<AccessRule, String>("guest"));
		tcStartTime.setCellValueFactory(new PropertyValueFactory<AccessRule, String>("startTime"));
		tcEndTime.setCellValueFactory(new PropertyValueFactory<AccessRule, String>("endTime"));

		tvAccessRules.setItems(FXCollections.observableArrayList(Main.getAccessRulesForUser()));

		gpRoot.add(tvAccessRules, 0, 0);
		gpRoot.add(btnAddRule, 0, 1);
		GridPane.setHalignment(btnAddRule, HPos.CENTER);
		
		btnAddRule.setOnMouseClicked(e -> Main.setScene("addRule"));
	}
}
