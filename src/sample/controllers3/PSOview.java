package sample.controllers3;

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


import static sample.controllers3.PSOConstants.maximumIterations;


public class PSOview extends Region {
    private static final int WIDTH = 1277;
    private static final int HEIGHT = 900;
    private Canvas canvas;

    private final PSOptimization psoPtimization;
    private static Route route = new Route();
    private static double w = 0;
    private static int counter = 0;

    private static int check = 10;
    private double Bshort, Ashort;

    public PSOview() {
        route = new Route(TSPUtils.CITIES);
        this.psoPtimization = new PSOptimization(route);
        this.canvas = new Canvas(WIDTH,HEIGHT);
        this.getChildren().addAll(canvas);
    }

    public void draw(Label label, Label label2) {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        Timeline t1 = new Timeline();
        t1.setCycleCount(maximumIterations*2);
        t1.setAutoReverse(true);
        t1.getKeyFrames().add(new KeyFrame(Duration.millis(50), actionEvent -> {
            if ( check % 10 == 0){
                Bshort = getShortestPath();
            }
            check++;
            update(counter,w,label2);
            Ashort = getShortestPath();
            shortestPath(label);
            counter++;
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            drawXY(gc);
            if( Ashort == Bshort){
                drawBestRoute2(gc);
            }else {
                drawBestRoute(gc);
            }
        }));
        t1.play();
    }

    private double getShortestPath() {
        return this.psoPtimization.gBest.getFullRouteDistance();
    }

    private void shortestPath(Label label2){
        label2.setText("Shortest Path: " + String.format("%.2f", this.psoPtimization.gBest.getFullRouteDistance()));
    }

    public void totalCities(Label label){
        sample.controllers3.TSPUtils tspUtils = new sample.controllers3.TSPUtils();
        tspUtils.getCITIES();
        label.setText("Total Of Cities: " + tspUtils.getCITIES());
    }

    private void update(int counter,double w, Label label) {
        for(int i = 0 ; i < this.psoPtimization.particlesList.size()-1 ; i ++)
        {
            this.psoPtimization.particlesList.get(i).getBest();
        }
        this.psoPtimization.sort();
        if(this.psoPtimization.particlesList.get(0).pBest.getFullRouteDistance() < this.psoPtimization.gBest.getFullRouteDistance())
        {
            this.psoPtimization.gBest = new Route(this.psoPtimization.particlesList.get(0).pBest);
        }
        w = PSOConstants.high - (((double) counter) / maximumIterations) * (PSOConstants.high - PSOConstants.low);
        for(int i = 0 ; i < this.psoPtimization.particlesList.size() - 1 ; i++)
        {
            for(int j = 0 ; j< particle.problemSize-1 ; j++)
            {
                double r1 = PSOConstants.random.nextDouble();
                double r2 = PSOConstants.random.nextDouble();
                double vel = this.psoPtimization.particlesList.get(i).velocity.getVelocity().get(j);
                double loc = this.psoPtimization.particlesList.get(i).location.getLocations().get(j);
                double pBestLoc = this.psoPtimization.particlesList.get(i).locationPBest.getLocations().get(j);
                double gBestLoc = this.psoPtimization.gBestLocation.getLocations().get(j);
                double newVel = (w * vel) + (r1 * PSOConstants.c1) * (pBestLoc - loc) + (r2 * PSOConstants.c2) * (gBestLoc - loc);
                this.psoPtimization.particlesList.get(i).velocity.getVelocity().set(j,newVel);
                double newPos = loc + newVel;
                this.psoPtimization.particlesList.get(i).location.locations.set(j,newPos);
                this.psoPtimization.particlesList.get(i).swapWithLocation((int)Math.abs(loc-newPos));
            }
        }
        System.out.println("Epoch "+counter+" has been finished");
        label.setText("MaximumIterations : " + counter);
        System.out.println("Approached Global minimum : "+(int)this.psoPtimization.gBest.getFullRouteDistance()+" km");
    }

    private void drawBestRoute(final GraphicsContext g) {
        for(int i = 0; i < this.psoPtimization.gBest.cities.size() - 1; i++) {
            sample.controllers3.TSPGene gene = this.psoPtimization.gBest.cities.get(i);
            sample.controllers3.TSPGene neighbor = this.psoPtimization.gBest.cities.get(i + 1);
            g.setLineWidth(1);
            g.setStroke(Color.BLACK);
            g.strokeLine(gene.getX(), gene.getY(), neighbor.getX(), neighbor.getY());
        }
        g.setFill(Color.RED);
        for(final TSPGene gene : this.psoPtimization.gBest.cities) {
            g.fillOval(gene.getX(), gene.getY(), 10, 10);
        }
    }

    private void drawBestRoute2(final GraphicsContext g) {
        for(int i = 0; i < this.psoPtimization.gBest.cities.size() - 1; i++) {
            sample.controllers3.TSPGene gene = this.psoPtimization.gBest.cities.get(i);
            sample.controllers3.TSPGene neighbor = this.psoPtimization.gBest.cities.get(i + 1);
            g.setLineWidth(3);
            g.setStroke(Color.GREEN);
            g.strokeLine(gene.getX(), gene.getY(), neighbor.getX(), neighbor.getY());
        }
        g.setFill(Color.RED);
        for(final TSPGene gene : this.psoPtimization.gBest.cities) {
            g.fillOval(gene.getX(), gene.getY(), 10, 10);
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
