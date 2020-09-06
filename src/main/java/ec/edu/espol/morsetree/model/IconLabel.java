/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.morsetree.model;

import de.jensd.fx.glyphs.GlyphIcons;
import de.jensd.fx.glyphs.GlyphsDude;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;

/**
 *
 * @author mbpretina
 */
public class IconLabel extends GlyphsDude{
    
    private Label lbl;
    
    public IconLabel(GlyphIcons icon){
        lbl = createIconLabel(icon,"","17px","17px",ContentDisplay.LEFT);
        lbl.getStyleClass().add("icon-label");
    }
    
    public Label getLabel(){
        return this.lbl;
    }
    
}
