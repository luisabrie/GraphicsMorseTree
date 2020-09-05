/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.morsetree.util;

import ec.edu.espol.morsetree.model.TreeNodeItem;

/**
 *
 * @author mbpretina
 */
public class Node{
    private Character caracter;
    private Node left;
    private Node right;
    private TreeNodeItem tni;

    public Character getCaracter() {
        return caracter!=null?caracter:' ';
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setCaracter(Character caracter) {
        this.caracter = caracter;
    }

    Node() {

    }
    
    Node(Character caracter) {
       this.caracter = caracter;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
        public TreeNodeItem getTni() {
        return tni;
    }

    public void setTni(TreeNodeItem tni) {
        this.tni = tni;
    }
    }
