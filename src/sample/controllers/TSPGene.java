package sample.controllers;

import java.util.Objects;

public class TSPGene {

    private final int x;
    private final int y;

    TSPGene(final int x,
            final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y+ ")";
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    double distance(final TSPGene other) {
        int xDistance = Math.abs(getX() - other.getX());
        int yDistance = Math.abs(getY() - other.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        return distance;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TSPGene gene = (TSPGene) o;
        return this.x == gene.x &&
                this.y == gene.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
