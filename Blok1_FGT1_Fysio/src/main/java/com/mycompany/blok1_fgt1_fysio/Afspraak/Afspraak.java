/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.blok1_fgt1_fysio.Afspraak;

import java.sql.Date;

/**
 *
 * @author jordy
 */
public class Afspraak {
    private String fysiotherapeutNaam;
    private String patientNaam;
    private Date datum;

    public Afspraak(String fysiotherapeutNaam, String patientNaam, Date datum) {
        this.fysiotherapeutNaam = fysiotherapeutNaam;
        this.patientNaam = patientNaam;
        this.datum = datum;
    }

    public String getFysiotherapeutNaam() {
        return fysiotherapeutNaam;
    }

    public String getPatientNaam() {
        return patientNaam;
    }
    
    public Date getDatum() {
        return datum;
    }
}
