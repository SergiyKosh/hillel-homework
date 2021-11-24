import utils.OperationsWithEvents;
import utils.Parser;
import utils.Phi;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Parser parser = new Parser();
        parser.getJournal().forEach(System.out::println);
        Phi phi = new Phi();
        System.out.println(phi.phi(phi.tableFor("ел пиццу", parser.getJournal())));
        OperationsWithEvents operations = new OperationsWithEvents();
        System.out.println(operations.getAllEvents(parser.getJournal()).size());
    }
}
