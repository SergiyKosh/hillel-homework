import org.hw.entity.MyPhoneBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MyPhoneBookTest {
    private MyPhoneBook myPhoneBook;

    @BeforeEach
    void setUp() {
        myPhoneBook = new MyPhoneBook();
    }

    @Test
    void testPhoneBookIsEmptyIfNoContactsAdded() {
        List<MyPhoneBook.PhoneRecord> book = myPhoneBook.getPhoneNumbers();
        assertTrue(book.isEmpty());
    }

    @Test
    void testPhoneBookSizeIfContactsAdded() {
        int expectedSize = 2;
        myPhoneBook.addPhoneNumber("Name1", "0987654321");
        myPhoneBook.addPhoneNumber("Name2", "0739876543");
        List<MyPhoneBook.PhoneRecord> book = myPhoneBook.getPhoneNumbers();
        assertFalse(book.isEmpty());
        assertEquals(expectedSize, book.size());
    }

    @Test
    void testSortByName() {
        myPhoneBook.addPhoneNumber("Name2", "0987654321");
        myPhoneBook.addPhoneNumber("Name1", "0739876543");
        myPhoneBook.addPhoneNumber("Name0", "0739876543");
        List<MyPhoneBook.PhoneRecord> nonSortedPhoneBook = myPhoneBook.getPhoneNumbers();
        myPhoneBook.sortByName();
        List<MyPhoneBook.PhoneRecord> sortedPhoneBookByMethodFromMyPhoneBookClass = myPhoneBook.getPhoneNumbers();
        List<MyPhoneBook.PhoneRecord> sortedPhoneBookByStreamAPI = nonSortedPhoneBook.stream()
                .sorted(Comparator.comparing(MyPhoneBook.PhoneRecord::getName))
                .collect(Collectors.toList());
        assertEquals(sortedPhoneBookByStreamAPI, sortedPhoneBookByMethodFromMyPhoneBookClass);
    }

    @Test
    void testSortByPhoneNumber() {
        myPhoneBook.addPhoneNumber("Name1", "0987654321");
        myPhoneBook.addPhoneNumber("Name2", "0739876543");
        myPhoneBook.addPhoneNumber("Name3", "0967654321");
        List<MyPhoneBook.PhoneRecord> nonSortedPhoneBook = myPhoneBook.getPhoneNumbers();
        myPhoneBook.sortByPhoneNumber();
        List<MyPhoneBook.PhoneRecord> sortedPhoneBookByMethodFromMyPhoneBookClass = myPhoneBook.getPhoneNumbers();
        List<MyPhoneBook.PhoneRecord> sortedPhoneBookByStreamAPI = nonSortedPhoneBook.stream()
                .sorted(Comparator.comparing(MyPhoneBook.PhoneRecord::getPhone))
                .collect(Collectors.toList());
        assertEquals(sortedPhoneBookByStreamAPI, sortedPhoneBookByMethodFromMyPhoneBookClass);
    }

    @Test
    void testPrintPhoneBook() {
        PrintStream printStreamMock = Mockito.mock(PrintStream.class);
        System.setOut(printStreamMock);
        myPhoneBook.addPhoneNumber("qwe", "123");
        myPhoneBook.addPhoneNumber("ewq", "321");
        myPhoneBook.printPhoneBook(myPhoneBook.getPhoneNumbers());
        Mockito.verify(printStreamMock, Mockito.times(2)).println(Mockito.any(MyPhoneBook.PhoneRecord.class));
    }
}