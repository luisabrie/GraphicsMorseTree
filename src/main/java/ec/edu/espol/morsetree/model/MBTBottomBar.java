/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.morsetree.model;


import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author mbpretina
 */
public class MBTBottomBar extends HBox{
    
    public MBTBottomBar(MBTView view){
        setSpacing(8);
        Label musicLabel = new IconLabel(FontAwesomeIcon.MUSIC).getLabel();
        Label tachometerLabel = new IconLabel(FontAwesomeIcon.TACHOMETER).getLabel();
        
        getChildren().addAll(musicLabel,
                view.getHzSlider(),
                tachometerLabel,
                view.getSpeedSlider()
        );
        setAlignment(Pos.CENTER);
        setPadding(new Insets(0,0,3,0));

    }
    public void setCancelButton(Label btCancel){
        getChildren().add(0,btCancel);
    }
    public void setTextField(TextField tf){
        getChildren().add(0,tf);
    }
}
