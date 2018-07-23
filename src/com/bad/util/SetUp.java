package com.bad.util;

import com.bad.GUI.GUI;
import com.bad.GUI.LoginGUI;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SetUp extends Application {
    private static Stage stage = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	stage = primaryStage;
        stage.setOnCloseRequest(e -> GuestAccessMain.exit());
        stage.setWidth(GuestAccessMain.getWidth());
        stage.setHeight(GuestAccessMain.getHeight());
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:res/myPlaceIcon.PNG"));
        setScene(new LoginGUI());
        stage.show();
    }

    public static void setScene(GUI scene) {
        stage.setTitle(scene.getTitle());
        stage.setScene(scene.getScene());
        stage.hide();
        stage.show();
    }
}
