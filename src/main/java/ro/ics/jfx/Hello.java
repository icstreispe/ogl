package ro.ics.jfx;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by X13 on 02.01.2017.
 */
public class Hello extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creating the java button
        Button btn = getButton();
        // Initializing the StackPane class
        StackPane root = new StackPane();
        // Adding all the nodes to the FlowPane
        root.getChildren().add(btn);
        //Creating a scene object
        Scene scene = new Scene(root, 300, 250);
        //Adding the title to the window (primaryStage)
        primaryStage.setTitle("Hello JavaFX!");
        primaryStage.setScene(scene);
        // show the window(primaryStage)
        primaryStage.show();
    }

    private Button getButton() {
        Button btn = new Button();
        // Setting text to button
        btn.setText("Hello World");
        //registering a handler for button
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // printing Hello World! to the console
                System.out.println("Hello World!");
            }
        });
        return btn;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
