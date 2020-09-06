module ec.edu.espol.morsetree.vista {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.base;
    requires java.desktop;    
    requires fontawesomefx;
    
    opens ec.edu.espol.morsetree.vista to javafx.fxml;
    exports ec.edu.espol.morsetree.vista;
    
}
