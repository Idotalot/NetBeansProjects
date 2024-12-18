/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BenodigdheidModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.BenodigdheidUpdateDeleteView;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.BenodigdheidController;

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
public class BenodigdheidOverzichtView {
    public final TableView<BenodigdheidModel> table;
    public final TableColumn<BenodigdheidModel, String> nameColumn;
    public final Label titel, tableViewTitel;
    public ObservableList<BenodigdheidModel> benodigdhedenData;

    private BenodigdheidController benodigdheidController;
    
    public BenodigdheidOverzichtView(GridPane pane) {
        // Initialize the table and column
        table = new TableView<BenodigdheidModel>();
        nameColumn = new TableColumn<>("Naam");

        // Set up the column to retrieve "naam" property from BenodigdheidModel
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
        
        // Add the column to the table
        table.getColumns().add(nameColumn);

        // Labels
        titel = new Label("Benodigdheden");
        tableViewTitel = new Label("Overzicht Benodigdheden");

        // Objecten toevoegen aan GridPane
        pane.add(titel, 0, 0);
        pane.add(tableViewTitel, 0, 1);
        pane.add(table, 0, 2);

        // Get data from the controller
        benodigdheidController = new BenodigdheidController();
        benodigdhedenData = benodigdheidController.Read();

        // Set the data to the table
        table.setItems(benodigdhedenData);
        
        table.setOnMouseClicked(event -> {
            BenodigdheidModel selectedItem = table.getSelectionModel().getSelectedItem();
            
            if (selectedItem != null) {
                pane.getChildren().clear();
                new BenodigdheidUpdateDeleteView(pane, selectedItem);
            }
        });
    }
}
