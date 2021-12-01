package org.hw;

import org.hw.entity.MyPhoneBook;
import org.hw.utils.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        MyPhoneBook phoneBook = new MyPhoneBook();
        menu.menu(phoneBook);
    }
}
