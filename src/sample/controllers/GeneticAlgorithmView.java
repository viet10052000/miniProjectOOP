package sample.controllers;

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

import static sample.controllers.GeneticAlgorithmController.WIDTH;
import static sample.controllers.GeneticAlgorithmController.HEIGHT;


public class GeneticAlgorithmView extends Region {
    public Canvas canvas;
    public final TSPPopulation population;

    private double Bshort, Ashort;
    private static int check = 15;

    public GeneticAlgorithmView(){
        this.population = new TSPPopulation(TSPUtils.CITIES, 5000);
        this.canvas = new Canvas(WIDTH,GeneticAlgorithmController.HEIGHT);
        this.getChildren().addAll(canvas);
    }

    public void draw(Label label2){
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        Timeline t1 = new Timeline();
        t1.setCycleCount(Animation.INDEFINITE);
        t1.setAutoReverse(true);
        t1.getKeyFrames().add(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if ( check % 15 == 0){
                    Bshort = getShortestPath();
                }
                check++;
                updatePpulation();
                Ashort = getShortestPath();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawXY(gc);
                shortestPath(label2);
                if(Ashort == Bshort){
                    drawBestChromosome2(gc);
                }else {
                    drawBestChromosome(gc);
                }
            }
        }));
        t1.play();
    }

    private void updatePpulation() {
        this.population.update();
    }

    private void shortestPath(Label label2){
        label2.setText("Shortest Path: " + String.format("%.2f", this.population.getAlpha().getDistance()));
    }

    private double getShortestPath(){
        return this.population.getAlpha().getDistance();
    }

    public void totalCities(Label label1){
        final java.util.List<TSPGene> chromosome = this.population.getAlpha().getChromosome();
        label1.setText("Total Of Cities: " + chromosome.size());
    }

    private void drawBestChromosome(javafx.scene.canvas.GraphicsContext gc) {
        final java.util.List<TSPGene> chromosome = this.population.getAlpha().getChromosome();
        for(int i = 0; i < chromosome.size() - 1; i++) {
            TSPGene gene = chromosome.get(i);
            TSPGene neighbor = chromosome.get(i + 1);
            gc.setLineWidth(1);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(gene.getX(), gene.getY(), neighbor.getX(), neighbor.getY());
        }
        gc.setFill(Color.RED);
        for(final TSPGene gene : chromosome) {
            gc.fillOval(gene.getX(), gene.getY(), 10, 10);
        }
    }

    private void drawBestChromosome2(javafx.scene.canvas.GraphicsContext gc) {
        final java.util.List<TSPGene> chromosome = this.population.getAlpha().getChromosome();
        for(int i = 0; i < chromosome.size() - 1; i++) {
            TSPGene gene = chromosome.get(i);
            TSPGene neighbor = chromosome.get(i + 1);
            gc.setLineWidth(3);
            gc.setStroke(Color.GREEN);
            gc.strokeLine(gene.getX(), gene.getY(), neighbor.getX(), neighbor.getY());
        }
        gc.setFill(Color.RED);
        for(final TSPGene gene : chromosome) {
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
