package com.mycompany.blok1_fgt1_fysio.Fysio;

import com.mycompany.blok1_fgt1_fysio.DBCPDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author jordy
 */
public class FysioOverzicht {
    public final TableView<Fysio> table;
    public final TableColumn<Fysio, String> nameColumn, specColumn;
    public final Label titel, listViewTitel;
    public ObservableList<Fysio> fysioData;
    private Connection con;

    public FysioOverzicht(GridPane pane) {
        // Initialize TableView and Columns
        table = new TableView<Fysio>();
        nameColumn = new TableColumn<>("Naam");
        specColumn = new TableColumn<>("Specialisatie");

        // Bind TableColumn to Fysio class properties
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("naam"));
        specColumn.setCellValueFactory(new PropertyValueFactory<>("specialisatie"));
        
        // Add columns to the table
        table.getColumns().add(nameColumn);
        table.getColumns().add(specColumn);
        
        titel = new Label("Fysio");
        listViewTitel = new Label("Overzicht Fysiotherapeuten");

        fysioData = FXCollections.observableArrayList();

        // Add objects to the pane
        pane.add(titel, 0, 0);
        pane.add(listViewTitel, 0, 2);
        pane.add(table, 0, 3);
        
        // Fetch data from the database
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM FysiotherapeutHeeftSpecialisatie");

            // Process each row in the result set
            while (result.next()) {
                String strNaam = result.getString("Naam");
                String strSpec = result.getString("Specialisatie");

                // Create a Fysio object and add it to the observable list
                Fysio fysio = new Fysio(strNaam, strSpec);
                fysioData.add(fysio);
            }

            // Set the table's items to the observable list
            table.setItems(fysioData);

        } catch (SQLException se) {
            System.out.println("Database error: " + se.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
                System.out.println("Error closing connection: " + se.getMessage());
            }
        }

        table.setOnMouseClicked(event -> {
            // Get the selected item, which is of type Fysio, not String
            Fysio selectedItem = table.getSelectionModel().getSelectedItem();

            // Ensure that an item is selected
            if (selectedItem != null) {
                // Access the name and specialization directly from the Fysio object
                String name = selectedItem.getNaam();
                String spec = selectedItem.getSpecialisatie();

                // Log the selected values to the console
                System.out.println("Selected Name: " + name);
                System.out.println("Selected Specialization: " + spec);

                // Clear the pane and pass the name and specialization to the FysioUpdateDelete constructor
                pane.getChildren().clear();
                new FysioUpdateDelete(pane, name, spec);
            }
        });
    }

    // Helper method to format the row like two columns (not needed for TableView, but kept here)
    private String formatRow(String firstColumn, String secondColumn) {
        // Create a formatted string to simulate two columns with tab spaces or padding
        return String.format("%-20s %s", firstColumn, secondColumn);
    }
}


