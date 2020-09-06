
package ec.edu.espol.morsetree.util;

import ec.edu.espol.morsetree.model.NotificationLabel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;



public class MorseBinaryTree {
    private Node root = new Node('∫');    
    private HashMap<Character,String> hmap = new HashMap<>();
    
    public double height (){
        return height(root);
    }
    private double height (Node n){
        if (n == null)
            return 0;
        return 1.00+ Math.max(height(n.getLeft()), height(n.getRight()));
    }
    private void add(Character element, String morseRepresentation){
        Node now = root;
        int i = 0;
        Character letter;
        hmap.put(element, morseRepresentation);
        while (i < morseRepresentation.length()){
            letter = morseRepresentation.charAt(i);
            switch(letter){
                case '_':
                    if (now.getLeft() != null)
                        now = now.getLeft();
                    else {
                        now.setLeft(new Node());
                        now = now.getLeft();
                    }
                    break;
                case '.':
                    if (now.getRight() != null) 
                        now = now.getRight();
                    else {
                        now.setRight(new Node());
                        now = now.getRight();
                    }
                    break;
                default:
                    NotificationLabel.getInstance().send("A ocurrido un error, no se encontró accion para : "+letter);
            }
            i = i +2;
        }
        now.setCaracter(element);
    }
    public String lookFor(Character elemento){
        return hmap.get(elemento);
    }   
    public void generateTreeFromFile(){
        ClassLoader classLoader = getClass().getClassLoader();
        
        URL resource = classLoader.getResource("traduccion.txt");
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            File file = new File(resource.getFile());
            try (FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader)) {
            
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    add(line.charAt(0), line.substring(4));
                }
            }
        }   catch (FileNotFoundException ex) {
                NotificationLabel.getInstance().send("File not found: "+ ex.getMessage());
            } catch (IOException ex) {
                NotificationLabel.getInstance().send("An error has ocurred: "+ ex.getMessage());
            }
        }
    }
    public Node getRoot() {
        return root;
    }
    
    
}
