/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Datum;

import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class DatumOverzicht {
    public final Label titel, listViewTitel;
    public final ListView<String> listView;
    public ObservableList<String> lvItems;
    private Connection con;
    
    public DatumOverzicht(GridPane pane) {
        titel = new Label("Datums");
        listViewTitel = new Label("Overzicht Datums");

        listView = new ListView<>();
        
        // Ophalen gegevens uit DB
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM datums");
            
            while(result.next()) {
                String strNaam = result.getString("Datum");
                
                listView.getItems().add(strNaam);
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        } finally {
            try {
                con.close();
            } catch(SQLException se) {
                System.out.println(se.getMessage());
            }
        }

        // Objecten toevoegen aan pane
        pane.add(titel, 0, 0);
        pane.add(listViewTitel, 0, 2);
        pane.add(listView, 0, 3);
        
        // Selecteren van gegevens uit lijst
        listView.setOnMouseClicked(event -> {
            LocalDate selectedItem = LocalDate.parse(listView.getSelectionModel().getSelectedItem());
            if (selectedItem != null) {
                // Split the selected item into two parts
                pane.getChildren().clear();
                new DatumUpdateDelete(pane, selectedItem);
            }
        });
    }
}
