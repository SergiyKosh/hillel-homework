package com.example;

import com.example.entity.DayJournal;
import com.example.json.JsonReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = Files.lines(Path.of("src/main/resources/journal_ru.json")).collect(Collectors.joining());
        JsonReader reader = new JsonReader();
        ArrayList<DayJournal> lst = (ArrayList<DayJournal>) reader.getDayJournal(path);
        lst.forEach(System.out::println);
    }
}
