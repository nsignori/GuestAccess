package com.bad.GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GUI {
    private String title = "";

    protected GridPane gpRoot = new GridPane();
    protected Label lblError = new Label("");

    public GUI(String title) {
        this.title = title;

        gpRoot.setAlignment(Pos.CENTER);
        gpRoot.setHgap(5);
        gpRoot.setVgap(20);
        
        lblError.setTextFill(Color.RED);
    }

    public Scene getScene() {
    	VBox vb = new VBox();
    	vb.setBackground(new Background(new BackgroundFill(Color.rgb(119, 189, 67), null, null)));
    	vb.setAlignment(Pos.TOP_CENTER);
    	try {
    		ImageView im = new ImageView(new Image(new FileInputStream("C:\\Users\\Nick\\Documents\\Workspace\\GuestAccess\\res\\myPlaceLogo.PNG")));
			im.setFitWidth(Main.getWidth() * .5);
			im.setPreserveRatio(true);
    		vb.getChildren().addAll(im, gpRoot, lblError);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        return new Scene(vb);
    }

    public String getTitle() {
        return title;
    }
}
