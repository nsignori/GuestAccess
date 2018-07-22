package com.bad.GUI;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeGUI extends GUI {
	private TableView<AccessRule> tvAccessRules = new TableView<AccessRule>();
	
	public HomeGUI() {
		super("Home");
		
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
	}
}
