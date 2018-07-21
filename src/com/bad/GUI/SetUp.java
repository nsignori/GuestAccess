package com.bad.GUI;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SetUp extends Application {
    private static Stage stage = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setOnCloseRequest(e -> Main.exit());
        setScene(new LoginGUI());
        stage.show();
    }

    public static void setScene(GUI scene) {
        double width = Screen.getPrimary().getVisualBounds().getWidth(), height = Screen.getPrimary().getVisualBounds().getHeight();
        stage.setTitle(scene.getTitle());
        stage.setScene(scene.getScene());
        stage.setWidth(scene.getWidth());
        stage.setHeight(scene.getHeight());
        stage.setX((width - scene.getWidth()) / 2);
        stage.setY((height - scene.getHeight()) / 2);
    }
}
