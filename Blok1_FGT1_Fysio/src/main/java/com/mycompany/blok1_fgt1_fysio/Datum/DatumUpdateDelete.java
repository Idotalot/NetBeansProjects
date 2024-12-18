/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Datum;

import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;
import com.mycompany.blok1_fgt1_fysio.Specialisatie.specOverzicht;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class DatumUpdateDelete {
    public final Label titel, naamLabel;
    public final DatePicker datePicker;
    public final Button updateKnop, deleteKnop;
    public Connection con;
    
    public DatumUpdateDelete(GridPane pane, LocalDate date) 
    {
        titel = new Label("Datums");
        naamLabel = new Label("Naam");
        
        datePicker = new DatePicker(date);
        
        updateKnop = new Button("Bewerken");
        deleteKnop = new Button("Verwijderen");
        
        // Update methode
        updateKnop.setOnAction(event -> {
            LocalDate newDate = datePicker.getValue();
            
            // Query uitvoeren
            try {
                con = DBCPDataSource.getConnection();
                String query = 
                        "UPDATE datums "
                        + "SET Datum = '"+newDate+"' "
                        + " WHERE Datum = '"+date+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("A specialisatie was updated successfully!");
                    
                    pane.getChildren().clear();
                    new DatumOverzicht(pane);
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
                        "DELETE FROM datums "
                        + " WHERE Datum = '"+date+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsRemoved = preparedStatement.executeUpdate();
                if (rowsRemoved > 0) {
                    System.out.println("A specialisatie was deleted successfully!");
                    
                    pane.getChildren().clear();
                    new DatumOverzicht(pane);
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
        
        pane.add(datePicker, 3, 2);

        pane.add(updateKnop, 2, 4);
        pane.add(deleteKnop, 3, 4);   

    }
}
