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
public class FilmAddView {
    public final Label titel, naamTitel, starttijdTitel, eindtijdTitel, leeftijdsklasseTitel, lblError;
    public final TextField txtFilmNaam;
    public final ComboBox cbStarttijd, cbEindtijd, cbLeeftijdsklasse;
    public final Button aanmaakKnop;
    
    private FilmController filmController;
    
    public FilmAddView(GridPane pane) {
        // Defineren van objecten
        titel = new Label("Film Toevoegen");
        naamTitel = new Label("Naam");
        starttijdTitel = new Label("Starttijd");
        eindtijdTitel = new Label("Eindtijd");
        leeftijdsklasseTitel = new Label("Leeftijdsklasse");
        
        txtFilmNaam = new TextField();
        cbStarttijd = new ComboBox();
        cbEindtijd = new ComboBox();
        cbLeeftijdsklasse = new ComboBox();
        
        aanmaakKnop = new Button("Aanmaken");
        
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
        
        pane.add(aanmaakKnop, 0, 5);
        
        pane.add(lblError, 0, 6);
        
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
        
        aanmaakKnop.setOnAction(event -> {
            // Inputs defineren
            String naam = txtFilmNaam.getText();
            Time starttijd = Time.valueOf(cbStarttijd.getValue().toString());
            Time eindtijd = Time.valueOf(cbEindtijd.getValue().toString());
            String leeftijdsklasse = cbLeeftijdsklasse.getValue().toString();
            
            // Check of waardes niet leeg zijn.
            if (naam != null && !naam.isEmpty()) {
                // Nieuwe BenodigdheidModel aanmaken met inputs
                FilmModel filmModel = new FilmModel(naam, starttijd, eindtijd, leeftijdsklasse);

                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = filmController.CreateDelete(filmModel, "Create");

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new FilmOverzichtView(pane);
                } else {
                    // Error handling
                    lblError.setText("Er is een fout opgetreden bij het aanmaken van de film.");
                }
            } else {
                // Error handling
                lblError.setText("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
    }
}
