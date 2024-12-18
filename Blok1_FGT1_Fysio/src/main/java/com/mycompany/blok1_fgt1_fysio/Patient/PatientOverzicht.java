/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Patient;

import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;
import com.mycompany.blok1_fgt1_fysio.Fysio.Fysio;
import com.mycompany.blok1_fgt1_fysio.Fysio.FysioUpdateDelete;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class PatientOverzicht {
    public final TableView<Patient> table;
    public final TableColumn<Patient, String> nameColumn, adresColumn, woonplaatsColumn;
    public final Label titel, listViewTitel;
    public ObservableList<Patient> patientData;
    private Connection con;

    public PatientOverzicht(GridPane pane) {
        // Zichtbaren objecten defineren
        table = new TableView<Patient>();
        nameColumn = new TableColumn<>("Naam");
        adresColumn = new TableColumn<>("Adres");
        woonplaatsColumn = new TableColumn<>("Woonplaats");

        // TableColumn aan Patient class eigenschappen koppelen
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
        adresColumn.setCellValueFactory(new PropertyValueFactory<>("adres"));
        woonplaatsColumn.setCellValueFactory(new PropertyValueFactory<>("woonplaats"));
        
        // Kolommen toevoegen aan tabel
        table.getColumns().add(nameColumn);
        table.getColumns().add(adresColumn);
        table.getColumns().add(woonplaatsColumn);
        
        titel = new Label("Patiënten");
        listViewTitel = new Label("Overzicht Patiënten");

        patientData = FXCollections.observableArrayList();
        
        // Objecten toevoegen aan pane
        pane.add(titel, 0, 0);
        pane.add(listViewTitel, 0, 2);
        pane.add(table, 0, 3);
        
        // Ophalen gegevens uit DB
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM PatientWoontOpAdresInDeWoonplaats");

            // Process each row in the result set
            while (result.next()) {
                String strNaam = result.getString("Naam");
                String strAdres = result.getString("Adres");
                String strWoonplaats = result.getString("Woonplaats");
                                

                // Create a Fysio object and add it to the observable list
                Patient patient = new Patient(strNaam, strAdres, strWoonplaats);
                patientData.add(patient);
            }

            // Set the table's items to the observable list
            table.setItems(patientData);

        } catch (SQLException se) {
            System.out.println("Database error: " + se.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
                System.out.println("Error closing connection: " + se.getMessage());
            }
        }

        
        
        // Selecteren van gegevens uit lijst
        table.setOnMouseClicked(event -> {
            // Geselecteerde item defineren
            Patient selectedItem = table.getSelectionModel().getSelectedItem();

            // Ensure that an item is selected
            if (selectedItem != null) {
                // Subitems van geselecteerd item defineren
                String name = selectedItem.getNaam();
                String adres = selectedItem.getAdres();
                String woonplaats = selectedItem.getWoonplaats();

                // Subitem waardes loggen
                System.out.println("Selected Name: " + name);
                System.out.println("Selected Adres: " + adres);
                System.out.println("Selected Woonplaats: " + woonplaats);


                // Pane leegmaken
                pane.getChildren().clear();
                new PatientUpdateDelete(pane, name, adres, woonplaats);
            }
        });
    }
}
