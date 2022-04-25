package com.intrograph;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    @Override
    public void start(Stage stage) {
    	Pane canvas = new Pane();
    	Scene scene = new Scene(canvas, 300, 300, Color.BEIGE);
    	Rectangle rectangle = new Rectangle(50,30, Color.RED);
        rectangle.relocate(0, 100);
        Circle circle = new Circle(20, Color.BLUE);
        circle.relocate(150, 0);
        
        canvas.getChildren().add(rectangle);
        canvas.getChildren().add(circle);
        
        stage.setTitle("Moving rectangle + circle");
        stage.setScene(scene);
        stage.show();
        
        Bounds bounds = canvas.getBoundsInLocal();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), 
                new KeyValue(rectangle.layoutXProperty(), bounds.getMaxX()-rectangle.getWidth())));

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(3),
                new KeyValue(circle.layoutYProperty(), bounds.getMaxY()-circle.getRadius())));
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        timeline2.play();
    }
    
    public static void main(String[] args) {
        launch();
    }
}