/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Afspraak;

import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class AfspraakToevoegen 
{
    public final Label titel, fysioLabel, patientLabel, datumLabel;
    public final ComboBox fysioBox, patientBox, datumBox;
    public final Button toevoegKnop;
    public Connection con;

    public AfspraakToevoegen(GridPane pane) 
    {
        titel = new Label("Afspraken");
        fysioLabel = new Label("Fysiotherapeut");
        patientLabel = new Label("Patiënt");
        datumLabel = new Label("Datum");

        fysioBox = new ComboBox();
        patientBox = new ComboBox();
        datumBox = new ComboBox();
        
        // Fysiotherapeuten ophalen uit Fysiotherapeuten tabel
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM FysiotherapeutHeeftSpecialisatie");

            while(result.next()) {
                String strNaam = result.getString("Naam");

                fysioBox.getItems().add(strNaam);
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
        
        // Patiënten ophalen uit Patiënten tabel
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM PatientWoontOpAdresInDeWoonplaats");

            while(result.next()) {
                String strNaam = result.getString("Naam");

                patientBox.getItems().add(strNaam);
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
        
        // Patiënten ophalen uit Patiënten tabel
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM Datums");

            while(result.next()) {
                String strDatum = result.getString("Datum");

                datumBox.getItems().add(strDatum);
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
        
        toevoegKnop = new Button("Toevoegen");
        
        
        // Update method
        toevoegKnop.setOnAction(event -> {
            String fysio = fysioBox.getValue().toString();
            String patient = patientBox.getValue().toString();
            LocalDate datum = LocalDate.parse(datumBox.getValue().toString());
            
            try {
                con = DBCPDataSource.getConnection();
                String query = "INSERT INTO FysiotherapeutBehandeltPatientOpDatum (Fysiotherapeut, Patiënt, Datum) "
                        + "VALUES ('"+fysio+"', '"+patient+"', '"+datum+"')";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("A new 'afspraak' was added successfully!");
                    
                    pane.getChildren().clear();
                    new AfspraakOverzicht(pane);
                }
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            } finally {
                try {
                    if (con != null) con.close();
                } catch (SQLException se) {
                    System.out.println(se.getMessage());
                }
            } 
        });
        
        pane.add(titel, 2, 0);
        pane.add(fysioLabel, 2, 2);
        pane.add(patientLabel, 2, 3);
        pane.add(datumLabel, 2, 4);

        pane.add(fysioBox, 3, 2);
        pane.add(patientBox, 3, 3);
        pane.add(datumBox, 3, 4);

        pane.add(toevoegKnop, 2, 5);
    }
}
