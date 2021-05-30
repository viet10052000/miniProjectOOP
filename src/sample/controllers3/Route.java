package sample.controllers3;

import java.util.ArrayList;
import java.util.Collections;

public class Route {
    public ArrayList<TSPGene> cities = new ArrayList<TSPGene>();

    public Route() {
    }

    public Route(ArrayList<TSPGene> cities)
    {
        this.cities.addAll(cities);
        Collections.shuffle(this.cities);
    }

    public Route(Route route){
        for(int i = 0 ; i< route.cities.size() ; i++)
        {
            cities.add(route.cities.get(i));
        }
    }

    public double getFullRouteDistance()
    {
        double fullDistance = 0;
        for (int i = 0 ; i < cities.size();i++)
        {
            if (i+1==cities.size()){
                fullDistance+=cities.get(i-1).distance(cities.get(i));
            }else{
                fullDistance+=cities.get(i).distance(cities.get(i+1));
            }
        }
        fullDistance+=cities.get(cities.size()-1).distance(cities.get(0));
        return fullDistance;
    }

}
