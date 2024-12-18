/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fxvb0201_2;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author jordy
 */
public class FXVb0206 
{
    private final Button knop, herstelknop;
    private final TextField tekstvak;
    // Automatisch wordt de constructor uitgevoerd
    public FXVb0206(FlowPane p) 
    {
        knop = new Button("klik");
        tekstvak = new TextField();
        herstelknop = new Button("Veeg uit");
        
        knop.setOnAction( event -> {
            tekstvak.setText("Alles ondergeklikt");
        });
        
        herstelknop.setOnAction(event -> {
            tekstvak.clear();
        });
        
        
        p.getChildren().addAll(knop, tekstvak, herstelknop);
    }
}
