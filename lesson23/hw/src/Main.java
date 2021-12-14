import utils.Reader;
import utils.TextFormat;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> sortedText = new TextFormat().sort(new Reader().readFile());
        sortedText.forEach(System.out::println);
    }
}
