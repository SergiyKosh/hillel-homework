import entity.Journal;
import utils.Correlations;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Correlations operations = new Correlations();
        String maxEvent = new ArrayList<>(operations.getMaxCorrelation().keySet()).get(0);
        String minEvent = new ArrayList<>(operations.getMinCorrelation().keySet()).get(0);
        String finalEvent = maxEvent + "-" + minEvent;

        System.out.println("\nAll correlations:\n---------------------------------------");
        System.out.print(stringForPrintMap(operations.getCorrelations()));
        System.out.println("---------------------------------------");

        System.out.println("\nCorrelations from -0.1 until 0.1:\n---------------------------------------");
        System.out.print(stringForPrintMap(operations.getFinalCorrelations()));
        System.out.println("---------------------------------------");

        List<Journal> lst = operations.getFoundedCorrelations();
        List<Integer> in = operations.tableFor(finalEvent, lst);

        System.out.println("\nCorrelation for '" + finalEvent + "':\n---------------------------------------");
        System.out.printf("|%-25s:%-11f|\n", finalEvent, operations.phi(in));
        System.out.println("---------------------------------------");
    }

    private static String stringForPrintMap(Map<String, Double> map) {
        return map.entrySet().stream()
                .map(x -> String.format("|%-25s:%-11f|\n", x.getKey(), x.getValue()))
                .collect(Collectors.joining());
    }
}
