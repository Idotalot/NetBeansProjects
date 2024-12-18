/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fxvb0305;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author jordy
 */
public class FXVb0305 {
    private final TextField invoervak1, invoervak2, resultaatvak;
    private final Button plusknop, deelknop, moduloknop;
    
    public FXVb0305(Pane pane) {
        // GUI Componenten
        invoervak1 = new TextField();
        invoervak2 = new TextField();
        plusknop = new Button("+");
        deelknop = new Button("/");
        moduloknop = new Button("%");
        resultaatvak = new TextField();   
        
        // GUI Component positioning
        invoervak1.relocate(20, 20);
        invoervak2.relocate(20, 50);
        plusknop.relocate(20, 80);
        deelknop.relocate(40, 80);
        moduloknop.relocate(60, 80);
        resultaatvak.relocate(20, 110);
        
        invoervak1.setAlignment(Pos.CENTER_RIGHT);
        invoervak1.setAlignment(Pos.CENTER_RIGHT);
        plusknop.setAlignment(Pos.CENTER_RIGHT);
        deelknop.setAlignment(Pos.CENTER_RIGHT);
        moduloknop.setAlignment(Pos.CENTER_RIGHT);
        resultaatvak.setAlignment(Pos.CENTER_RIGHT);

        // GUI Component behavior
        plusknop.setOnAction(event -> {
            int getal1 = Integer.parseInt(invoervak1.getText());
            int getal2 = Integer.parseInt(invoervak2.getText());
            
            int resultaat = getal1 + getal2;
            
            resultaatvak.setText(""+resultaat);
        });
        
        deelknop.setOnAction(event -> {
            int getal1 = Integer.parseInt(invoervak1.getText());
            int getal2 = Integer.parseInt(invoervak2.getText());
            
            int resultaat = getal1 / getal2;
            
            resultaatvak.setText(""+resultaat);
        });
        
        moduloknop.setOnAction(event -> {
            int getal1 = Integer.parseInt(invoervak1.getText());
            int getal2 = Integer.parseInt(invoervak2.getText());
            
            int resultaat = getal1 % getal2;
            
            resultaatvak.setText(""+resultaat);
        });
        
        pane.getChildren().addAll(invoervak1, invoervak2, plusknop, deelknop, moduloknop, resultaatvak);
    }
}
