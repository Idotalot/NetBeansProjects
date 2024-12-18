///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.blok1_fgt1_fysio;
//
////import com.mycompany.blok1_fgt1_fysio.Datum.DatumOverzicht;
//
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuBar;
//import javafx.scene.control.MenuItem;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.VBox;
//
//import com.mycompany.blok1_fgt1_fysio.Adres.AdresOverzicht;
//
///**
// *
// * @author jordy
// */
//public class Startscherm 
//{
//    private final Menu fysioMenu, patientMenu, afspraakMenu;
//    private final MenuItem viewFysio, viewSpec, viewPatient, viewAdres, viewWoonplaats, viewAfspraak, viewDatums;
//            
//    public Startscherm (FlowPane pane) {
//        MenuBar menuBar = new MenuBar();
//        VBox vBox = new VBox(menuBar);
//        
//        fysioMenu = new Menu("Fysiotherapeut");
//        patientMenu = new Menu("Patiënt");
//        afspraakMenu = new Menu("Afspraak");
//        
//        viewFysio = new MenuItem("Overzicht Fysiotherapeuten");
//        viewSpec = new MenuItem("Overzicht Specialisaties");
//        viewPatient = new MenuItem("Overzicht Patiënten");
//        viewAdres = new MenuItem("Overzicht Adressen");
//        viewWoonplaats = new MenuItem("Overzicht Woonplaatsen");
//        viewAfspraak = new MenuItem("Overzicht Afspraken");
//        viewDatums = new MenuItem("Overzicht Datums");
//        
//        FlowPane emptyPane = new FlowPane();
//        
//        viewAdres.setOnAction(event -> {
//            // Create an instance of the AdresToevoegen class
//            AdresOverzicht adresOverzicht = new AdresOverzicht();
//
//            // Replace the current pane's children with the new pane from AdresToevoegen
//            pane.getChildren().clear(); // Clear current content
//            pane.getChildren().add(adresOverzicht.getPane()); // Add the new pane
//        });
//
//                
//        fysioMenu.getItems().addAll(viewFysio, viewSpec);
//        patientMenu.getItems().addAll(viewPatient, viewAdres, viewWoonplaats);
//        afspraakMenu.getItems().addAll(viewAfspraak, viewDatums);
//        
//        menuBar.getMenus().addAll(fysioMenu, patientMenu, afspraakMenu);
//        
//        pane.getChildren().addAll(menuBar);
//    }
//}
