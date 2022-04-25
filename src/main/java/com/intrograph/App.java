package com.intrograph;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    Pane canvas = new Pane();
    Rectangle rectangle = new Rectangle(50,50, Color.RED);
    Scene scene = new Scene(canvas, 800, 800, Color.BEIGE);
    double xHalf = scene.getWidth()/2;
    double yHalf = scene.getHeight()/2;

    @Override
    public void start(Stage stage) {
    	    	
        rectangle.relocate(xHalf, yHalf*2);
        Line xLine = new Line(0, yHalf, xHalf*2, yHalf);
        Line yLine = new Line(xHalf, 0, xHalf, yHalf*2);
        
        canvas.getChildren().add(xLine);
        canvas.getChildren().add(yLine);
        canvas.getChildren().add(rectangle);

        stage.setTitle("Crazy Billiard");
        stage.setScene(scene);
        stage.show();


        PathTransition path = new PathTransition();
        int positio = 2;
        
            path.setNode(rectangle);
            path.setDuration(Duration.seconds(3));
            path.setPath(setRndCurve(positio));
            path.setCycleCount(1);
            path.play();

    }
    public CubicCurve setRndCurve(int posi)
    {
        Random random = new Random();
        CubicCurve curve = new CubicCurve();
        curve.setStartX(rectangle.getX());
        curve.setStartY(rectangle.getY());
        switch(posi)
        {
            case 0:
            curve.setControlX1(random.nextInt((int) xHalf) - xHalf/2);
            curve.setControlY1(random.nextInt((int) yHalf) + yHalf/2);
            curve.setEndX(xHalf);
            curve.setEndY(yHalf);
            break;
            case 1:
            curve.setControlX1(-random.nextInt((int) xHalf) - xHalf/2);
            curve.setControlY1(random.nextInt((int) yHalf) - yHalf/2);
            curve.setEndX(-xHalf);
            curve.setEndY(yHalf);
            break;
            case 2:
            curve.setControlX1(random.nextInt((int) xHalf) - xHalf/2);
            curve.setControlY1(-random.nextInt((int) yHalf) - yHalf/2);
            curve.setEndX(-xHalf);
            curve.setEndY(-yHalf);
            break;
            case 3:
            curve.setControlX1(random.nextInt((int) xHalf) + xHalf/2);
            curve.setControlY1(random.nextInt((int) yHalf) - yHalf/2);
            curve.setEndX(xHalf);
            curve.setEndY(yHalf);
            break;
        }
        
        curve.setControlX2(curve.getControlX1());
        curve.setControlY2(curve.getControlY1());

        return curve;
    }
    
    public static void main(String[] args) {
        launch();
    }
}