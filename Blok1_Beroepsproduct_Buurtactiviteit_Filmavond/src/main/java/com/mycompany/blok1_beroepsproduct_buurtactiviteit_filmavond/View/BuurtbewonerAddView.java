/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BuurtbewonerModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.BuurtbewonerController;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BenodigdheidModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Controller.BenodigdheidController;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.VoedselvoorkeurModel;
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
public class BuurtbewonerAddView {
    public final Label titel, naamTitel, voedselvoorkeurTitel, leeftijdsklasseTitel, benodigdhedenTitel, lblError;
    public final TextField txtBuurtbewonerNaam;
    public final ComboBox cbVoedselvoorkeur, cbLeeftijdsklasse, cbBenodigdheden;
    public final Button aanmaakKnop;
    
    public ObservableList<BenodigdheidModel> benodigdhedenData;
    public ObservableList<VoedselvoorkeurModel> voedselvoorkeurData;
    
    private BuurtbewonerController buurtbewonerController;
    private BenodigdheidController benodigdheidController;
    private VoedselvoorkeurController voedselvoorkeurController;
    
    public BuurtbewonerAddView(GridPane pane) {
        // Initialize labels, text field, and button
        titel = new Label("Buurtbewoner Toevoegen");
        naamTitel = new Label("Naam");
        voedselvoorkeurTitel = new Label("Voedselvoorkeur");
        leeftijdsklasseTitel = new Label("Leeftijdsklasse");
        benodigdhedenTitel = new Label("Benodigdheden");
        
        txtBuurtbewonerNaam = new TextField();
        cbVoedselvoorkeur = new ComboBox();
        cbLeeftijdsklasse = new ComboBox();
        cbBenodigdheden = new ComboBox();
        
        aanmaakKnop = new Button("Aanmaken");
        
        lblError = new Label();

        // Objecten toevoegen aan GridPane
        pane.add(titel, 0, 0);
        
        pane.add(txtBuurtbewonerNaam, 1, 1);
        pane.add(naamTitel, 0, 1);
        pane.add(cbVoedselvoorkeur, 1, 2);
        pane.add(voedselvoorkeurTitel, 0, 2);
        pane.add(cbLeeftijdsklasse, 1, 3);
        pane.add(leeftijdsklasseTitel, 0, 3);
        pane.add(cbBenodigdheden, 1, 4);
        pane.add(benodigdhedenTitel, 0, 4);
        
        pane.add(aanmaakKnop, 0, 5);

        // Controller defineren
        voedselvoorkeurController = new VoedselvoorkeurController();
        voedselvoorkeurData = voedselvoorkeurController.Read();
        
        for (VoedselvoorkeurModel voedselvoorkeur : voedselvoorkeurData)
            cbVoedselvoorkeur.getItems().add(voedselvoorkeur.getNaam());
        
        cbLeeftijdsklasse.getItems().addAll("Minderjarig", "Meerderjarig");
        
        benodigdheidController = new BenodigdheidController();
        benodigdhedenData = benodigdheidController.Read();
        
        // Leeglaten van benodigdheden *OPTIONEEL*
        cbBenodigdheden.getItems().add("");
        
        for (BenodigdheidModel benodigdheid : benodigdhedenData)
            cbBenodigdheden.getItems().add(benodigdheid.getNaam());
        
        buurtbewonerController = new BuurtbewonerController();
        
        aanmaakKnop.setOnAction(event -> {
            // Inputs defineren
            String naam = txtBuurtbewonerNaam.getText();
            String voedselvoorkeur = cbVoedselvoorkeur.getValue().toString();
            String leeftijdsklasse = cbLeeftijdsklasse.getValue().toString();
            String benodigdheid = cbBenodigdheden.getValue().toString();
            
            VoedselvoorkeurModel voedselvoorkeurModel = new VoedselvoorkeurModel(voedselvoorkeur);
           
            BenodigdheidModel benodigdheidModel = new BenodigdheidModel(benodigdheid);
            
            // Check of waardes niet leeg zijn.
            if (naam != null && !naam.isEmpty()) {
                // Nieuwe BenodigdheidModel aanmaken met inputs
                BuurtbewonerModel buurtbewonerModel = new BuurtbewonerModel(naam, voedselvoorkeurModel, leeftijdsklasse, benodigdheidModel);

                // CreateDelete method oproepen uit controller met juiste parameters
                Boolean result = buurtbewonerController.CreateDelete(buurtbewonerModel, "Create");

                // Actie uitvoerne gebasseerd op het resultaat
                if (result == true) {
                    pane.getChildren().clear();
                    new BuurtbewonerOverzichtView(pane);                    
                } else {
                    // Error handling
                    lblError.setText("Er is een fout opgetreden bij het aanmaken van de buurtbewoner.");
                }
            } else {
                // Error handling
                lblError.setText("Niet alle *VERPLICHTTE* velden zijn ingevuld.");
            }
        });
    }
}
