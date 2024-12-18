/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BenodigdheidModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BuurtbewonerModel;
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
public class BuurtbewonerController {
    public Connection con;
    public String naam, updatedNaam, leeftijdsklasse, updatedLeeftijdsklasse, query, message;
    public BenodigdheidModel benodigdheid, updatedBenodigdheid;
    public VoedselvoorkeurModel voedselvoorkeur, updatedVoedselvoorkeur;
    public Boolean result;
    
    public boolean CreateDelete(BuurtbewonerModel buurtbewoner, String requestedAction) {
        naam = buurtbewoner.getNaam();
        voedselvoorkeur = buurtbewoner.getVoedselvoorkeur();
        leeftijdsklasse = buurtbewoner.getLeeftijdsklasse();
        benodigdheid = buurtbewoner.getBenodigdheid();
        
        try {
            con = DBCPDataSource.getConnection();
            PreparedStatement preparedStatement; 
            
            switch (requestedAction) {
                case "Create":
                    query = "INSERT INTO Buurtbewoners (Naam, Voedselvoorkeur, Leeftijdsklasse, Benodigdheid) VALUES (?, ?, ?, ?)";
                    
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, naam);
                    preparedStatement.setString(2, voedselvoorkeur.getNaam());
                    preparedStatement.setString(3, leeftijdsklasse);
                    preparedStatement.setString(4, benodigdheid.getNaam());
                    
                    message = "Een nieuwe buurtbewoner is toegevoegd!";
                    break;
                case "Delete":
                    query = "DELETE FROM Buurtbewoners WHERE Naam = ?";
                    
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, naam);
                    
                    message = "Een buurtbewoner is verwijderd!";
                    break;
                    
                default:
                    throw new IllegalArgumentException("Invalid action: " + requestedAction); 
            }
            
                     
            System.out.println(preparedStatement);

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
    
    public boolean Update(BuurtbewonerModel buurtbewoner, BuurtbewonerModel updatedBuurtbewoner) {        
        
        naam = buurtbewoner.getNaam();
        voedselvoorkeur = buurtbewoner.getVoedselvoorkeur();
        leeftijdsklasse = buurtbewoner.getLeeftijdsklasse();
        benodigdheid = buurtbewoner.getBenodigdheid();
        
        updatedNaam = updatedBuurtbewoner.getNaam();
        updatedVoedselvoorkeur = updatedBuurtbewoner.getVoedselvoorkeur();
        updatedLeeftijdsklasse = updatedBuurtbewoner.getLeeftijdsklasse();
        updatedBenodigdheid = updatedBuurtbewoner.getBenodigdheid();
        
        try {
            con = DBCPDataSource.getConnection();
            
            query = "UPDATE Buurtbewoners SET Naam = ?, Voedselvoorkeur = ?, Leeftijdsklasse = ?, Benodigdheid = ? WHERE Naam = ?";
                    
            PreparedStatement preparedStatement = con.prepareStatement(query);          
            preparedStatement.setString(1, updatedNaam);
            preparedStatement.setString(2, updatedVoedselvoorkeur.getNaam());
            preparedStatement.setString(3, updatedLeeftijdsklasse);
            preparedStatement.setString(4, updatedBenodigdheid.getNaam());
            preparedStatement.setString(5, naam);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Een buurtbewoner is bewerkt!");

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
    
    public ObservableList<BuurtbewonerModel> Read() {
        ObservableList<BuurtbewonerModel> buurtbewoners = FXCollections.observableArrayList();
        
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM Buurtbewoners");

            while(result.next()) {
                // Per resultaat een nieuw model aanmaken en toevoegen aan de lijst
                String naam = result.getString("Naam");
                String voedselvoorkeurNaam = result.getString("Voedselvoorkeur");
                String leeftijdsklasse = result.getString("Leeftijdsklasse");
                String benodigdheidNaam = result.getString("Benodigdheid");
                
                VoedselvoorkeurModel voedselvoorkeur = new VoedselvoorkeurModel(voedselvoorkeurNaam);
                BenodigdheidModel benodigdheid = new BenodigdheidModel(benodigdheidNaam);
                
                BuurtbewonerModel buurtbewoner = new BuurtbewonerModel(naam, voedselvoorkeur, leeftijdsklasse, benodigdheid);
                buurtbewoners.add(buurtbewoner);
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
        
        return buurtbewoners;
    }
}
