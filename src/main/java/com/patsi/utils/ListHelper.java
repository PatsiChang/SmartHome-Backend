package com.patsi.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListHelper {
    public static <T> List<T> newList() {
        return new ArrayList<T>();
    }
    public static <T> List<T> newListWithParam(T... elements) {
        return Stream.of(elements).collect(Collectors.toList());
    }
    public static <T> List<T> setToList(Set<T> values) {
        return new ArrayList<T>(values);
    }
    public static <T> Set<T> newSet() {
        return new HashSet<T>();
    }

}
