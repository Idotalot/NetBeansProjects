package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BenodigdheidModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.BenodigdheidController;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class BenodigdheidUpdateDeleteView {
    public final Label titel, naamTitel;
    public final TextField benodigdheidText;
    public final Button bewerkKnop, verwijderKnop;
    
    private BenodigdheidController benodigdheidController;

    public BenodigdheidUpdateDeleteView(GridPane pane, BenodigdheidModel benodigdheid) {
        // Initialize labels, text field, and button
        titel = new Label("Benodigdheid Bewerken");
        naamTitel = new Label("Naam");        
        benodigdheidText = new TextField();        
        bewerkKnop = new Button("Bewerken");
        verwijderKnop = new Button("Verwijderen");

        // Objecten toevoegen aan GridPane
        pane.add(titel, 0, 0);
        pane.add(naamTitel, 0, 1);
        pane.add(benodigdheidText, 1, 1);
        pane.add(bewerkKnop, 0, 2);
        pane.add(verwijderKnop, 1, 2);

        String naam = benodigdheid.getNaam();
        benodigdheidText.setText(naam);
        
        // Controller defineren
        benodigdheidController = new BenodigdheidController();
        
        bewerkKnop.setOnAction(event -> {
            // Inputs defineren
            String updatedNaam = benodigdheidText.getText();
            
            // Check of waardes niet leeg zijn.
            if (updatedNaam != null && !updatedNaam.isEmpty()) {
                // Nieuwe BenodigdheidModel aanmaken met inputs
                BenodigdheidModel updatedBenodigdheid = new BenodigdheidModel(updatedNaam);

                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = benodigdheidController.Update(benodigdheid, updatedBenodigdheid);

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new BenodigdheidOverzichtView(pane);                    
                } else {
//                    System.out.println("Er is een fout opgetreden bij het aanmaken van de benodigdheid.");
                }
            } else {
                System.out.println("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
        
        verwijderKnop.setOnAction(event -> {            
            // Check of waardes niet leeg zijn.
            if (benodigdheid.getNaam() != null && !benodigdheid.getNaam().isEmpty()) {
                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = benodigdheidController.CreateDelete(benodigdheid, "Delete");

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new BenodigdheidOverzichtView(pane);                    
                } else {
//                    System.out.println("Er is een fout opgetreden bij het aanmaken van de benodigdheid.");
                }
            } else {
                System.out.println("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
    }
}
