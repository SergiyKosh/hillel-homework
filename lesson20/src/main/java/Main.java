import entity.MyPhoneBook;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyPhoneBook phoneBook = new MyPhoneBook();
        String name = "";
        String phone;

        System.out.print("Input name of new contact: ");
//        name = new Scanner(System.in).nextLine();
//        System.out.print("Input phone number: ");
//        phone = scanner.nextLine();
        phone = " ";
        phoneBook.addPhoneNumber(name, phone);
    }
}
