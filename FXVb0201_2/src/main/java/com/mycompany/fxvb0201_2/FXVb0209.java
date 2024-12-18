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
public class FXVb0209 
{
    private final Button knop, knop2;
    private final TextField tekstvak, tekstvak2;
    // Automatisch wordt de constructor uitgevoerd
    public FXVb0209(FlowPane p) 
    {
        knop = new Button("Voornaam");
        tekstvak = new TextField();
        
        knop2 = new Button("Achternaam");
        tekstvak2 = new TextField();
        
        knop.setOnAction( event -> {
            tekstvak.setText("Jordy");
        });
        
        knop2.setOnAction(event -> {
            tekstvak2.setText("Perret");
        });
        
        
        p.getChildren().addAll(knop, tekstvak, knop2, tekstvak2);
    }
}
