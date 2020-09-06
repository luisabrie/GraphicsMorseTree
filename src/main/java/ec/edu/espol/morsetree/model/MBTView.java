/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.morsetree.model;

import ec.edu.espol.morsetree.util.MorseBinaryTree;
import ec.edu.espol.morsetree.util.Node;
import ec.edu.espol.morsetree.util.SoundUtils;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 *
 * @author mbpretina
 */
public class MBTView extends Pane{
    private final MorseBinaryTree mbt;
    private double vertSeparation = 50;
    private final Slider hzSlider = new Slider(300, 2000, 500);
    private final Slider speedSlider = new Slider(150, 1500, 300);
    
    public MBTView(MorseBinaryTree mbt) {
        this.mbt = mbt;
        widthProperty().addListener(cl -> displayTree());
        heightProperty().addListener((cl,oldvalue,newvalue) -> {
            vertSeparation = newvalue.doubleValue()/(mbt.height()+1);
            displayTree(); 
        });
    }
    public void displayTree(){
        this.getChildren().clear();
        if (mbt.getRoot() != null){
            displayTree(mbt.getRoot(), getWidth() / 2, vertSeparation, getWidth() / 4);
        }
    }
    private void displayTree(Node node, double x, double y, double hSeparation){
        TreeNodeItem tni = new TreeNodeItem(node.getCaracter(),x,y);
        getChildren().add(tni);
        node.setTni(tni);
        if (node.getLeft() != null){
            displayTree(node.getLeft(), x - hSeparation, y + vertSeparation, hSeparation / 2);
            drawLine(node.getLeft(),x,y,-hSeparation);
        }
        if (node.getRight() != null){
            displayTree(node.getRight(),x + hSeparation,y + vertSeparation, hSeparation / 2);
            drawLine(node.getRight(),x,y,hSeparation);
        }
        
    }
    private void drawLine(Node node,double x,double y,double hGap){
        Line line = new Line(x + hGap, y + vertSeparation, x, y);
        getChildren().add(0,line); 
        node.getTni().setLine(line);
    }
    
    public void followGraphically(Character c){
        String morseRepresentation = mbt.lookFor(c);
        if(!Character.isSpaceChar(c) && morseRepresentation != null){
                mbt.getRoot().getTni().focusNode();
                followGraphically(morseRepresentation, mbt.getRoot(),0);
                mbt.getRoot().getTni().resetColor();
        }
        else{
            try {
                Thread.sleep((long) speedSlider.getValue()*5);
            } catch (InterruptedException ex) {
                NotificationLabel.getInstance().send(ex.getMessage());
                Thread.currentThread().interrupt();
            }
        }
        

    }
    
    private void followGraphically(String morseRepresentation, Node n, int pos){
        if (pos < morseRepresentation.length()){
            switch(morseRepresentation.charAt(pos)){
                case '_':
                    showLeftPath(n);
                    followGraphically(morseRepresentation,n.getLeft(),pos+2);
                    n.getLeft().getTni().resetColor();
                    break;
                case '.':
                    showRightPath(n);
                    followGraphically(morseRepresentation,n.getRight(),pos+2);
                    n.getRight().getTni().resetColor();
                    break;
                default:
                    NotificationLabel.getInstance().send("Se desconoce el simbolo: " +morseRepresentation.charAt(pos));
                    break;
            }
        }
            
    }
    private void showLeftPath(Node n){
        showPath(n.getLeft(),3);
    }
    private void showRightPath(Node n){
        showPath(n.getRight(),1);
    }
    private void showPath(Node n,int speeddiff){
        n.getTni().focusNode();
        n.getTni().focusPath();
        SoundUtils.tone((int) hzSlider.getValue(),(int) speedSlider.getValue()*speeddiff);
        n.getTni().traversed();
    }
    public Slider getHzSlider() {
        return hzSlider;
    }

    public Slider getSpeedSlider() {
        return speedSlider;
    }
    
}
