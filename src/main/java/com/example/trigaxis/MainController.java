package com.example.trigaxis;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainController {

    @FXML
    private HBox mainHBox;

    @FXML
    private VBox mainVBox;

    @FXML
    private VBox sideBar;

    @FXML
    private StackPane mainCanvas;

    @FXML
    public void initialize(){

        System.out.println("Initializing FXML");
        try{
            String stylesheet = getClass().getResource("mainStyle.css").toExternalForm();
            mainVBox.getStylesheets().add(stylesheet);
        }
        catch (Exception e){
            System.out.println("Stylesheet failed to load");
        }
        Platform.runLater(() -> CreateNewCanvas());

    }

    @FXML
    private Label welcomeText;

    private void CreateNewCanvas(){
        Group root = new Group();
        Canvas canvas = new Canvas(mainCanvas.getWidth(), mainCanvas.getHeight());
        canvas.widthProperty().bind(mainCanvas.widthProperty());
        canvas.heightProperty().bind(mainCanvas.heightProperty());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        AxisSetup(gc);
        root.getChildren().add(canvas);
        mainCanvas.getChildren().add(root);
    }

    private void AxisSetup(GraphicsContext gc){
        gc.setLineWidth(1);
        gc.setFill(Color.BLACK);


        for (int i = 0; i < mainCanvas.getWidth(); i += 30){
            if (i >= mainCanvas.getWidth()/2 - 20 && i <= mainCanvas.getWidth()/2 + 20){
                gc.setLineWidth(3);
                gc.strokeLine(i, 0, i, mainCanvas.getHeight());
                gc.setLineWidth(1);
            }
            else{
                gc.strokeLine(i, 0, i, mainCanvas.getHeight());
            }
        }

        for (int i = 0; i < mainCanvas.getHeight(); i += 30){
            if (i >= mainCanvas.getHeight()/2 - 20 && i <= mainCanvas.getHeight()/2 + 20){
                gc.setLineWidth(3);
                gc.strokeLine(0, i, mainCanvas.getWidth(), i);
                gc.setLineWidth(1);
            }
            else{
                gc.strokeLine(0, i, mainCanvas.getWidth(), i);
            }
        }
    }

}