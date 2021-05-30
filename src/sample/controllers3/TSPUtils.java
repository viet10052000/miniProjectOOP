package sample.controllers3;


import sample.controllers3.TSPGene;
import java.util.ArrayList;
import java.util.Random;

public class TSPUtils {

    private static final int WIDTH = 1250;
    private static final int HEIGHT = 800;
    private final static Random R = new Random(10000);


    //    // tạo 60 thành phố bất kỳ
    static ArrayList<sample.controllers3.TSPGene> CITIES = generateData(15);

    public void setCITIES(sample.controllers3.TSPGene[] CITIES, int numDataPoints) {
        sample.controllers3.TSPUtils.CITIES = generateData(numDataPoints);
    }

    public TSPUtils() {
    }

    public int getCITIES(){
        return sample.controllers3.TSPUtils.CITIES.size();
    }

    private static ArrayList<sample.controllers3.TSPGene> generateData(final int numDataPoints) {
        final ArrayList<sample.controllers3.TSPGene> data = new ArrayList<>();
        for(int i = 0; i < numDataPoints; i++) {
            data.add(new TSPGene(sample.controllers3.TSPUtils.randomIndex(WIDTH), sample.controllers3.TSPUtils.randomIndex(HEIGHT)));
        }
        return data;
    }

    static int randomIndex(final int limit) {
        return R.nextInt(limit);
    }

}

