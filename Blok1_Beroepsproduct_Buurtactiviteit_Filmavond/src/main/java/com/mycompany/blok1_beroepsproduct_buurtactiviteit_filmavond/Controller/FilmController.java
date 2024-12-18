/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.FilmModel;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.DBCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jordy
 */
public class FilmController {
    public Connection con;
    public String naam, updatedNaam, leeftijdsklasse, updatedLeeftijdsklasse, query, message;
    public Time starttijd, updatedStarttijd, eindtijd, updatedEindtijd;
    public Boolean result;
    
    public boolean CreateDelete(FilmModel film, String requestedAction) {
        naam = film.getNaam();
        starttijd = film.getStarttijd();
        eindtijd = film.getEindtijd();
        leeftijdsklasse = film.getLeeftijdsklasse();
        
        // Connectie maken met DB en uitvoeren van query
        try {
            con = DBCPDataSource.getConnection();
            PreparedStatement preparedStatement;

            switch (requestedAction) {
                case "Create":
                    query = "INSERT INTO Films (Naam, Starttijd, Eindtijd, Leeftijdsklasse) VALUES (?, ?, ?, ?)";
                    
                    preparedStatement = con.prepareStatement(query);            
                    preparedStatement.setString(1, naam);
                    preparedStatement.setTime(2, starttijd);
                    preparedStatement.setTime(3, eindtijd);
                    preparedStatement.setString(4, leeftijdsklasse);
                    
                    message = "Een nieuwe film is toegevoegd!";
                    break;

                case "Delete":
                    query = "DELETE FROM Films WHERE Naam = ?";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, naam);
                    message = "Een film is verwijderd!";
                    break;

                default:
                    throw new IllegalArgumentException("Invalid action: " + requestedAction);
            }

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
    
    public boolean Update(FilmModel film, FilmModel updatedFilm) {        
        
        naam = film.getNaam();
        starttijd = film.getStarttijd();
        eindtijd = film.getEindtijd();
        leeftijdsklasse = film.getLeeftijdsklasse();
        
        updatedNaam = updatedFilm.getNaam();
        updatedStarttijd = updatedFilm.getStarttijd();
        updatedEindtijd = updatedFilm.getEindtijd();
        updatedLeeftijdsklasse = updatedFilm.getLeeftijdsklasse();
        
        // Connectie maken met DB en uitvoeren van query
        try {
            con = DBCPDataSource.getConnection();
            
            query = "UPDATE Films SET Naam = ?, Starttijd = ?, Eindtijd = ?, Leeftijdsklasse = ? WHERE Naam = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, updatedNaam);
            preparedStatement.setTime(2, updatedStarttijd);
            preparedStatement.setTime(3, updatedEindtijd);
            preparedStatement.setString(4, updatedLeeftijdsklasse);
            preparedStatement.setString(5, naam);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Een film is bewerkt!");

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
    
    public ObservableList<FilmModel> Read() {
        ObservableList<FilmModel> films = FXCollections.observableArrayList();
        
        try {
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM Films");

            while(result.next()) {
                // Per resultaat een nieuw model aanmaken en toevoegen aan de lijst
                String naam = result.getString("Naam");
                Time starttijd = result.getTime("Starttijd");
                Time eindtijd = result.getTime("Eindtijd");
                String leeftijdsklasse = result.getString("Leeftijdsklasse");
                
                FilmModel film = new FilmModel(naam, starttijd, eindtijd, leeftijdsklasse);
                films.add(film);
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
        
        return films;
    }
}
