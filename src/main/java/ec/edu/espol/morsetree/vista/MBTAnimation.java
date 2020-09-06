package ec.edu.espol.morsetree.vista;


import ec.edu.espol.morsetree.model.NotificationLabel;
import ec.edu.espol.morsetree.model.MBTView;
import ec.edu.espol.morsetree.util.MorseBinaryTree;
import ec.edu.espol.morsetree.util.SoundUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import ec.edu.espol.morsetree.model.IconLabel;
import ec.edu.espol.morsetree.model.MBTBottomBar;
import ec.edu.espol.morsetree.model.MBTTextField;
import javafx.scene.control.Label;

/**
 *
 * @author mbpretina
 */
public class MBTAnimation extends Application{
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    MorseBinaryTree mbt;
    TextField tfKey;
    @Override
    public void start(Stage primaryStage) throws Exception {
        mbt = new MorseBinaryTree();
        mbt.generateTreeFromFile();
        BorderPane pane = new BorderPane();
        MBTView view = new MBTView(mbt);
        pane.setCenter(view);
        pane.setTop(NotificationLabel.getInstance());
        tfKey = new MBTTextField();
        
        Label btCancel = new IconLabel(FontAwesomeIcon.CLOSE).getLabel();
        btCancel.setOnMouseClicked(e -> {
            executorService.shutdownNow();
            view.displayTree();
            executorService = Executors.newSingleThreadExecutor();
        });
        
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        tfKey.textProperty().addListener(
            (observable, oldValue, newValue) -> {
                pause.setOnFinished(event -> {
                    if (sanityCheck(newValue)){
                        btCancel.setVisible(true);
                        for (char ch: newValue.toCharArray()) {
                            executorService.submit(() -> view.followGraphically(Character.toUpperCase(ch))     
                        );}}});
                pause.playFromStart();});
        MBTBottomBar hBox = new MBTBottomBar(view);
        hBox.setTextField(tfKey);
        hBox.setCancelButton(btCancel);
        
        pane.setBottom(hBox);
        
        Scene scene = new Scene(pane,900,500);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styles/animation.css").toExternalForm());
        primaryStage.setTitle("Morse Binary Tree Animation");
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args){
        launch();
    }
    private boolean sanityCheck(String txt){
        tfKey.setStyle("-fx-control-inner-background: #81B29A");
        for (char ch: txt.toCharArray()) {
            if (mbt.lookFor(Character.toUpperCase(ch)) == null && !Character.isSpaceChar(ch)){
                SoundUtils.tone(2000, 100);
                NotificationLabel.getInstance().send("No se encontró el caracter especial: "+ch);
                tfKey.setStyle("-fx-control-inner-background: #D90368");
                return false;
            }
        }
        return true;
    }
}
