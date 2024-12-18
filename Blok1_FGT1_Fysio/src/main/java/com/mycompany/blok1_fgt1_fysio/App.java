package com.mycompany.blok1_fgt1_fysio;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        MenuBalk menuBar = new MenuBalk(root);
        VBox vbox = new VBox(menuBar, root);
        var scene = new Scene(vbox, 600, 600);
        stage.setScene(scene);
        stage.setTitle("FGT-1 Fysiotherapeut");
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}