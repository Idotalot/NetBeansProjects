/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Afspraak;

import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;
import com.mycompany.blok1_fgt1_fysio.Patient.PatientOverzicht;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class AfspraakUpdateDelete 
{
    public final Label titel, fysioLabel, patientLabel, datumLabel;
    public final ComboBox fysioBox, patientBox, datumBox;
    public final Button updateKnop, deleteKnop;
    public Connection con;
    
    public AfspraakUpdateDelete(GridPane pane, String fysio, String patient, Date datum) 
    {
        titel = new Label("Afspraken");
        fysioLabel = new Label("Fysiotherapeut");
        patientLabel = new Label("Patiënt");
        datumLabel = new Label("Datum");

        fysioBox = new ComboBox();
        patientBox = new ComboBox();
        datumBox = new ComboBox();
        
        // Huidige eigenschappen zetten
        fysioBox.setValue(fysio);
        patientBox.setValue(patient);
        datumBox.setValue(datum);
        
        // Fysiotherapeuten ophalen uit Fysiotherapeuten tabel
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM Fysiotherapeuten");

            while(result.next()) {
                String strAdres = result.getString("Naam");

                fysioBox.getItems().add(strAdres);
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
            ResultSet result = stat.executeQuery("SELECT * FROM Patiënten");

            while(result.next()) {
                String strWp = result.getString("Naam");

                patientBox.getItems().add(strWp);
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
        
        updateKnop = new Button("Bewerken");
        deleteKnop = new Button("Verwijderen");
        
        
        // Update method
        updateKnop.setOnAction(event -> {
            String newFysio = fysioBox.getValue().toString();
            String newPatient = patientBox.getValue().toString();
            LocalDate newDatum = LocalDate.parse(datumBox.getValue().toString());
            
            try {
                con = DBCPDataSource.getConnection();
                String query = 
                        "UPDATE FysiotherapeutBehandeltPatientOpDatum "
                        + "SET Fysiotherapeut = '"+newFysio+"', Patiënt = '"+newPatient+"', Datum = '"+newDatum+"' "
                        + " WHERE Fysiotherapeut = '"+fysio+"' AND Patiënt = '"+patient+"' AND Datum = '"+datum+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("An 'afspraak' was updated successfully!");
                    
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
        
        
        // Delete method
        deleteKnop.setOnAction(event -> {
            try {
                con = DBCPDataSource.getConnection();
                String query = 
                        "DELETE FROM FysiotherapeutBehandeltPatientOpDatum "
                        + " WHERE Fysiotherapeut = '"+fysio+"' AND Patiënt = '"+patient+"' AND Datum = '"+datum+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsRemoved = preparedStatement.executeUpdate();
                if (rowsRemoved > 0) {
                    System.out.println("An 'afspraak' was deleted successfully!");
                    
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

        pane.add(updateKnop, 2, 5);
        pane.add(deleteKnop, 3, 5);   
    }
}