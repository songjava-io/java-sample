package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class TestPersonCollect {

    @Data
    @AllArgsConstructor
    private class Person {
        private String id;
        private String name;
    }

    @org.junit.jupiter.api.Test
    public void test() {
        List<Person> a = Arrays.asList(new Person("google", "구글"), new Person("naver",  null));
        List<Person> b = Arrays.asList(new Person("naver", "네이버"), new Person("spring", "Spring Boot"));

        List<Person> list = new ArrayList<>();

        list.addAll(a);
        list.addAll(b);

        ArrayList<Person> result = list.stream().collect(Collectors.collectingAndThen(Collectors.toMap(Person::getId, Function.identity(), (left, right) -> {
            left.setName(right.getName());
            return left;
        }), m -> new ArrayList<>(m.values())));
        log.debug("result : {}", result);
    }
}
