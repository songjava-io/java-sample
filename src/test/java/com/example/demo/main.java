package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class main {

    static class Person{
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Test
    public  void main() {
        List<Person> personList = Arrays.asList(new Person("song", 34), new Person("song", 43),
                new Person("mary", 81), new Person("john", 12), new Person("bob", 22));

        System.out.println("list of person objects - " + personList);

        Stream<Person> personStream = personList.stream();

        Map<String, List<Person>> result = personStream.collect(Collectors.teeing(
                Collectors.filtering(p -> p.name.equals("song") , Collectors.toList()),
                Collectors.filtering(p -> p.age == 34, Collectors.toList()),
                (res1, res2) -> {
                    Map<String, List<Person>> map = new HashMap<>();
                    map.put("EvenAgedPersons", res1);
                    map.put("OddAgedPersons", res2);
                    return map;
                }));

        System.out.println("Result of applying teeing - " + result);

    }
}