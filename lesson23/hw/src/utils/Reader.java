package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Reader {
    public List<String> readFile() throws IOException {
        return Files.lines(Paths.get("src/resources/text.txt"))
                .map(str -> str.split(" "))
                .map(List::of)
                .flatMap(Collection::stream)
                .map(String::toLowerCase)
                .map(letter -> letter.replaceAll("[^A-Za-zА-Яа-я]", ""))
                .filter(letter -> letter.length() > 0)
                .collect(Collectors.toList());
    }
}
