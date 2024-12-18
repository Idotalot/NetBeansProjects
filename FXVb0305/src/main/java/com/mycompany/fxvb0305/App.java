package com.mycompany.fxvb0305;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Aanmaken FlowPane
        Pane emptyPane = new Pane();
        new FXVb0305(emptyPane);
        
        var scene = new Scene(emptyPane, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}