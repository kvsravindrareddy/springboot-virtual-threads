package com.veera.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ListMerge {
    public static void main(String[] args) {
        List<String> lOne = new ArrayList<>();
        lOne.add("B1");
        List<String> lTwo = new ArrayList<>();
        lTwo.add("A");

        Stream.concat(lOne.stream(), lTwo.stream()).sorted().forEach(System.out::println);
    }
}
