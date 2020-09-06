/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.morsetree.model;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/**
 *
 * @author mbpretina
 */
public class MBTTextField extends TextField{
    public MBTTextField(){
        setPrefColumnCount(6);
        setAlignment(Pos.BASELINE_LEFT);
        setPrefWidth(391);
        setPrefHeight(27);
        setPromptText("Escriba lo que quiere traducir a morse y espere 3 segundos");
    }
}
