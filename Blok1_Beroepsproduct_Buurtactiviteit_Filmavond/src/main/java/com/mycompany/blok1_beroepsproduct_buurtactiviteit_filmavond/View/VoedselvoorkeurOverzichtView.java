/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.VoedselvoorkeurModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.VoedselvoorkeurUpdateDelete;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.VoedselvoorkeurController;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class VoedselvoorkeurOverzichtView {
    public final TableView<VoedselvoorkeurModel> table;
    public final TableColumn<VoedselvoorkeurModel, String> nameColumn;
    public final Label titel, tableViewTitel;
    public ObservableList<VoedselvoorkeurModel> voedselvoorkeurData;

    private VoedselvoorkeurController voedselvoorkeurController;
    
    public VoedselvoorkeurOverzichtView(GridPane pane) {
        // Initialize the table and column
        table = new TableView<VoedselvoorkeurModel>();
        nameColumn = new TableColumn<>("Naam");

        // Kolom opzetten om "naam" op te halen uit model
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
        
        // Kolom toevoegen aan tabel
        table.getColumns().add(nameColumn);

        // Labels
        titel = new Label("Voedselvoorkeuren");
        tableViewTitel = new Label("Overzicht Voedselvoorkeuren");

        // Add title and table to the grid pane
        pane.add(titel, 0, 0);
        pane.add(tableViewTitel, 0, 1);
        pane.add(table, 0, 2);

        // Get data from the controller
        voedselvoorkeurController = new VoedselvoorkeurController();
        voedselvoorkeurData = voedselvoorkeurController.Read();

        // Set the data to the table
        table.setItems(voedselvoorkeurData);
        
        table.setOnMouseClicked(event -> {
            VoedselvoorkeurModel selectedItem = table.getSelectionModel().getSelectedItem();
            
            if (selectedItem != null) {
                pane.getChildren().clear();
                new VoedselvoorkeurUpdateDelete(pane, selectedItem);
            }
        });
    }
}
