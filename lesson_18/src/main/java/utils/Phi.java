package utils;

import entity.Journal;

import java.util.ArrayList;
import java.util.List;

public class Phi {
    public Double phi(List<Integer> table) {
        return (table.get(3) * table.get(0) - table.get(2) * table.get(1)) /
                Math.sqrt((table.get(2) + table.get(3)) *
                        (table.get(0) + table.get(1)) *
                        (table.get(1) + table.get(3)) *
                        (table.get(0) + table.get(2)));
    }

    public List<Integer> tableFor(String event, List<Journal> events) {
        List<Integer> matrix = new ArrayList<>(List.of(0, 0, 0, 0));
        events.forEach(x -> {
            int index = 0;
            if (x.getEvents().contains(event)) {
                index += 1;
            }
            if (x.isSquirrel()) {
                index += 2;
            }
            matrix.set(index, matrix.get(index) + 1);
        });
        return matrix;
    }
}
