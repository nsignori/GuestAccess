package com.bad.GUI;

import com.bad.util.Main;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SetUp extends Application {
    private static Stage stage = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	stage = primaryStage;
        stage.setOnCloseRequest(e -> Main.exit());
        stage.setWidth(Main.getWidth());
        stage.setHeight(Main.getHeight());
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
