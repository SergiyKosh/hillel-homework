import utils.Reader;

import java.io.IOException;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        new Reader().loadCustomersList()
                .forEach((k, v) ->
                        System.out.printf(
                                "%s\n%s\n",
                                k,
                                v.entrySet().stream()
                                        .map(x -> x.getKey() + " " + x.getValue() + "\n")
                                        .collect(Collectors.joining())
                        ));
    }
}
