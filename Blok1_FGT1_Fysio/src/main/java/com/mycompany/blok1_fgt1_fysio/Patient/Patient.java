/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Patient;

/**
 *
 * @author jordy
 */
public class Patient {
    private String naam;
    private String adres;
    private String woonplaats;

    public Patient(String naam, String adres, String woonplaats) {
        this.naam = naam;
        this.adres = adres;
        this.woonplaats = woonplaats;
    }

    public String getNaam() {
        return naam;
    }

    public String getAdres() {
        return adres;
    }
    
    public String getWoonplaats() {
        return woonplaats;
    }
}

