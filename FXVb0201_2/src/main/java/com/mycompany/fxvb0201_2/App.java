package com.mycompany.fxvb0201_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
//        
//        // Aanmaken lege FlowPane
//        FlowPane emptyPane = new FlowPane();
//        
//        // Declareren van een instantie
//        new FXVb0210(emptyPane);
//        
//        var scene = new Scene(emptyPane, 640, 480);
//        stage.setScene(scene);
//        stage.show();
    }
    
    public class Startscherm 
{
    private final Menu fysioMenu, patientMenu, afspraakMenu;
    private final MenuItem viewFysio, viewSpec, viewPatient, viewAdres, viewWoonplaats, viewAfspraak, viewDatums;
            
    public Startscherm (Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        VBox vBox = new VBox(menuBar);
        
        fysioMenu = new Menu("Fysiotherapeut");
        patientMenu = new Menu("Patiënt");
        afspraakMenu = new Menu("Afspraak");
        
        viewFysio = new MenuItem("Overzicht Fysiotherapeuten");
        viewSpec = new MenuItem("Overzicht Specialisaties");
        viewPatient = new MenuItem("Overzicht Patiënten");
        viewAdres = new MenuItem("Overzicht Adressen");
        viewWoonplaats = new MenuItem("Overzicht Woonplaatsen");
        viewAfspraak = new MenuItem("Overzicht Afspraken");
        viewDatums = new MenuItem("Overzicht Datums");
        
        FlowPane emptyPane = new FlowPane();
        
        viewAdres.setOnAction(event -> {
            // Create an instance of the AdresToevoegen class
            AdresOverzicht adresOverzicht = new AdresOverzicht();

            // Replace the current pane's children with the new pane from AdresToevoegen
            pane.getChildren().clear(); // Clear current content
            pane.getChildren().add(adresOverzicht.getPane()); // Add the new pane
        });

                
        fysioMenu.getItems().addAll(viewFysio, viewSpec);
        patientMenu.getItems().addAll(viewPatient, viewAdres, viewWoonplaats);
        afspraakMenu.getItems().addAll(viewAfspraak, viewDatums);
        
        menuBar.getMenus().addAll(fysioMenu, patientMenu, afspraakMenu);
        
        pane.getChildren().addAll(menuBar);
    }
    
    public class Startscherm 
{
    private final Menu fysioMenu, patientMenu, afspraakMenu;
    private final MenuItem viewFysio, viewSpec, viewPatient, viewAdres, viewWoonplaats, viewAfspraak, viewDatums;
            
    public Startscherm (Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        VBox vBox = new VBox(menuBar);
        
        fysioMenu = new Menu("Fysiotherapeut");
        patientMenu = new Menu("Patiënt");
        afspraakMenu = new Menu("Afspraak");
        
        viewFysio = new MenuItem("Overzicht Fysiotherapeuten");
        viewSpec = new MenuItem("Overzicht Specialisaties");
        viewPatient = new MenuItem("Overzicht Patiënten");
        viewAdres = new MenuItem("Overzicht Adressen");
        viewWoonplaats = new MenuItem("Overzicht Woonplaatsen");
        viewAfspraak = new MenuItem("Overzicht Afspraken");
        viewDatums = new MenuItem("Overzicht Datums");
        
        FlowPane emptyPane = new FlowPane();
        
        viewAdres.setOnAction(event -> {
            // Create an instance of the AdresToevoegen class
            AdresOverzicht adresOverzicht = new AdresOverzicht();

            // Replace the current pane's children with the new pane from AdresToevoegen
            pane.getChildren().clear(); // Clear current content
            pane.getChildren().add(adresOverzicht.getPane()); // Add the new pane
        });

                
        fysioMenu.getItems().addAll(viewFysio, viewSpec);
        patientMenu.getItems().addAll(viewPatient, viewAdres, viewWoonplaats);
        afspraakMenu.getItems().addAll(viewAfspraak, viewDatums);
        
        menuBar.getMenus().addAll(fysioMenu, patientMenu, afspraakMenu);
        
        pane.getChildren().addAll(menuBar);
    }

    public static void main(String[] args) {
        launch();
    }

}