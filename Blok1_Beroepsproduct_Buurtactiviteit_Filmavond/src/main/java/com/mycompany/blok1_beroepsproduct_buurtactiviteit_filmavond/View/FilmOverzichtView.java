/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.FilmModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.FilmController;

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
public class FilmOverzichtView {
    public final TableView<FilmModel> table;
    public final TableColumn<FilmModel, String> nameColumn, starttijdColumn, eindtijdColumn, leeftijdsklasseColumn;
    public final Label titel, tableViewTitel;
    public ObservableList<FilmModel> filmData;

    private FilmController filmController;
    
    public FilmOverzichtView(GridPane pane) {
        // Initialize the table and column
        table = new TableView<FilmModel>();
        nameColumn = new TableColumn<>("Naam");
        starttijdColumn = new TableColumn<>("Starttijd");
        eindtijdColumn = new TableColumn<>("Eindtijd");
        leeftijdsklasseColumn = new TableColumn<>("Leeftijdsklasse");

        // Kolommen opzetten om waardes op te halen uit Model
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
        starttijdColumn.setCellValueFactory(new PropertyValueFactory<>("starttijd"));
        eindtijdColumn.setCellValueFactory(new PropertyValueFactory<>("eindtijd"));
        leeftijdsklasseColumn.setCellValueFactory(new PropertyValueFactory<>("leeftijdsklasse"));
        
        // Kolommen toevoegen aan tabel
        table.getColumns().add(nameColumn);
        table.getColumns().add(starttijdColumn);
        table.getColumns().add(eindtijdColumn);
        table.getColumns().add(leeftijdsklasseColumn);

        // Labels
        titel = new Label("Films");
        tableViewTitel = new Label("Overzicht Films");

        // Objecten toevoegen aan GridPane
        pane.add(titel, 0, 0);
        pane.add(tableViewTitel, 0, 1);
        pane.add(table, 0, 2);

        // Data ophalen uit de controller
        filmController = new FilmController();
        filmData = filmController.Read();

        // Set the data to the table
        table.setItems(filmData);
        
        // Film tabel soorteren op starttijd (Vroegste eerst)
        starttijdColumn.setSortType(TableColumn.SortType.ASCENDING);
        table.getSortOrder().add(starttijdColumn);
        
        table.setOnMouseClicked(event -> {
            FilmModel selectedItem = table.getSelectionModel().getSelectedItem();
            
            if (selectedItem != null) {
                pane.getChildren().clear();
                new FilmUpdateDeleteView(pane, selectedItem);
            }
        });
    }
}
