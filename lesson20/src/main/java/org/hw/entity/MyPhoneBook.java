package org.hw.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyPhoneBook {
    private final List<PhoneRecord> phoneNumbers;

    public MyPhoneBook() {
        this.phoneNumbers = new ArrayList<>();
    }

    public void addPhoneNumber(String name, String phone) {
        phoneNumbers.add(new PhoneRecord(name, phone));
    }

    public void printPhoneBook(List<PhoneRecord> phoneNumbers) {
        phoneNumbers.forEach(System.out::println);
    }

    public void sortByName() {
         phoneNumbers.sort(new Comparator<PhoneRecord>() {
            @Override
            public int compare(PhoneRecord pr1, PhoneRecord pr2) {
                return pr1.getName().compareTo(pr2.getName());
            }
        });
    }

    public void sortByPhoneNumber() {
        phoneNumbers.sort(Comparator.comparing(PhoneRecord::getPhone));
    }

    //для тестов
    public List<PhoneRecord> getPhoneNumbers() {
        return phoneNumbers;
    }

    //сделал его public для того, чтобы можно было писать тесты для телефонной книги
    public class PhoneRecord {
        private final String name;
        private final String phone;

        PhoneRecord(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        @Override
        public String toString() {
            return String.format("%-10s:%10s", name, phone);
        }
    }
}
