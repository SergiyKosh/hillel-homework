import entity.Journal;
import utils.OperationsWithEvents;
import utils.Parser;
import utils.Phi;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Parser parser = new Parser();
//        parser.getJournal().forEach(System.out::println);
        OperationsWithEvents operations = new OperationsWithEvents();
        List<Journal> lst = new ArrayList<>(operations.getFoundedCorrelations());
        List<Integer> in = new Phi().tableFor(operations.getMaxCorrelation() + "-" + operations.getMinCorrelation(), operations.getFoundedCorrelations());
        System.out.println(in);
        Double corr = new Phi().phi(in);
        System.out.println(corr);
    }
}
