/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.BenodigdheidAddView;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.BenodigdheidOverzichtView;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.VoedselvoorkeurAddView;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.VoedselvoorkeurOverzichtView;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.BuurtbewonerAddView;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.BuurtbewonerOverzichtView;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.FilmAddView;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.View.FilmOverzichtView;


import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class MenuBalk extends MenuBar {
    private Menu mBenodigdheid, mVoedselvoorkeur, mBuurtbewoner, mFilm;
    private MenuItem miBenodigdheidAdd, miBenodigdheidView, miVoedselvoorkeurAdd, miVoedselvoorkeurView, miBuurtbewonerAdd, miBuurtbewonerView, miFilmAdd, miFilmView;
    
    public MenuBalk(GridPane root) {
        // Benodigdheden menu defineren
        mBenodigdheid = new Menu("Benodigdheden");
        
        miBenodigdheidAdd = new MenuItem("Benodigdheid Toevoegen");
        miBenodigdheidAdd.setOnAction(e -> {
            root.getChildren().clear();
            new BenodigdheidAddView(root);
        });
        
        miBenodigdheidView = new MenuItem("Benodigdheden Bekijken");
        miBenodigdheidView.setOnAction(e -> {
            root.getChildren().clear();
            new BenodigdheidOverzichtView(root);
        });
        
        mBenodigdheid.getItems().addAll(miBenodigdheidAdd, miBenodigdheidView);
        
        // Voedselvoorkeuren menu defineren
        mVoedselvoorkeur = new Menu("Voedselvoorkeuren");
        
        miVoedselvoorkeurAdd = new MenuItem("Voedselvoorkeur Toevoegen");
        miVoedselvoorkeurAdd.setOnAction(e -> {
            root.getChildren().clear();
            new VoedselvoorkeurAddView(root);
        });
        
        miVoedselvoorkeurView = new MenuItem("Voedselvoorkeuren Bekijken");
        miVoedselvoorkeurView.setOnAction(e -> {
            root.getChildren().clear();
            new VoedselvoorkeurOverzichtView(root);
        });
        
        mVoedselvoorkeur.getItems().addAll(miVoedselvoorkeurAdd, miVoedselvoorkeurView);
        
        // Buurtbewoner menu defineren
        mBuurtbewoner = new Menu("Buurtbewoners");
        
        miBuurtbewonerAdd = new MenuItem("Buurtbewoner Toevoegen");
        miBuurtbewonerAdd.setOnAction(e -> {
            root.getChildren().clear();
            new BuurtbewonerAddView(root);
        });
        
        miBuurtbewonerView = new MenuItem("Buurtbewoners Bekijken");
        miBuurtbewonerView.setOnAction(e -> {
            root.getChildren().clear();
            new BuurtbewonerOverzichtView(root);
        });
        
        mBuurtbewoner.getItems().addAll(miBuurtbewonerAdd, miBuurtbewonerView);
        
        // Film menu defineren
        mFilm = new Menu("Films");
        
        miFilmAdd = new MenuItem("Film Toevoegen");
        miFilmAdd.setOnAction(e -> {
            root.getChildren().clear();
            new FilmAddView(root);
        });
        
        miFilmView = new MenuItem("Films Bekijken");
        miFilmView.setOnAction(e -> {
            root.getChildren().clear();
            new FilmOverzichtView(root);
        });
        
        mFilm.getItems().addAll(miFilmAdd, miFilmView);
        
        // Alles menus toevoegen aan een lijst
        this.getMenus().addAll(mBenodigdheid, mVoedselvoorkeur, mBuurtbewoner, mFilm);
    }
}
