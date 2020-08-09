
package ec.edu.espol.morsetree.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;


public class MorseBinaryTree {
    private Node root = new Node('∫');    
    
    private class Node{
        private Character caracter;
        private Node left;
        private Node right;

        private Node() {
           
        }
        private Node(Character caracter) {
           this.caracter = caracter;
        }
    }
    
    private void add(Character element, String morseRepresentation){
        Node now = root;
        int i = 0;
        Character letter;
        while (i < morseRepresentation.length()){
            letter = morseRepresentation.charAt(i);
            switch(letter){
                case '.':
                    if (now.left != null)
                    now = now.left;
                    else {
                        now.left = new Node();
                        now = now.left;
                    }
                    break;
                case '_':
                    if (now.right != null) now = now.right;
                    else {
                        now.right = new Node();
                        now = now.right;
                    }
                    break;
                default:
                    System.out.println("A ocurrido un error, no se encontró accion para : "+letter);
            }
            i = i +2;
        }
        now.caracter = element;
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
            System.out.println(n.caracter);
            preOrden(n.left);
            preOrden(n.right);
        }
    }
    
    
}
