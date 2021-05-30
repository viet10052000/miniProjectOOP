package sample.controllers2;

public class hillClimbingAlgorithm {

    public static int maximumIterations = 1000;

    public Route findShortestRoute(Route currentRoute, int iterationCounter) {
        Route neighborhoodSolution;
        while (iterationCounter < maximumIterations) {
            neighborhoodSolution = getNeighborhoodSolution(new Route(currentRoute)); // on crée une solution voisine
            if (neighborhoodSolution.getFullRouteDistance() <= currentRoute.getFullRouteDistance()) {
                iterationCounter = 0;

                currentRoute = new Route(neighborhoodSolution);
            } else {
                iterationCounter++;
            }
        }
        return currentRoute;
    }



    public Route getNeighborhoodSolution(Route route)
    {
        int random1 = 0 ;
        int random2 = 0 ;

        while(random1==random2){
            random1 = (int) (route.cities.size()* Math.random());
            random2 = (int) (route.cities.size()* Math.random());
        }
        TSPGene city_1 = route.cities.get(random1);
        TSPGene city_2 = route.cities.get(random2);
        route.cities.set(random2,city_1);
        route.cities.set(random1,city_2);
        return route;
    }
}
