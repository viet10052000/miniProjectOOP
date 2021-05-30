package sample.controllers2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class hillClimbingAlgorithmView extends Region {
    private static final int WIDTH = 1277;
    private static final int HEIGHT = 900;
    private Canvas canvas;

    private final hillClimbingAlgorithm hillclimbingalgorithm;
    private static Route currentRoute = new Route();
    private static int check = 1000;
    private double Bshort, Ashort;
    private static int iterationCounter;

    public hillClimbingAlgorithmView(){
        this.hillclimbingalgorithm = new hillClimbingAlgorithm();
        this.canvas = new Canvas(WIDTH,HEIGHT);
        this.getChildren().addAll(canvas);
    }

    public void draw(Label label, Label label2){
        currentRoute = new Route(TSPUtils.CITIES);
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        Timeline t1 = new Timeline();
        t1.setCycleCount(Animation.INDEFINITE);
        t1.setAutoReverse(true);
        t1.getKeyFrames().add(new KeyFrame(Duration.millis(2), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if ( check % 1000 == 0){
                    Bshort = getShortestPath();
                }
                check++;
                update(label2);
                Ashort = getShortestPath();
                shortestPath(label);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawXY(gc);
                if( Ashort == Bshort ){
                    drawBestRoute2(gc);
                }else{
                    drawBestRoute(gc);
                }
            }
        }));
        t1.play();
    }


    private void shortestPath(Label label2){
        label2.setText("Shortest Path: " + String.format("%.2f", currentRoute.getFullRouteDistance()));
    }

    private double getShortestPath() {
        return currentRoute.getFullRouteDistance();
    }

    public void totalCities(Label label){
        TSPUtils tspUtils = new TSPUtils();
        tspUtils.getCITIES();
        label.setText("Total Of Cities: " + tspUtils.getCITIES());
    }

    private void update(Label label){
        Route neighborhoodSolution;
        neighborhoodSolution = this.hillclimbingalgorithm.getNeighborhoodSolution(new Route(currentRoute)); // on crée une solution voisine
        if (neighborhoodSolution.getFullRouteDistance() <= currentRoute.getFullRouteDistance()) {
            iterationCounter = 0;
            currentRoute = new Route(neighborhoodSolution);
        } else {
            if(iterationCounter < 10000){
                label.setText("Iteration : " + iterationCounter);
            }else{
                label.setText("Iteration : MAX" );
            }
            iterationCounter++;
        }
    }


    private void drawBestRoute(GraphicsContext gc) {
        for(int i = 0; i < currentRoute.cities.size() - 1; i++) {
            TSPGene gene = currentRoute.cities.get(i);
            TSPGene neighbor = currentRoute.cities.get(i + 1);
            gc.setLineWidth(1);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(gene.getX(), gene.getY(), neighbor.getX(), neighbor.getY());
        }
        gc.setFill(javafx.scene.paint.Color.RED);
        for(final TSPGene gene : currentRoute.cities) {
            gc.fillOval(gene.getX(), gene.getY(), 10, 10);
        }
    }

    private void drawBestRoute2(GraphicsContext gc) {
        for(int i = 0; i < currentRoute.cities.size() - 1; i++) {
            TSPGene gene = currentRoute.cities.get(i);
            TSPGene neighbor = currentRoute.cities.get(i + 1);
            gc.setLineWidth(3);
            gc.setStroke(Color.GREEN);
            gc.strokeLine(gene.getX(), gene.getY(), neighbor.getX(), neighbor.getY());
        }
        gc.setFill(javafx.scene.paint.Color.RED);
        for(final TSPGene gene : currentRoute.cities) {
            gc.fillOval(gene.getX(), gene.getY(), 10, 10);
        }
    }

    private void drawXY(GraphicsContext gc){
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0,0,WIDTH,HEIGHT);
        gc.setLineWidth(0.5);
        gc.setStroke(Color.BLACK);
        for(int i = 0 ; i < WIDTH; i+=30){
            gc.strokeLine(i,0,i,HEIGHT);
        }
        for(int i = 0 ; i < HEIGHT; i+=30){
            gc.strokeLine(0,i,WIDTH,i);
        }
    }
}