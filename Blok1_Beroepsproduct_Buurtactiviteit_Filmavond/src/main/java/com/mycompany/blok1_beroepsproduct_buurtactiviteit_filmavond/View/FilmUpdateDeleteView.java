/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.FilmModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.FilmController;

import java.sql.Time;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class FilmUpdateDeleteView {
    public final Label titel, naamTitel, starttijdTitel, eindtijdTitel, leeftijdsklasseTitel, lblError;
    public final TextField txtFilmNaam;
    public final ComboBox cbStarttijd, cbEindtijd, cbLeeftijdsklasse;
    public final Button bewerkKnop, verwijderKnop;
    
    private FilmController filmController;
    
    public FilmUpdateDeleteView(GridPane pane, FilmModel film) {
        // Initialize labels, text field, and button
        titel = new Label("Film Bewerken");
        naamTitel = new Label("Naam");
        starttijdTitel = new Label("Starttijd");
        eindtijdTitel = new Label("Eindtijd");
        leeftijdsklasseTitel = new Label("Leeftijdsklasse");
        
        txtFilmNaam = new TextField();
        cbStarttijd = new ComboBox();
        cbEindtijd = new ComboBox();
        cbLeeftijdsklasse = new ComboBox();
        
        bewerkKnop = new Button("Bewerken");
        verwijderKnop = new Button("Verwijderen");
        
        lblError = new Label();

        // Objecten toevoegen aan GridPane
        pane.add(titel, 0, 0);
        
        pane.add(txtFilmNaam, 1, 1);
        pane.add(naamTitel, 0, 1);
        pane.add(cbStarttijd, 1, 2);
        pane.add(starttijdTitel, 0, 2);
        pane.add(cbEindtijd, 1, 3);
        pane.add(eindtijdTitel, 0, 3);
        pane.add(cbLeeftijdsklasse, 1, 4);
        pane.add(leeftijdsklasseTitel, 0, 4);
        
        pane.add(bewerkKnop, 0, 5);
        pane.add(verwijderKnop, 1, 5);
        
        pane.add(lblError, 0, 6);
        
        String naam = film.getNaam();
        Time starttijd = film.getStarttijd();
        Time eindtijd = film.getEindtijd();
        String leeftijdsklasse = film.getLeeftijdsklasse();
        
        txtFilmNaam.setText(naam);
        cbStarttijd.setValue(starttijd);
        cbEindtijd.setValue(eindtijd);
        cbLeeftijdsklasse.setValue(leeftijdsklasse);
        
        Time starttijd1 = Time.valueOf("18:00:00");
        Time starttijd2 = Time.valueOf("20:00:00");
        Time starttijd3 = Time.valueOf("22:00:00");
        
        cbStarttijd.getItems().addAll(starttijd1, starttijd2, starttijd3);
        
        Time eindtijd1 = Time.valueOf("19:45:00");
        Time eindtijd2 = Time.valueOf("21:45:00");
        Time eindtijd3 = Time.valueOf("1:00:00");
        
        cbEindtijd.getItems().addAll(eindtijd1, eindtijd2, eindtijd3);
        
        cbLeeftijdsklasse.getItems().addAll("Minderjarig", "Meederjarig");
        
        filmController = new FilmController();
        
        bewerkKnop.setOnAction(event -> {
            // Inputs defineren
            String updatedNaam = txtFilmNaam.getText();
            Time updatedStarttijd = Time.valueOf(cbStarttijd.getValue().toString());
            Time updatedEindtijd = Time.valueOf(cbEindtijd.getValue().toString());
            String updatedLeeftijdsklasse = cbLeeftijdsklasse.getValue().toString();
            
            // Check of waardes niet leeg zijn.
            if (naam != null && !naam.isEmpty()) {
                // Nieuwe BenodigdheidModel aanmaken met inputs
                FilmModel updatedFilm = new FilmModel(updatedNaam, updatedStarttijd, updatedEindtijd, updatedLeeftijdsklasse);

                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = filmController.Update(film, updatedFilm);

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new FilmOverzichtView(pane);                    
                } else {
                    // Error handling
                    lblError.setText("Er is een fout opgetreden bij het bewerken van de film.");
                }
            } else {
                // Error handling
                lblError.setText("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
        
        verwijderKnop.setOnAction(event -> {            
            // Check of waardes niet leeg zijn.
            if (naam != null && !naam.isEmpty()) {
                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = filmController.CreateDelete(film, "Delete");

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new FilmOverzichtView(pane);                    
                } else {
                    // Error handling
                    lblError.setText("Er is een fout opgetreden bij het verwijderen van de film.");
                }
            } else {
                // Error handling
                lblError.setText("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
    }
}
