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
public class FXVb0210 
{
    private final Button knop, knop2, knop3;
    private final TextField tekstvak;
            
    public FXVb0210(FlowPane p) 
    {
        knop = new Button("Tekst1");
        knop2 = new Button("Tekst2");
        knop3 = new Button("Tekst3");
        
        tekstvak = new TextField();
        
        knop.setOnAction(event ->{
            tekstvak.setText("Rood");
        });
        
        knop2.setOnAction(event ->{
            tekstvak.setText("Groen");
        });
        
        knop3.setOnAction(event ->{
            tekstvak.setText("Blauw");
        });
        
        p.getChildren().addAll(tekstvak, knop, knop2, knop3);
    }
}
