package com.patsi.main;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class SmartHomeTest {
    @Test
    public void testStream() {
        List<String> testList =  new ArrayList<>(List.of("Batmy", "Patsi", "Musashi", "Batmy"));
        testList.add(null);

        Map<Character, List<String>> testList2 = testList.stream()
            .filter(Objects :: nonNull)
            .collect(
                Collectors.groupingBy(s -> s.toString().charAt(0),
                Collectors.mapping(s -> s.equals("Batmy") ? "Batmy Loves Patsi" : s, Collectors.toList()))
            );


        System.out.print(testList2);
    }

    @Test
    void testObjectStream() {
        List<Map<String, Object>> objs = List.of(
            Map.of("name", "Batmy",
                "age", 1000,
                "sex", "M"),
            Map.of("name", "Patsi",
                "age", 0,
                "sex", "F"),
            Map.of("name", "mu",
                "age", 10000000,
                "sex", "M"),
            Map.of("name", "unknown")
        );

        List<Map<String, Object>> objs2 = objs.stream()
            .filter(s -> s.get("sex") == "M")
            .collect(toList());
            System.out.println(objs2);

    }
}
