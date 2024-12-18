/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Fysio;

import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;
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
public class FysioToevoegen 
{
    public final Label titel, naamLabel, specLabel;
    public final TextField naamVeld;
    public final ComboBox specBox;
    public final Button toevoegKnop;
    public Connection con;
    
    public FysioToevoegen(GridPane pane) {
        titel = new Label("Fysio");
        naamLabel = new Label("Naam");
        specLabel = new Label("Specialiteit");
        
        naamVeld = new TextField("");
        specBox = new ComboBox();
        
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM Specialisaties");
            
            while(result.next()) {
                String strSpec = result.getString("Naam");
                
                specBox.getItems().add(strSpec);
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
            
            try {
                con = DBCPDataSource.getConnection();
                String query = "INSERT INTO FysiotherapeutHeeftSpecialisatie (Naam, Specialisatie) VALUES ('"+naam+"', '"+specBox.getValue().toString()+"')";
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new fysiotherapeut was inserted successfully!");
                    
                    pane.getChildren().clear();
                    new FysioOverzicht(pane);
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
        pane.add(specLabel, 2, 3);
        
        pane.add(naamVeld, 3, 2);
        pane.add(specBox, 3, 3);

        pane.add(toevoegKnop, 2, 4);
    }
}
