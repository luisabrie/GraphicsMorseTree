/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.morsetree.model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author mbpretina
 */
public class TreeNodeItem extends StackPane {
    private final Circle circle;
    private Line line;
    private final Text text;
    private static final double RADIUS = 15;
    
    public TreeNodeItem(Character c,double x,double y){
        this.circle = new Circle(RADIUS);
        this.text = new Text(c.toString());
        circle.setFill(Color.web("#85FFC7"));
        circle.setStroke(Color.BLACK);
        setTranslateX(x-RADIUS);
        setTranslateY(y-RADIUS);
                
        getChildren().addAll(circle, text);
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
        line.setStroke(Color.web("#E6E6E6"));
    }
    
    public void focusNode() {
        circle.setFill(Color.web("#FF8552"));
    }
    public void focusPath(){
        line.setStroke(Color.web("#FF8552"));
    }
    public void traversed(){
        circle.setFill(Color.web("#297373"));
    }
    public void resetColor(){
        if (circle!=null)
            circle.setFill(Color.web("#85FFC7"));
        if (line!=null)
            line.setStroke(Color.web("#E6E6E6"));  
    }
    
}
