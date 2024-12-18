package com.mycompany.blok1_fgt1_fysio.Specialisatie;

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
public class specToevoegen {
    public final Label titel, naamLabel;
    public final TextField naamVeld;
    public final Button toevoegKnop;
    public Connection con;
    
    public specToevoegen(GridPane pane) {
        titel = new Label("Specialisatie");
        naamLabel = new Label("Naam");
        
        naamVeld = new TextField("");
        
        toevoegKnop = new Button("Toevoegen");
        
        toevoegKnop.setOnAction(event -> {
            try {
                con = DBCPDataSource.getConnection();
                String query = "INSERT INTO specialisaties (Naam) VALUES ('"+naamVeld.getText()+"')";
                PreparedStatement preparedStatement = con.prepareStatement(query);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new specialisatie was inserted successfully!");
                    
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

        pane.add(toevoegKnop, 2, 4);
    }
}
