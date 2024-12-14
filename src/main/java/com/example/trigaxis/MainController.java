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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MainController {
    int[][] circleMatrix;

    @FXML
    private HBox mainHBox;

    @FXML
    private VBox mainVBox;

    @FXML
    private VBox sideBar;

    @FXML
    private StackPane mainCanvas;

    @FXML
    private Group circles;


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


    private void CreateNewCanvas(){
        Group root = new Group();
        Canvas canvas = new Canvas(mainCanvas.getWidth(), mainCanvas.getHeight());
        canvas.widthProperty().bind(mainCanvas.widthProperty());
        canvas.heightProperty().bind(mainCanvas.heightProperty());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        AxisSetup(gc);
        root.getChildren().add(canvas);
        mainCanvas.getChildren().add(root);

        canvas.setOnMouseClicked(event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x >= 0 && y >= 0 && x < circleMatrix.length && y < circleMatrix[0].length && circleMatrix[x][y] == 1) {
                gc.setFill(Color.BLUE);
                gc.fillOval(x - 5, y - 5, 20, 20); // Highlight the circle
            }
        });

    }

    private void AxisSetup(GraphicsContext gc){
        gc.setLineWidth(1);
        gc.setFill(Color.BLACK);
        int offset = 20;
        int lineDistance = 30;


        for (int i = 0; i < mainCanvas.getWidth(); i += lineDistance){
            if (i >= mainCanvas.getWidth()/2 - offset && i <= mainCanvas.getWidth()/2 + offset){
                gc.setLineWidth(3);
                gc.strokeLine(i, 0, i, mainCanvas.getHeight());
                gc.setLineWidth(1);
            }
            else{
                gc.strokeLine(i, 0, i, mainCanvas.getHeight());
            }
        }

        for (int i = 0; i < mainCanvas.getHeight(); i += lineDistance){
            if (i >= mainCanvas.getHeight()/2 - offset && i <= mainCanvas.getHeight()/2 + offset){
                gc.setLineWidth(3);
                gc.strokeLine(0, i, mainCanvas.getWidth(), i);
                gc.setLineWidth(1);
            }
            else{
                gc.strokeLine(0, i, mainCanvas.getWidth(), i);
            }
        }


        gc.beginPath();
        gc.setFill(Color.DARKRED);
        circleMatrix = new int[(int) mainCanvas.getWidth()][(int) mainCanvas.getHeight()];

        for (int i = -4; i < mainCanvas.getWidth(); i += lineDistance ){
            for (int j = -4; j < mainCanvas.getHeight(); j += lineDistance){
                gc.fillOval(i, j, 10, 10);
                if (i > -1 && j > -1){
                    circleMatrix[i][j] = 1;
                }

            }
        }

        gc.clip();

    }



}