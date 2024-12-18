package com.mycompany.blok1_fgt1_fysio.Woonplaats;
import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class WoonplaatsOverzicht {
    public final Label titel, listViewTitel;
    public final ListView<String> listView;
    public ObservableList<String> lvItems;
    private Connection con;
    
    public WoonplaatsOverzicht(GridPane pane) 
    {
        titel = new Label("Woonplaatsen");
        listViewTitel = new Label("Overzicht Woonplaatsen");

        listView = new ListView<>();
        
        // Ophalen gegevens uit DB
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM Woonplaatsen");
            
            while(result.next()) {
                String strNaam = result.getString("Naam");
                
                listView.getItems().add(strNaam);
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

        // Objecten toevoegen aan pane
        pane.add(titel, 0, 0);
        pane.add(listViewTitel, 0, 2);
        pane.add(listView, 0, 3);
        
        // Selecteren van gegevens uit lijst
        listView.setOnMouseClicked(event -> {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // Split the selected item into two parts
                pane.getChildren().clear();
                new WoonplaatsUpdateDelete(pane, selectedItem);
            }
        });
    }

    // Helper method to format the row like two columns
    private String formatRow(String firstColumn, String secondColumn) {
        // Create a formatted string to simulate two columns with tab spaces or padding
        return String.format("%-20s %s", firstColumn, secondColumn);
    }
}
