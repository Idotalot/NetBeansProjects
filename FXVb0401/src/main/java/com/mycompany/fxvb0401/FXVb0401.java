/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fxvb0401;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jordy
 */
public class FXVb0401 
{
    public final TextField invoerVeld, resultaatVeld;
    public final Button opnieuwKnop;
    
    public FXVb0401 (GridPane pane) {
        invoerVeld = new TextField();
        resultaatVeld = new TextField();
        
        opnieuwKnop = new Button("Opniew");
        
        invoerVeld.setOnAction(event -> {
            double invoerWaarde = Double.valueOf(invoerVeld.getText());
            double resultaat = invoerWaarde * 12;
            
            resultaatVeld.setText(String.valueOf(resultaat));
        });
        
        opnieuwKnop.setOnAction(event -> {
            invoerVeld.setText("");
            resultaatVeld.setText("");
        });
        
        pane.add(invoerVeld, 0, 0);
        pane.add(resultaatVeld, 0, 1);
        pane.add(opnieuwKnop, 0, 3);

    }
}
