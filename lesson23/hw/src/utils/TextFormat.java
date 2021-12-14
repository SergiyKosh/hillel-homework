package utils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextFormat {
    public List<String> sort(List<String> listForSorting) {
        return listForSorting.stream()
                .distinct()
                .collect(Collectors.groupingBy(x -> x.charAt(0)))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList()).stream()
                .map(lst -> String.join(" ", lst))
                .collect(Collectors.toList());
    }
}
