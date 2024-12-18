/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BuurtbewonerModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.VoedselvoorkeurModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BenodigdheidModel;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.BuurtbewonerController;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.BenodigdheidController;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.VoedselvoorkeurController;

import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class BuurtbewonerUpdateDeleteView {
    public final Label titel, naamTitel, voedselvoorkeurTitel, leeftijdsklasseTitel, benodigdhedenTitel, lblError;
    public final TextField txtBuurtbewonerNaam;
    public final ComboBox cbVoedselvoorkeur, cbLeeftijdsklasse, cbBenodigdheid;
    public final Button bewerkKnop, verwijderKnop;
    
    public ObservableList<BenodigdheidModel> benodigdhedenData;
    public ObservableList<VoedselvoorkeurModel> voedselvoorkeurData;
    
    private BuurtbewonerController buurtbewonerController;
    private BenodigdheidController benodigdheidController;
    private VoedselvoorkeurController voedselvoorkeurController;

    public BuurtbewonerUpdateDeleteView(GridPane pane, BuurtbewonerModel buurtbewoner) {
        // Defineren van objecten
        titel = new Label("Buurtbewoner Bewerken");
        naamTitel = new Label("Naam");
        voedselvoorkeurTitel = new Label("Voedselvoorkeur");
        leeftijdsklasseTitel = new Label("Leeftijdsklasse");
        benodigdhedenTitel = new Label("Benodigdheden");
        
        txtBuurtbewonerNaam = new TextField();
        cbVoedselvoorkeur = new ComboBox();
        cbLeeftijdsklasse = new ComboBox();
        cbBenodigdheid = new ComboBox();
        
        bewerkKnop = new Button("Bewerken");
        verwijderKnop = new Button("Verwijderen");
        
        lblError = new Label("");

        // Objecten toevoegen aan GridPane
        pane.add(titel, 0, 0);
        
        pane.add(txtBuurtbewonerNaam, 1, 1);
        pane.add(naamTitel, 0, 1);
        pane.add(cbVoedselvoorkeur, 1, 2);
        pane.add(voedselvoorkeurTitel, 0, 2);
        pane.add(cbLeeftijdsklasse, 1, 3);
        pane.add(leeftijdsklasseTitel, 0, 3);
        pane.add(cbBenodigdheid, 1, 4);
        pane.add(benodigdhedenTitel, 0, 4);
        
        pane.add(bewerkKnop, 0, 5);
        pane.add(verwijderKnop, 1, 5);
        
        pane.add(lblError, 0, 6);

        String naam = buurtbewoner.getNaam();
        VoedselvoorkeurModel voedselvoorkeur = buurtbewoner.getVoedselvoorkeur();
        String leeftijdsklasse = buurtbewoner.getLeeftijdsklasse();
        BenodigdheidModel benodigdheid = buurtbewoner.getBenodigdheid();
        
        
        txtBuurtbewonerNaam.setText(naam);
        cbVoedselvoorkeur.setValue(voedselvoorkeur.getNaam());
        cbLeeftijdsklasse.setValue(leeftijdsklasse);
        cbBenodigdheid.setValue(benodigdheid.getNaam());
        
        voedselvoorkeurController = new VoedselvoorkeurController();
        voedselvoorkeurData = voedselvoorkeurController.Read();
        
        for (VoedselvoorkeurModel voedselvoorkeurItem : voedselvoorkeurData)
            cbVoedselvoorkeur.getItems().add(voedselvoorkeurItem.getNaam());
        
        cbLeeftijdsklasse.getItems().addAll("Minderjarig", "Meerderjarig");
        
        benodigdheidController = new BenodigdheidController();
        benodigdhedenData = benodigdheidController.Read();
        
        // Leeglaten van benodigdheden *OPTIONEEL*
        cbBenodigdheid.getItems().add("");
        
        for (BenodigdheidModel benodigdheidItem : benodigdhedenData)
            cbBenodigdheid.getItems().add(benodigdheidItem.getNaam());
        
        buurtbewonerController = new BuurtbewonerController();
        
        // Controller defineren
        buurtbewonerController = new BuurtbewonerController();
        
        bewerkKnop.setOnAction(event -> {
            // Inputs defineren
            String updatedNaam = txtBuurtbewonerNaam.getText();
            String updatedVoedselvoorkeurNaam = cbVoedselvoorkeur.getValue().toString();
            String updatedLeeftijdsklasseNaam = cbLeeftijdsklasse.getValue().toString();
            String updatedBenodigdheidNaam = cbBenodigdheid.getValue().toString();
            
            // Check of waardes niet leeg zijn.
            if (updatedNaam != null && !updatedNaam.isEmpty()) {
                VoedselvoorkeurModel updatedVoedselvoorkeur = new VoedselvoorkeurModel(updatedVoedselvoorkeurNaam);
                BenodigdheidModel updatedBenodigdheid = new BenodigdheidModel(updatedBenodigdheidNaam);
                
                // Nieuwe BenodigdheidModel aanmaken met inputs
                BuurtbewonerModel updatedBuurtbewoner = new BuurtbewonerModel(updatedNaam, updatedVoedselvoorkeur, updatedLeeftijdsklasseNaam, updatedBenodigdheid);
                

                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = buurtbewonerController.Update(buurtbewoner, updatedBuurtbewoner);

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new BuurtbewonerOverzichtView(pane);                    
                } else {
                    // Error handling
                    lblError.setText("Er is een fout opgetreden bij het bewerken van de buurtbewoner.");
                }
            } else {
                // Error handling
                lblError.setText("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
        
        verwijderKnop.setOnAction(event -> {            
            // Check of waardes niet leeg zijn.
            if (benodigdheid.getNaam() != null && !benodigdheid.getNaam().isEmpty()) {
                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = buurtbewonerController.CreateDelete(buurtbewoner, "Delete");

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new BuurtbewonerOverzichtView(pane);                    
                } else {
                    // Error handling
                    lblError.setText("Er is een fout opgetreden bij het verwijderen van de buurtbewoner.");
                }
            } else {
                // Error handling
                lblError.setText("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
    }
}
