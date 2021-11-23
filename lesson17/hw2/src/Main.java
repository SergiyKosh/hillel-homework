import entity.Customer;
import utils.Reader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        Customer cust = new Customer();
        cust.getCustomers().putAll(reader.loadCustomersList());
        System.out.println(cust.getCustomers());
    }
}
