package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.BenodigdheidController;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.VoedselvoorkeurModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.VoedselvoorkeurOverzichtView;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.VoedselvoorkeurController;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BenodigdheidModel;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class VoedselvoorkeurAddView {
    public final Label titel, naamTitel, lblError;
    public final TextField voedselvoorkeurText;
    public final Button aanmaakKnop;
    
    private VoedselvoorkeurController voedselvoorkeurController;

    public VoedselvoorkeurAddView(GridPane pane) {
        // Initialize labels, text field, and button
        titel = new Label("Voedselvoorkeur Toevoegen");
        naamTitel = new Label("Naam");        
        voedselvoorkeurText = new TextField();        
        aanmaakKnop = new Button("Aanmaken");
        lblError = new Label();

        // Objecten toevoegen aan GridPane
        pane.add(titel, 0, 0);
        pane.add(naamTitel, 0, 1);
        pane.add(voedselvoorkeurText, 1, 1);
        pane.add(aanmaakKnop, 0, 2);
        pane.add(lblError, 0, 3);

        // Controller defineren
        voedselvoorkeurController = new VoedselvoorkeurController();
        
        aanmaakKnop.setOnAction(event -> {
            // Inputs defineren
            String naam = voedselvoorkeurText.getText();
            
            // Check of waardes niet leeg zijn.
            if (naam != null && !naam.isEmpty()) {
                // Nieuwe Model aanmaken met inputs
                VoedselvoorkeurModel voedselvoorkeurModel = new VoedselvoorkeurModel(naam);

                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = voedselvoorkeurController.CreateDelete(voedselvoorkeurModel, "Create");

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new VoedselvoorkeurOverzichtView(pane);                    
                } else {
                    // Error handling
                    lblError.setText("Er is een fout opgetreden bij het aanmaken van de voedselvoorkeur.");
                }
            } else {
                // Error handling
                lblError.setText("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
    }
}
