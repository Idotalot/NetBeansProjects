/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.VoedselvoorkeurModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.DBCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jordy
 */
public class VoedselvoorkeurController {
    public Connection con;
    public String naam, updatedNaam, query, message;
    public Boolean result;
    
    public boolean CreateDelete(VoedselvoorkeurModel voedselvoorkeur, String requestedAction) {
        naam = voedselvoorkeur.getNaam();
        
        try {
            con = DBCPDataSource.getConnection();
            PreparedStatement preparedStatement;
            
            switch (requestedAction) {
                case "Create":
                    query = "INSERT INTO Voedselvoorkeuren (Naam) VALUES (?)";
                    
                    message = "Een nieuwe voedselvoorkeur is toegevoegd!";
                    break;
                case "Delete":
                    query = "DELETE FROM Voedselvoorkeuren WHERE Naam = ?";
                    
                    message = "Een voedselvoorkeur is verwijderd!";
                    
            }
            
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, naam);
            
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(message);

                result = true;
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            result = false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException se) {
                System.out.println(se.getMessage());
                result = false;
            }
        }
        
        return result;
    }
    
    public boolean Update(VoedselvoorkeurModel voedselvoorkeur, VoedselvoorkeurModel updatedVoedselvoorkeur) {
        naam = voedselvoorkeur.getNaam();
        updatedNaam = updatedVoedselvoorkeur.getNaam();
        
        try {
            con = DBCPDataSource.getConnection();
            PreparedStatement preparedStatement;
            
            query = "UPDATE Voedselvoorkeuren SET Naam = ? WHERE Naam = ?";
                    
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, updatedNaam);
            preparedStatement.setString(2, naam);
            
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Een voedselvoorkeur is bewerkt!");

                result = true;
            }
        } catch (SQLException se) {
            System.out.println(se.getMessage());
            result = false;
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException se) {
                System.out.println(se.getMessage());
                result = false;
            }
        }
        
        return result;
    }
    
    public ObservableList<VoedselvoorkeurModel> Read() {
        ObservableList<VoedselvoorkeurModel> voedselvoorkeuren = FXCollections.observableArrayList();
        
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM Voedselvoorkeuren");

            while(result.next()) {
                // Per resultaat een nieuw model aanmaken en toevoegen aan de lijst
                String naam = result.getString("Naam");
                
                VoedselvoorkeurModel voedselvoorkeur = new VoedselvoorkeurModel(naam);
                voedselvoorkeuren.add(voedselvoorkeur);
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
        
        return voedselvoorkeuren;
    }
}
