package com.mycompany.fxvb0401;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Aanmaken lege FlowPane
        GridPane emptyPane = new GridPane();
        
        // Declareren van een instantie
        new FXVb0401(emptyPane);
        
        var scene = new Scene(emptyPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}