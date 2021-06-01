package com.olympic.cis143.m06.students.homework;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] a) {
        Main main = new Main();
        main.orderNamesAndConvertToUppercase();
        main.calculateAddListValues();
        main.findTheOldestPerson();
        main.findPeople21OrOver();
        main.orderByAge();
    }

    private void orderNamesAndConvertToUppercase() {
        List<String> names = Arrays.asList("bob", "steve", "alice");
        List<String> sortedAndUppercase = new HomeworkStuff().orderNamesAndConvertToUppercase(names);
        if (   !sortedAndUppercase.get(0).equals("ALICE")
            || !sortedAndUppercase.get(1).equals("BOB")
            || !sortedAndUppercase.get(2).equals("STEVE")) throw new RuntimeException("Namme have not been sorted and are not uppercase.");
        System.out.println(">> orderNamesAndConvertToUppercase passed");
    }

    private void calculateAddListValues() {
        Integer result = new HomeworkStuff().calculateAddListValues(Arrays.asList(1, 2, 3, 4, 5));
        if (!result.equals(15)) {
            throw new RuntimeException(":{ Calculating an array list did not work!");
        }
        System.out.println(">> calculateList passed");
    }

    private void findTheOldestPerson() {
        List<Person> people = Arrays.asList(new Person(20,"Joe"), new Person(23, "Samantha"), new Person(21, "Bobby"));
        Optional<Person> samantha = new HomeworkStuff().findTheOldestPerson(people);
        if (!samantha.get().getName().equals("Samantha")) throw new RuntimeException("The oldest should be Samantha");
        System.out.println(">>  findTheOldestPerson passed");
    }

    private void findPeople21OrOver() {
        List<Person> people = Arrays.asList(new Person(20,"Joe"), new Person(23, "Samantha"), new Person(21, "Bobby"));
        List<Person> over21 = new HomeworkStuff().findPeople21OrOver(people);
        if (over21.size() != 2 ) throw new RuntimeException("Result should only have 2 elements in it.");
        if (over21.stream().filter((p) -> p.getAge() < 21).count() > 0) throw new RuntimeException("Results should only be 21 and above.");
        System.out.println(">> findPeople21OrOver passed");
    }

    private void orderByAge() {
        List<Person> people = Arrays.asList(new Person(20,"Joe"), new Person(23, "Samantha"), new Person(21, "Bobby"), new Person(19, "Mary"));
        List<Person> sortedPeople = new HomeworkStuff().orderByAge(people);
        if (   sortedPeople.get(0).getAge() != 19
            || sortedPeople.get(1).getAge() != 20
            || sortedPeople.get(2).getAge() != 21
            || sortedPeople.get(3).getAge() != 23) throw new RuntimeException("People not sorted correctly.");
         System.out.println(">> orderByAge passed");
    }
}
