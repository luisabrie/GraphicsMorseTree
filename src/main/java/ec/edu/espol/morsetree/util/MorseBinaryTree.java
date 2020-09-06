
package ec.edu.espol.morsetree.util;

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
                    System.out.println("A ocurrido un error, no se encontró accion para : "+letter);
            }
            i = i +2;
        }
        now.setCaracter(element);
    }
    public String lookFor(Character elemento){
        String morseRepresentation = hmap.get(elemento);
        return morseRepresentation;
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
                System.out.println("File not found: "+ ex.getMessage());
            } catch (IOException ex) {
                System.out.println("An error has ocurred: "+ ex.getMessage());
            }
        }
    }

    public void preOrden(){
            preOrden(root);
        }
    private void preOrden(Node n){
        if (n!=null){
            System.out.println(n.getCaracter());
            preOrden(n.getLeft());
            preOrden(n.getRight());
        }
    }

    public Node getRoot() {
        return root;
    }
    
    
}
