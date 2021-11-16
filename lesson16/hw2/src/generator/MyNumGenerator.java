package generator;

import java.util.*;

public class MyNumGenerator {
    private int numberOfElements;
    private int minNumber;
    private int maxNumber;

    public MyNumGenerator(int numberOfElements, int minNumber, int maxNumber) {
        this.numberOfElements = numberOfElements;
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public int getMinNumber() {
        return minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public List<Integer> generateList(int numberOfElements, int minNumber, int maxNumber) {
        return new Random()
                .ints(minNumber, maxNumber)
                .limit(numberOfElements)
                .collect(ArrayList::new, List::add, Collection::addAll);
    }

    public Set<Integer> generateSet(int numberOfElements, int minNumber, int maxNumber) {
        if (maxNumber - minNumber < numberOfElements) {
            throw new UnsupportedOperationException();
        }

        List<Integer> randomNumbers = new ArrayList<>();

        for (int i = minNumber; i < maxNumber; i++) {
            randomNumbers.add(i);
        }

        Collections.shuffle(randomNumbers);

        return new LinkedHashSet<>(randomNumbers);
    }
}
