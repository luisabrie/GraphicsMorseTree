/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.morsetree.model;

import ec.edu.espol.morsetree.util.MorseBinaryTree;
import ec.edu.espol.morsetree.util.Node;
import ec.edu.espol.morsetree.util.SoundUtils;
import java.util.concurrent.Semaphore;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author mbpretina
 */
public class MBTView extends Pane{
    private MorseBinaryTree mbt;
    final private double vGap = 50;
    
    public MBTView(MorseBinaryTree mbt) {
        this.mbt = mbt;
        setStatus("Tree is empty");
        widthProperty().addListener(cl -> displayTree());
        heightProperty().addListener(cl -> displayTree());

    }
    
    public void setStatus(String msg){
        getChildren().add(new Text(20,20,msg));
    }
    
    public void displayTree(){
        this.getChildren().clear();
        if (mbt.getRoot() != null){
            displayTree(mbt.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
        }
    }
    private void displayTree(Node node, double x, double y, double hGap){
        Line line = null;
        TreeNodeItem tni = new TreeNodeItem(node.getCaracter(),x,y);
        getChildren().add(tni);
        node.setTni(tni);
        if (node.getLeft() != null){
            displayTree(node.getLeft(), x - hGap, y + vGap, hGap / 2);
            getChildren().add(0,line = new Line(x - hGap, y + vGap, x, y)); 
            node.getLeft().getTni().setLine(line);
            
        }
        if (node.getRight() != null){
            displayTree(node.getRight(),
                    x + hGap,
                    y + vGap,
                    hGap / 2);
            getChildren().add(0,line = new Line(x + hGap, y + vGap, x, y));
            node.getRight().getTni().setLine(line);
        }
        
    }
    public void followGraphically(Character c){

            String morseRepresentation = mbt.lookFor(c);
            if (morseRepresentation != null)
                mbt.getRoot().getTni().focusNode();
            followGraphically(morseRepresentation, mbt.getRoot(),0);
            mbt.getRoot().getTni().resetColor();

    }
    private void followGraphically(String morseRepresentation, Node n, int pos){
        if (pos < morseRepresentation.length()){
            System.out.print(n.getCaracter());
            System.out.println(morseRepresentation.charAt(pos));
            switch(morseRepresentation.charAt(pos)){
                case '.':
                    n.getLeft().getTni().focusNode();
                    n.getLeft().getTni().focusPath();
                    SoundUtils.tone(500,300);
                    n.getLeft().getTni().traversed();
                    followGraphically(morseRepresentation,n.getLeft(),pos+2);
                    n.getLeft().getTni().resetColor();
                    break;
                case '_':
                    n.getRight().getTni().focusNode();
                    n.getRight().getTni().focusPath();
                    SoundUtils.tone(500,900);
                    n.getRight().getTni().traversed();
                    followGraphically(morseRepresentation,n.getRight(),pos+2);
                    n.getRight().getTni().resetColor();
                    break;
            }
        }
            
    }
    
}
