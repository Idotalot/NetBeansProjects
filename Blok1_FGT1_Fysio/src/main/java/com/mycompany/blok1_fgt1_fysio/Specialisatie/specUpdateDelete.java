package com.mycompany.blok1_fgt1_fysio.Specialisatie;

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
 * @author jordy
 */

public class specUpdateDelete 
{
    public final Label titel, naamLabel;
    public final TextField naamVeld;
    public final Button updateKnop, deleteKnop;
    public Connection con;
    
    public specUpdateDelete(GridPane pane, String name) 
    {
        titel = new Label("Specialisatie");
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
                        "UPDATE specialisaties "
                        + "SET Naam = '"+newName+"' "
                        + " WHERE Naam = '"+name+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("A specialisatie was updated successfully!");
                    
                    pane.getChildren().clear();
                    new specOverzicht(pane);
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
                        "DELETE FROM specialisaties "
                        + " WHERE Naam = '"+name+"'";
                
                System.out.println(query);
                
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsRemoved = preparedStatement.executeUpdate();
                if (rowsRemoved > 0) {
                    System.out.println("A specialisatie was deleted successfully!");
                    
                    pane.getChildren().clear();
                    new specOverzicht(pane);
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
