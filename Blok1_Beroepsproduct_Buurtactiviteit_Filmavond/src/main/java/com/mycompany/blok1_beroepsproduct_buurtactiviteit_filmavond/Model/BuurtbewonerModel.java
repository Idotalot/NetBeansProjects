/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model;

import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.BenodigdheidModel;
import com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model.VoedselvoorkeurModel;

/**
 *
 * @author jordy
 */
public class BuurtbewonerModel {
    private String naam;
    private VoedselvoorkeurModel voedselvoorkeur;
    private String leeftijdsklasse;
    private BenodigdheidModel benodigdheid;
    
    public BuurtbewonerModel(String naam, VoedselvoorkeurModel voedselvoorkeur, String leeftijdsklasse, BenodigdheidModel benodigdheid) {
        this.naam = naam;
        this.voedselvoorkeur = voedselvoorkeur;
        this.leeftijdsklasse = leeftijdsklasse;
        this.benodigdheid = benodigdheid;
    }
    
    public String getNaam() {
        return naam;
    }
    
    public VoedselvoorkeurModel getVoedselvoorkeur() {
        return voedselvoorkeur;
    }
    
    public String getLeeftijdsklasse() {
        return leeftijdsklasse;
    }
    
    public BenodigdheidModel getBenodigdheid() {
        return benodigdheid;
    }
}
