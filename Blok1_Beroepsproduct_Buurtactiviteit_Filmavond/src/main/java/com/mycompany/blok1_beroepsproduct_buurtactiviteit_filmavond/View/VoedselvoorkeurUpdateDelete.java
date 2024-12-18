package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.VoedselvoorkeurModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.VoedselvoorkeurController;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class VoedselvoorkeurUpdateDelete {
    public final Label titel, naamTitel, lblError;
    public final TextField voedselvoorkeurText;
    public final Button bewerkKnop, verwijderKnop;
    
    private VoedselvoorkeurController voedselvoorkeurController;

    public VoedselvoorkeurUpdateDelete(GridPane pane, VoedselvoorkeurModel voedselvoorkeur) {
        // Defineren van objecten
        titel = new Label("Voedselvoorkeur Bewerken");
        naamTitel = new Label("Naam");        
        voedselvoorkeurText = new TextField();        
        bewerkKnop = new Button("Bewerken");
        verwijderKnop = new Button("Verwijderen");
        lblError = new Label();

        // Objecten toevoegen aan GridPane
        pane.add(titel, 0, 0);
        pane.add(naamTitel, 0, 1);
        pane.add(voedselvoorkeurText, 1, 1);
        pane.add(bewerkKnop, 0, 2);
        pane.add(verwijderKnop, 1, 2);
        pane.add(lblError, 0, 3);

        String naam = voedselvoorkeur.getNaam();
        voedselvoorkeurText.setText(naam);
        
        // Controller defineren
        voedselvoorkeurController = new VoedselvoorkeurController();
        
        bewerkKnop.setOnAction(event -> {
            // Inputs defineren
            String updatedNaam = voedselvoorkeurText.getText();
            
            // Check of waardes niet leeg zijn.
            if (updatedNaam != null && !updatedNaam.isEmpty()) {
                // Nieuw model aanmaken met inputs
                VoedselvoorkeurModel updatedVoedselvoorkeur = new VoedselvoorkeurModel(updatedNaam);

                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = voedselvoorkeurController.Update(voedselvoorkeur, updatedVoedselvoorkeur);

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new VoedselvoorkeurOverzichtView(pane);                    
                } else {
//                    System.out.println("Er is een fout opgetreden bij het aanmaken van het item.");
                }
            } else {
                System.out.println("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
        
        verwijderKnop.setOnAction(event -> {            
            // Check of waardes niet leeg zijn.
            if (voedselvoorkeur.getNaam() != null && !voedselvoorkeur.getNaam().isEmpty()) {
                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = voedselvoorkeurController.CreateDelete(voedselvoorkeur, "Delete");

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new VoedselvoorkeurOverzichtView(pane);                    
                } else {
                    // Error handling
                    lblError.setText("Er is een fout opgetreden bij het aanmaken van het item.");
                }
            } else {
                // Error handling
                lblError.setText("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
    }
}
