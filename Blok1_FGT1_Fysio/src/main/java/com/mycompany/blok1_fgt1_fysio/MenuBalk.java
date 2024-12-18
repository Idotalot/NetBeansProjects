package com.mycompany.blok1_fgt1_fysio;

import com.mycompany.blok1_fgt1_fysio.Adres.AdresOverzicht;
import com.mycompany.blok1_fgt1_fysio.Adres.AdresToevoegen;

import com.mycompany.blok1_fgt1_fysio.Fysio.FysioOverzicht;
import com.mycompany.blok1_fgt1_fysio.Fysio.FysioToevoegen;

import com.mycompany.blok1_fgt1_fysio.Patient.PatientOverzicht;
import com.mycompany.blok1_fgt1_fysio.Patient.PatientToevoegen;

import com.mycompany.blok1_fgt1_fysio.Specialisatie.specOverzicht;
import com.mycompany.blok1_fgt1_fysio.Specialisatie.specToevoegen;

import com.mycompany.blok1_fgt1_fysio.Woonplaats.WoonplaatsOverzicht;
import com.mycompany.blok1_fgt1_fysio.Woonplaats.WoonplaatsToevoegen;

import com.mycompany.blok1_fgt1_fysio.Afspraak.AfspraakOverzicht;
import com.mycompany.blok1_fgt1_fysio.Afspraak.AfspraakToevoegen;

import com.mycompany.blok1_fgt1_fysio.Datum.DatumOverzicht;
import com.mycompany.blok1_fgt1_fysio.Datum.DatumToevoegen;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
 
public class MenuBalk extends MenuBar 
{
    private Menu mFysio, mSpec, mPatient, mWoonplaats, mAdres, mAfspraak, mDatum;
    private MenuItem miFysioAdd, miFysioView, miSpecAdd, miSpecView, miPatientAdd, miPatientView, miWoonplaatsAdd, miWoonplaatsView, miAdresAdd, miAdresView, miDatumAdd, miDatumView, miAfspraakAdd, miAfspraakView;
    
    public MenuBalk(GridPane root) {
        
        // Fysio menu defineren
        mFysio = new Menu("Fysiotherapeuten");
        
        miFysioAdd = new MenuItem("Fysiotherapeut Toevoegen");
        miFysioAdd.setOnAction(e -> {
            root.getChildren().clear();
            new FysioToevoegen(root);
        });
        
        miFysioView = new MenuItem("Fysiotherapeut Overzicht");
        miFysioView.setOnAction(e -> {
            root.getChildren().clear();
            new FysioOverzicht(root);
        });
        
        mFysio.getItems().addAll(miFysioAdd, miFysioView);
        
        // Spec menu defineren
        mSpec = new Menu("Specialisaties");
        
        miSpecAdd = new MenuItem("Specialisatie Toevoegen");
        miSpecAdd.setOnAction(e -> {
            root.getChildren().clear();
            new specToevoegen(root);
        });
        
        miSpecView = new MenuItem("Specialisatie Overzicht");
        miSpecView.setOnAction(e -> {
            root.getChildren().clear();
            new specOverzicht(root);
        });

        mSpec.getItems().addAll(miSpecAdd, miSpecView);
        
        // Datummenu defineren
        mPatient = new Menu("Patïenten");
        
        miPatientAdd = new MenuItem("Patiënt Toevoegen");
        miPatientAdd.setOnAction(e -> {
            root.getChildren().clear();
            new PatientToevoegen(root);
        });
        
        miPatientView = new MenuItem("Patiënt Overzicht");
        miPatientView.setOnAction(e -> {
            root.getChildren().clear();
            new PatientOverzicht(root);
        });
        
        mPatient.getItems().addAll(miPatientAdd, miPatientView);
        
        // Woonplaats menu defineren
        mWoonplaats = new Menu("Woonplaatsen");
        
        miWoonplaatsAdd = new MenuItem("Woonplaats Toevoegen");
        miWoonplaatsAdd.setOnAction(e -> {
            root.getChildren().clear();
            new WoonplaatsToevoegen(root);
        });
        
        miWoonplaatsView = new MenuItem("Woonplaats Overzicht");
        miWoonplaatsView.setOnAction(e -> {
            root.getChildren().clear();
            new WoonplaatsOverzicht(root);
        });
        
        mWoonplaats.getItems().addAll(miWoonplaatsAdd, miWoonplaatsView);
        
        // Adresmenu defineren
        mAdres = new Menu("Adressen");
        
        miAdresAdd = new MenuItem("Adres Toevoegen");
        miAdresAdd.setOnAction(e -> {
            root.getChildren().clear();
            new AdresToevoegen(root);
        });
        
        miAdresView = new MenuItem("Adres Overzicht");
        miAdresView.setOnAction(e -> {
            root.getChildren().clear();
            new AdresOverzicht(root);
        });
        
        mAdres.getItems().addAll(miAdresAdd, miAdresView);
        
        // Afspraak defineren
        mAfspraak = new Menu("Afspraken");
        
        miAfspraakAdd = new MenuItem("Afspraak Toevoegen");
        miAfspraakAdd.setOnAction(e -> {
            root.getChildren().clear();
            new AfspraakToevoegen(root);
        });
        
        miAfspraakView = new MenuItem("Afspraak Overzicht");
        miAfspraakView.setOnAction(e -> {
            root.getChildren().clear();
            new AfspraakOverzicht(root);
        });
        
        mAfspraak.getItems().addAll(miAfspraakAdd, miAfspraakView);
        
        // Datummenu defineren
        mDatum = new Menu("Datum");
        
        miDatumAdd = new MenuItem("Datum Toevoegen");
        miDatumAdd.setOnAction(e -> {
            root.getChildren().clear();
            new DatumToevoegen(root);
        });
        
        miDatumView = new MenuItem("Datum Overzicht");
        miDatumView.setOnAction(e -> {
            root.getChildren().clear();
            new DatumOverzicht(root);
        });
        
        mDatum.getItems().addAll(miDatumAdd, miDatumView);
        
        this.getMenus().addAll(mFysio, mSpec, mPatient, mWoonplaats, mAdres, mAfspraak, mDatum);
    }
}
