package org.hw.utils;

import java.util.Scanner;

public class Menu {
    public void menu(org.hw.entity.MyPhoneBook phoneBook) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s",
                "Menu:",
                "1 - Add new contact",
                "2 - Print phone book",
                "3 - Sort by name",
                "4 - Sort by phone number",
                "0 - Exit",
                "-> ");
        String o = scanner.nextLine();
        switch(o) {
            case "1":
                System.out.print("Input name: ");
                String name = scanner.nextLine();
                System.out.print("Input phone number: ");
                String phone = scanner.nextLine();
                phoneBook.addPhoneNumber(name, phone);
                menu(phoneBook);
                break;
            case "2":
                phoneBook.printPhoneBook();
                menu(phoneBook);
                break;
            case "3":
                phoneBook.sortByName();
                phoneBook.printPhoneBook();
                menu(phoneBook);
                break;
            case "4":
                phoneBook.sortByPhoneNumber();
                phoneBook.printPhoneBook();
                menu(phoneBook);
                break;
            case "0":
                break;
            default:
                System.out.println("\nThis menu item does not exist. Try again!");
                menu(phoneBook);
                break;
        }
    }
}
