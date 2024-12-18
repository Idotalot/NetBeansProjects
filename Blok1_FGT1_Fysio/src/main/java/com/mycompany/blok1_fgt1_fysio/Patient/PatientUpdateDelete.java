/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Patient;

import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;
import com.mycompany.blok1_fgt1_fysio.Fysio.FysioOverzicht;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class PatientUpdateDelete 
{
    public final Label titel, naamLabel, adresLabel, woonplaatsLabel;
    public final TextField naamVeld;
    public final ComboBox adresBox, woonplaatsBox;
    public final Button updateKnop, deleteKnop;
    public Connection con;
    
    public PatientUpdateDelete(GridPane pane, String name, String adres, String woonplaats) 
    {
        titel = new Label("Patiënten");
        naamLabel = new Label("Naam");
        adresLabel = new Label("Adres");
        woonplaatsLabel = new Label("Woonplaats");

        naamVeld = new TextField(name);
        adresBox = new ComboBox();
        woonplaatsBox = new ComboBox();
        
        // Huidige eigenschappen zetten
        adresBox.setValue(adres);
        woonplaatsBox.setValue(woonplaats);
        
        // Adressen ophalen uit Adres tabel
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM Adressen");

            while(result.next()) {
                String strAdres = result.getString("Naam");

                adresBox.getItems().add(strAdres);
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
        
        // Adressen ophalen uit Woonplaats tabel
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM Woonplaatsen");

            while(result.next()) {
                String strWp = result.getString("Naam");

                woonplaatsBox.getItems().add(strWp);
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
            String newName = naamVeld.getText();
            String newAdres = adresBox.getValue().toString();
            String newWoonplaats = woonplaatsBox.getValue().toString();
            
            try {
                con = DBCPDataSource.getConnection();
                String query = 
                        "UPDATE PatientWoontOpAdresInDeWoonplaats "
                        + "SET Naam = '"+newName+"', Adres = '"+newAdres+"', Woonplaats = '"+newWoonplaats+"' "
                        + " WHERE Naam = '"+name+"' AND Adres = '"+adres+"' AND Woonplaats = '"+woonplaats+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("A patient was updated successfully!");
                    
                    pane.getChildren().clear();
                    new PatientOverzicht(pane);
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
                        "DELETE FROM PatientWoontOpAdresInDeWoonplaats "
                        + " WHERE Naam = '"+name+"' AND Adres = '"+adres+"' AND Woonplaats = '"+woonplaats+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsRemoved = preparedStatement.executeUpdate();
                if (rowsRemoved > 0) {
                    System.out.println("A patient was deleted successfully!");
                    
                    pane.getChildren().clear();
                    new PatientOverzicht(pane);
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
        pane.add(naamLabel, 2, 2);
        pane.add(adresLabel, 2, 3);
        pane.add(woonplaatsLabel, 2, 4);

        pane.add(naamVeld, 3, 2);
        pane.add(adresBox, 3, 3);
        pane.add(woonplaatsBox, 3, 4);

        pane.add(updateKnop, 2, 5);
        pane.add(deleteKnop, 3, 5);   
    }
}
