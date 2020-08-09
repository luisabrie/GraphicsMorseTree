module ec.edu.espol.morsetree {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.base;
    requires com.jfoenix;

    opens ec.edu.espol.morsetree to javafx.fxml;
    exports ec.edu.espol.morsetree;
}
