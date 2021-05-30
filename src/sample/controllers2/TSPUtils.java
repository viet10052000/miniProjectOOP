package sample.controllers2;

import java.util.ArrayList;
import java.util.Random;

public class TSPUtils {

    private static final int WIDTH = 1250;
    private static final int HEIGHT = 800;
    private final static Random R = new Random(10000);


//    // tạo 30 thành phố bất kỳ
    static ArrayList<TSPGene> CITIES = generateData(15);

    public void setCITIES(TSPGene[] CITIES, int numDataPoints) {
        TSPUtils.CITIES = generateData(numDataPoints);
    }

    public TSPUtils() {
    }

    public int getCITIES(){
        return TSPUtils.CITIES.size();
    }

    private static ArrayList<TSPGene> generateData(final int numDataPoints) {
        final ArrayList<TSPGene> data = new ArrayList<>();
        for(int i = 0; i < numDataPoints; i++) {
            data.add(new TSPGene(TSPUtils.randomIndex(WIDTH), TSPUtils.randomIndex(HEIGHT)));
        }
        return data;
    }

    static int randomIndex(final int limit) {
        return R.nextInt(limit);
    }

}
