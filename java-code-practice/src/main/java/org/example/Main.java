package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
                .appendPattern("dd-MMM-yyyy").toFormatter(Locale.ENGLISH);

        LocalDate.parse("12-JAN-2014", formatter);
        System.out.println( LocalDate.parse("12-JAN-2014", formatter));
    }
}