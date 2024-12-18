/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_beroepsproduct_buurtactiviteit_filmavond.Model;

import java.sql.Time;

/**
 *
 * @author jordy
 */
public class FilmModel {
    private String naam, leeftijdsklasse;
    private Time starttijd, eindtijd;
    
    public FilmModel(String naam, Time starttijd, Time eindtijd, String leeftijdsklasse) {
        this.naam = naam;
        this.starttijd = starttijd;
        this.eindtijd = eindtijd;
        this.leeftijdsklasse = leeftijdsklasse;
    }
    
    public String getNaam() {
        return naam;
    }
    
    public Time getStarttijd() {
        return starttijd;
    }
    
    public Time getEindtijd() {
        return eindtijd;
    }
    
    public String getLeeftijdsklasse() {
        return leeftijdsklasse;
    }
}
