/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Adres;

import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;
import com.mycompany.blok1_fgt1_fysio.Specialisatie.specOverzicht;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class AdresUpdateDelete {
    public final Label titel, naamLabel;
    public final TextField naamVeld;
    public final Button updateKnop, deleteKnop;
    public Connection con;
    
    public AdresUpdateDelete(GridPane pane, String name) 
    {
        titel = new Label("Adres");
        naamLabel = new Label("Naam");
        
        naamVeld = new TextField(name);
        
        updateKnop = new Button("Bewerken");
        deleteKnop = new Button("Verwijderen");
        
        // Update methode
        updateKnop.setOnAction(event -> {
            String newName = naamVeld.getText();
            
            // Query uitvoeren
            try {
                con = DBCPDataSource.getConnection();
                String query = 
                        "UPDATE adressen "
                        + "SET Naam = '"+newName+"' "
                        + " WHERE Naam = '"+name+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("An adres was updated successfully!");
                    
                    pane.getChildren().clear();
                    new AdresOverzicht(pane);
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
        
        
        // Delete methode
        deleteKnop.setOnAction(event -> {
            // Query uitvoeren
            try {
                con = DBCPDataSource.getConnection();
                String query = 
                        "DELETE FROM adressen "
                        + " WHERE Naam = '"+name+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsRemoved = preparedStatement.executeUpdate();
                if (rowsRemoved > 0) {
                    System.out.println("An adres was deleted successfully!");
                    
                    pane.getChildren().clear();
                    new AdresOverzicht(pane);
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
        
        pane.add(naamVeld, 3, 2);

        pane.add(updateKnop, 2, 4);
        pane.add(deleteKnop, 3, 4);   

    }
}
