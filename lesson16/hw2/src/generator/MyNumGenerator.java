package generator;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyNumGenerator {
    private final int numberOfElements;
    private final int minNumber;
    private final int maxNumber;

    public MyNumGenerator(int numberOfElements, int minNumber, int maxNumber) {
        this.numberOfElements = numberOfElements;
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    public List<Integer> generateList() {
        return IntStream
                .range(minNumber, maxNumber)
                .limit(numberOfElements)
                .map(num -> ThreadLocalRandom.current().nextInt(minNumber, maxNumber))
                .boxed()
                .collect(Collectors.toList());
    }

    public Set<Integer> generateSet() {
        if (maxNumber - minNumber < numberOfElements) {
            throw new UnsupportedOperationException();
        }

        Set<Integer> set = new LinkedHashSet<>();

        while (set.size() < numberOfElements) {
            set.add(ThreadLocalRandom.current().nextInt(minNumber, maxNumber));
        }

        return set;
    }
}

