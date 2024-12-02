package com.example.trigaxis;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML
    private VBox mainVBox;

    @FXML
    public void initialize(){
        try{
            String stylesheet = getClass().getResource("/stylesheets/MainControllerCSS.css").toExternalForm();
            mainVBox.getStylesheets().add(stylesheet);  // Or adjust if targeting the Scene);
        }
        catch (Exception e){
            System.out.println("Stylesheet failed to load");
        }
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}