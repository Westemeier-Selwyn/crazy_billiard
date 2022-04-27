package com.intrograph;

import java.util.Random;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    Pane canvas = new Pane();
    Rectangle rectangle = new Rectangle(50,50, Color.RED);
    Circle point = new Circle(10, Color.BLUEVIOLET);
    Scene scene = new Scene(canvas, 1000, 1000, Color.BEIGE);
    double xHalf = scene.getWidth()/2;
    double yHalf = scene.getHeight()/2;
    int po = 0;
    int nr = 0;
    int[][] crd = new int[2][80];

    @Override
    public void start(Stage stage) {
    	    	
        Line xLine = new Line(0, yHalf, xHalf*2, yHalf);
        Line yLine = new Line(xHalf, 0, xHalf, yHalf*2);
        Rectangle dia = new Rectangle(xHalf, yHalf, Color.AQUAMARINE);
        dia.relocate(xHalf/2, yHalf/2);

        canvas.getChildren().add(dia);
        canvas.getChildren().add(xLine);
        canvas.getChildren().add(yLine);
        canvas.getChildren().add(point);
        canvas.getChildren().add(rectangle);

        stage.setTitle("Crazy Billiard");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
            
        SequentialTransition sq = new SequentialTransition(p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po)
        ,p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po)
        ,p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po)
        ,p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po)
        ,p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po),p(po));

        nr = 0;
        SequentialTransition pq = new SequentialTransition(c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c()
        ,c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c()
        ,c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c()
        ,c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c(),c());
        sq.play();
        pq.play();
    
    }

    public SequentialTransition c()
    {
        Line line = new Line(0, 0, crd[0][nr], crd[1][nr]);
        PathTransition lineTransition = new PathTransition(Duration.millis(1), line, point);
        SequentialTransition path = new SequentialTransition(lineTransition,new PauseTransition(Duration.millis(1999)));
        nr++;
        return path;
    }

    public PathTransition p(int i)
    {
        PathTransition path = new PathTransition();
            path.setNode(rectangle);
            path.setDuration(Duration.seconds(2));
            path.setPath(setRndCurve(i));
            path.setCycleCount(1);
            po++;
            if(po == 4)
            {
                po = 0;
            }
        return path;
    }



    public CubicCurve setRndCurve(int posi)
    {
        Random random = new Random();
        CubicCurve curve = new CubicCurve();
        curve.setControlX1(random.nextInt((int) xHalf) + xHalf/2);
        curve.setControlY1(random.nextInt((int) yHalf) + yHalf/2);
        switch(posi)
        {
            case 0:
            curve.setStartX(xHalf);
            curve.setStartY(0);
            curve.setEndX(xHalf*2);
            curve.setEndY(yHalf);
            break;
            case 1:
            curve.setStartX(xHalf*2);
            curve.setStartY(yHalf);
            curve.setEndX(xHalf);
            curve.setEndY(yHalf*2);
            break;
            case 2:
            curve.setStartX(xHalf);
            curve.setStartY(yHalf*2);
            curve.setEndX(0);
            curve.setEndY(yHalf);
            break;
            case 3:
            curve.setStartX(0);
            curve.setStartY(yHalf);
            curve.setEndX(xHalf);
            curve.setEndY(0);
            break;
        }
        
        curve.setControlX2(curve.getControlX1());
        curve.setControlY2(curve.getControlY1());
        crd[0][nr] = (int) curve.getControlX1();
        crd[1][nr] = (int) curve.getControlY1();
        nr++;

        return curve;
    }
    
    public static void main(String[] args) {
        launch();
    }
}