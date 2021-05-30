package sample.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class TSPUtils {

    private static final int WIDTH =1250;
    private static final int HEIGHT = 850;
    private int numDataPoints;
    private final static Random R = new Random(10000);

    // tạo 30 thành phố bất kỳ
    static TSPGene[] CITIES = generateData(40);

    public void setCITIES(TSPGene[] CITIES, int numDataPoints) {
        TSPUtils.CITIES = generateData(numDataPoints);
    }

    public TSPUtils() {
    }


    // khởi tạo thành với tọa độ bất kỳ
    private static TSPGene[] generateData(final int numDataPoints) {
        final TSPGene[] data = new TSPGene[numDataPoints];
        for(int i = 0; i < numDataPoints; i++) {
            data[i] = new TSPGene(TSPUtils.randomIndex(WIDTH), TSPUtils.randomIndex(HEIGHT));
        }
        return data;
    }

    static int randomIndex(final int limit) {
        return R.nextInt(limit);
    }

    static<T> List<T>[] split(final List<T> list) {
        final List<T> first = new ArrayList<>();
        final List<T> second = new ArrayList<>();
        final int size = list.size();
        final int partitionIndex = 1 + TSPUtils.randomIndex(list.size());
        IntStream.range(0, size).forEach(i -> {
            if(i < (size+1)/partitionIndex) {
                first.add(list.get(i));
            } else {
                second.add(list.get(i));
            }
        });
        return (List<T>[]) new List[] {first, second};
    }
}
