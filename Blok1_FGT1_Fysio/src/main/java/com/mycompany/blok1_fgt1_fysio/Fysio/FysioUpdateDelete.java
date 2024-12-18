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
public class FysioUpdateDelete 
{
    public final Label titel, naamLabel, specLabel;
    public final TextField naamVeld;
    public final ComboBox specBox;
    public final Button updateKnop, deleteKnop;
    public Connection con;
    
    public FysioUpdateDelete(GridPane pane, String name, String spec) 
    {
        titel = new Label("Fysio");
        naamLabel = new Label("Naam");
        specLabel = new Label("Specialiteit");
        
        naamVeld = new TextField(name);
        specBox = new ComboBox();
        
        // Huidige eigenschappen zetten
        specBox.setValue(spec);
        
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
        
        updateKnop = new Button("Bewerken");
        deleteKnop = new Button("Verwijderen");
        
        
        // Update method
        updateKnop.setOnAction(event -> {
            String newName = naamVeld.getText();
            String newSpec = specBox.getValue().toString();
            
            try {
                con = DBCPDataSource.getConnection();
                String query = 
                        "UPDATE FysiotherapeutHeeftSpecialisatie "
                        + "SET Naam = '"+newName+"', Specialisatie = '"+newSpec+"' "
                        + " WHERE Naam = '"+name+"' AND Specialisatie = '"+spec+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("A fysiotherapeut was updated successfully!");
                    
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
        
        
        // Delete method
        deleteKnop.setOnAction(event -> {
            try {
                con = DBCPDataSource.getConnection();
                String query = 
                        "DELETE FROM FysiotherapeutHeeftSpecialisatie "
                        + " WHERE Naam = '"+name+"' AND Specialisatie = '"+spec+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsRemoved = preparedStatement.executeUpdate();
                if (rowsRemoved > 0) {
                    System.out.println("A fysiotherapeut was deleted successfully!");
                    
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

        pane.add(updateKnop, 2, 4);
        pane.add(deleteKnop, 3, 4);   
    }
}
