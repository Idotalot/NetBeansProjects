/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Afspraak;

import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
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
public class AfspraakOverzicht {
    public final TableView<Afspraak> table;
    public final TableColumn<Afspraak, String> afspraakColumn, patientColumn, dateColumn;
    public final Label titel, listViewTitel;
    public ObservableList<Afspraak> afspraakData;
    private Connection con;

    public AfspraakOverzicht(GridPane pane) {
        // Zichtbaren objecten defineren
        table = new TableView<Afspraak>();
        afspraakColumn = new TableColumn<>("Fysiotherapeut");
        patientColumn = new TableColumn<>("Patiënt");
        dateColumn = new TableColumn<>("Datum");

        // TableColumn aan Patient class eigenschappen koppelen
        afspraakColumn.setCellValueFactory(new PropertyValueFactory<>("fysiotherapeutNaam"));
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patientNaam"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("datum"));
        
        // Kolommen toevoegen aan tabel
        table.getColumns().add(afspraakColumn);
        table.getColumns().add(patientColumn);
        table.getColumns().add(dateColumn);
        
        titel = new Label("Afspraken");
        listViewTitel = new Label("Overzicht Afspraken");

        afspraakData = FXCollections.observableArrayList();
        
        // Objecten toevoegen aan pane
        pane.add(titel, 0, 0);
        pane.add(listViewTitel, 0, 2);
        pane.add(table, 0, 3);
        
        // Ophalen gegevens uit DB
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM FysiotherapeutBehandeltPatientOpDatum");

            // Process each row in the result set
            while (result.next()) {
                String strFysio = result.getString("Fysiotherapeut");
                String strPatient = result.getString("Patiënt");
                Date ldDatum = result.getDate("Datum");
                                

                // Create a Fysio object and add it to the observable list
                Afspraak afspraak = new Afspraak(strFysio, strPatient, ldDatum);
                afspraakData.add(afspraak);
            }

            // Set the table's items to the observable list
            table.setItems(afspraakData);

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
            Afspraak selectedItem = table.getSelectionModel().getSelectedItem();

            // Ensure that an item is selected
            if (selectedItem != null) {
                // Subitems van geselecteerd item defineren
                String fysio = selectedItem.getFysiotherapeutNaam();
                String patient = selectedItem.getPatientNaam();
                Date datum = selectedItem.getDatum();

                // Subitem waardes loggen
                System.out.println("Selected Fysio: " + fysio);
                System.out.println("Selected Patient: " + patient);
                System.out.println("Selected Datum: " + datum);


                // Pane leegmaken
                pane.getChildren().clear();
                new AfspraakUpdateDelete(pane, fysio, patient, datum);
            }
        });
    }
}