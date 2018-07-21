package com.bad.GUI;

import com.bad.storage.DBConnectionManager;

import javafx.application.Application;

public class Main {
    private static DBConnectionManager db = DBConnectionManager.getInstance();

    public static void main(String[] args) {
        Application.launch(SetUp.class);
    }

    public static void setScene(String scene) {
        switch (scene) {
            case "home":
                SetUp.setScene(new HomeGUI());
                break;
            default:
                System.out.println("Case \"" + scene + "\" not known.");
        }
    }

    public static void exit() {
        db.close();
    }
}
