import entity.Baggage;
import entity.Passenger;
import entity.Ticket;
import entity.VipTicket;
import info.FlightInfo;
import menu.Drinks;
import menu.Meal;
import menu.Menu;

public class TicketRunner {
    public static void main(String[] args) {
        Passenger pasPetrov = new Passenger("Василий", "Петров", 123456);
        Passenger pasSidorov = new Passenger("Саша", "Сидоров", 654321);

        FlightInfo flightFromKyivToParis = new FlightInfo(23675, "Киев", "Париж", 12.30, 1000);

        Ticket t1 = new Ticket(7, flightFromKyivToParis, pasPetrov);

        Ticket t2 = new VipTicket(
                2,
                flightFromKyivToParis,
                pasSidorov,
                new Meal(Menu.DESSERT, Drinks.YES),
                new Baggage(2, 20)
        );

        System.out.println(t1 + "\n" + t2);
    }
}
