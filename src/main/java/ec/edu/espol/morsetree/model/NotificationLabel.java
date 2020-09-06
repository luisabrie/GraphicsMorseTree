/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.morsetree.model;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 * @author mbpretina
 */
public class NotificationLabel extends Label{
    
    private static final NotificationLabel instance = new NotificationLabel();
    PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
    private NotificationLabel(){
        
    }
    public void send(String notification){
        instance.setVisible(true);
        instance.setText(notification);

        visiblePause.setOnFinished(
                event -> instance.setVisible(false)
        );
        
        visiblePause.play();
        
    }
    
    public static NotificationLabel getInstance(){
        return instance;
    }
    
}
