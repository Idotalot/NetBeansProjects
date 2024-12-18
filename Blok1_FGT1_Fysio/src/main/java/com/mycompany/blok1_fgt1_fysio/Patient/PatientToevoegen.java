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
public class PatientToevoegen 
{
    public final Label titel, naamLabel, adresLabel, woonplaatsLabel;
    public final TextField naamVeld;
    public final ComboBox adresBox, woonplaatsBox;
    public final Button toevoegKnop;
    public Connection con;

    public PatientToevoegen(GridPane pane) 
    {
        titel = new Label("Patiënten");
        naamLabel = new Label("Naam");
        adresLabel = new Label("Adres");
        woonplaatsLabel = new Label("Woonplaats");

        naamVeld = new TextField("");
        adresBox = new ComboBox();
        woonplaatsBox = new ComboBox();

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

        toevoegKnop = new Button("Toevoegen");

        toevoegKnop.setOnAction(event -> {
            String naam = naamVeld.getText();
            String adres = adresBox.getValue().toString();
            String woonplaats = woonplaatsBox.getValue().toString();
            
            try {
                con = DBCPDataSource.getConnection();
                String query = "INSERT INTO PatientWoontOpAdresInDeWoonplaats (Naam, Adres, Woonplaats) "
                        + "VALUES ('"+naam+"', '"+adres+"', '"+woonplaats+"')";
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new patient was inserted successfully!");

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

        pane.add(toevoegKnop, 2, 5);
    }
}
