package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Journal;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Parser {
    public List<Journal> getJournal() throws URISyntaxException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URI resource = Objects.requireNonNull(Journal.class.getClassLoader().getResource("journal_ru.json")).toURI();

        return Arrays.asList(objectMapper.readValue(new File(resource), Journal[].class));
    }
}
