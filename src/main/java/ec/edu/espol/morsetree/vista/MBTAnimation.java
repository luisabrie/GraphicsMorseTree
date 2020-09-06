/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.morsetree.vista;


import ec.edu.espol.morsetree.model.MBTView;
import ec.edu.espol.morsetree.util.MorseBinaryTree;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * @author mbpretina
 */
public class MBTAnimation extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        MorseBinaryTree mbt = new MorseBinaryTree();
        mbt.generateTreeFromFile();
        BorderPane pane = new BorderPane();
        MBTView view = new MBTView(mbt);
        pane.setCenter(view);
        
        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(6);
        tfKey.setAlignment(Pos.BASELINE_LEFT);
        tfKey.setPrefWidth(391);
        tfKey.setPrefHeight(27);
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        tfKey.textProperty().addListener(
            (observable, oldValue, newValue) -> {
                pause.setOnFinished(event -> {
                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    for (char ch: newValue.toCharArray()) {
                        executorService.submit(new Runnable() {
                            public void run() {
                                if(ch!=' '){
                                    view.followGraphically(Character.toUpperCase(ch));
                                }else{
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                    executorService.shutdown();
                });
                pause.playFromStart();
            }
        );
        Button btInsert = new Button("X");
        btInsert.setOnAction(e -> {
            view.followGraphically(';');
        });
        btInsert.prefHeight(35);
        tfKey.setPromptText("Key");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(tfKey,btInsert);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0,0,3,0));
        pane.setBottom(hBox);
        
        Scene scene = new Scene(pane,900,500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/animation.css").toExternalForm());
        primaryStage.setTitle("MBTAnimation");
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
