package com.bad.util;

import com.bad.GUI.AlarmLoginGUI;
import com.bad.GUI.GUI;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlarmSetUp extends Application {
    private static Stage stage = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	stage = primaryStage;
        stage.setOnCloseRequest(e -> AlarmControlMain.exit());
        stage.setWidth(AlarmControlMain.getWidth());
        stage.setHeight(AlarmControlMain.getHeight());
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:res/myPlaceIcon.PNG"));
        setScene(new AlarmLoginGUI());
        stage.show();
    }

    public static void setScene(GUI scene) {
        stage.setTitle(scene.getTitle());
        stage.setScene(scene.getScene());
        stage.hide();
        stage.show();
    }
}
