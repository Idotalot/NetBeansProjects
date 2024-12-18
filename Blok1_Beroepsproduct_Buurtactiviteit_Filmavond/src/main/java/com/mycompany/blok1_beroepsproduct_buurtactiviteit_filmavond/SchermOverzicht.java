/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

/**
 *
 * @author jordy
 */
public class SchermOverzicht {
    
    private ListView<String> lvOverzicht;
    private Connection con;
    
    public SchermOverzicht(Pane p) {
        lvOverzicht = new ListView();
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM bestemmingen");
            
            while(result.next()) {
                String strStad = result.getString("stad");
                lvOverzicht.getItems().add(strStad);
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
        lvOverzicht.relocate(10, 10);
        p.getChildren().add(lvOverzicht);
    }
}
