package com.bad.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GUI {
    private int width = 0;
    private int height = 0;
    private String title = "";

    protected GridPane gpRoot = new GridPane();
    protected Label lblError = new Label("");

    public GUI(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        gpRoot.setAlignment(Pos.CENTER);
        gpRoot.setHgap(5);
        gpRoot.setVgap(20);
    }

    public Scene getScene() {
        return new Scene(gpRoot);
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
