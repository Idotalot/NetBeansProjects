package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BenodigdheidModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.BenodigdheidOverzichtView;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.BenodigdheidController;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class BenodigdheidAddView {
    public final Label titel, naamTitel, lblError;
    public final TextField benodigdheidText;
    public final Button aanmaakKnop;
    
    private BenodigdheidController benodigdheidController;

    public BenodigdheidAddView(GridPane pane) {
        // Initialize labels, text field, and button
        titel = new Label("Benodigdheid Toevoegen");
        naamTitel = new Label("Naam");        
        benodigdheidText = new TextField();        
        aanmaakKnop = new Button("Aanmaken");
        lblError = new Label();

        // Objecten toevoegen aan GridPane
        pane.add(titel, 0, 0);
        pane.add(naamTitel, 0, 1);
        pane.add(benodigdheidText, 1, 1);
        pane.add(aanmaakKnop, 0, 2);
        pane.add(lblError, 0, 3);

        // Controller defineren
        benodigdheidController = new BenodigdheidController();
        
        aanmaakKnop.setOnAction(event -> {
            // Inputs defineren
            String naam = benodigdheidText.getText();
            
            // Check of waardes niet leeg zijn.
            if (naam != null && !naam.isEmpty()) {
                // Nieuwe BenodigdheidModel aanmaken met inputs
                BenodigdheidModel benodigdheidModel = new BenodigdheidModel(naam);

                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = benodigdheidController.CreateDelete(benodigdheidModel, "Create");

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new BenodigdheidOverzichtView(pane);                    
                } else {
                    // Error handling
                    lblError.setText("Er is een fout opgetreden bij het aanmaken van de benodigdheid.");
                }
            } else {
                // Error handling
                lblError.setText("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
    }
}
