import utils.Parser;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Parser parser = new Parser();
        parser.getJournal().forEach(System.out::println);
    }
}
