/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BuurtbewonerModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.BuurtbewonerController;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class BuurtbewonerOverzichtView {
    public final TableView<BuurtbewonerModel> table;
    public final TableColumn<BuurtbewonerModel, String> nameColumn, voedselvoorkeurColumn, leeftijdsklasseColumn, benodigdheidColumn;
    public final Label titel, tableViewTitel;
    public ObservableList<BuurtbewonerModel> buurtbewonerData;

    private BuurtbewonerController buurtbewonerController;
    
    public BuurtbewonerOverzichtView(GridPane pane) {
        // Initialize the table and column
        table = new TableView<BuurtbewonerModel>();
        nameColumn = new TableColumn<>("Naam");
        voedselvoorkeurColumn = new TableColumn<>("Voedselvoorkeur");
        leeftijdsklasseColumn = new TableColumn<>("Leeftijdsklasse");
        benodigdheidColumn = new TableColumn<>("Benodigdheid");

        // Kolommen opzetten om waardes op te halen uit Model
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
        voedselvoorkeurColumn.setCellValueFactory(new PropertyValueFactory<>("voedselvoorkeur"));
        leeftijdsklasseColumn.setCellValueFactory(new PropertyValueFactory<>("leeftijdsklasse"));
        benodigdheidColumn.setCellValueFactory(new PropertyValueFactory<>("benodigdheid"));

        voedselvoorkeurColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getVoedselvoorkeur().getNaam()));

        // Benodigdheid column should display the name of the benodigdheid
        benodigdheidColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getBenodigdheid().getNaam()));
        
        // Kolommen toevoegen aan tabel
        table.getColumns().add(nameColumn);
        table.getColumns().add(voedselvoorkeurColumn);
        table.getColumns().add(leeftijdsklasseColumn);
        table.getColumns().add(benodigdheidColumn);


        // Labels
        titel = new Label("Buurtbewoners");
        tableViewTitel = new Label("Overzicht Buurtbewoners");

        // Objecten toevoegen aan GridPane
        pane.add(titel, 0, 0);
        pane.add(tableViewTitel, 0, 1);
        pane.add(table, 0, 2);

        // Data ophalen uit de controller
        buurtbewonerController = new BuurtbewonerController();
        buurtbewonerData = buurtbewonerController.Read();

        // Set the data to the table
        table.setItems(buurtbewonerData);
        
        table.setOnMouseClicked(event -> {
            BuurtbewonerModel selectedItem = table.getSelectionModel().getSelectedItem();
            
            if (selectedItem != null) {
                pane.getChildren().clear();
                new BuurtbewonerUpdateDeleteView(pane, selectedItem);
            }
        });
    }
}
