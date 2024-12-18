/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Woonplaats;

import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;
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
public class WoonplaatsUpdateDelete {
    public final Label titel, naamLabel;
    public final TextField naamVeld;
    public final Button updateKnop, deleteKnop;
    public Connection con;
    
    public WoonplaatsUpdateDelete(GridPane pane, String name) 
    {
        titel = new Label("Woonplaats");
        naamLabel = new Label("Naam");
        
        naamVeld = new TextField(name);
        
        updateKnop = new Button("Bewerken");
        deleteKnop = new Button("Verwijderen");
        
        // Update methode
        updateKnop.setOnAction(event -> {
            String newName = naamVeld.getText();
            
            try {
                con = DBCPDataSource.getConnection();
                String query = 
                        "UPDATE woonplaatsen "
                        + "SET Naam = '"+newName+"' "
                        + " WHERE Naam = '"+name+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("A woonplaats was updated successfully!");
                    
                    pane.getChildren().clear();
                    new WoonplaatsOverzicht(pane);
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
            try {
                con = DBCPDataSource.getConnection();
                String query = 
                        "DELETE FROM woonplaatsen "
                        + " WHERE Naam = '"+name+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsRemoved = preparedStatement.executeUpdate();
                if (rowsRemoved > 0) {
                    System.out.println("A woonplaats was deleted successfully!");
                    
                    pane.getChildren().clear();
                    new WoonplaatsOverzicht(pane);
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
