package com.olympic.cis143.m06.students.homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * This lab uses the java Stream API.
 */
public class HomeworkStuff {
    /**
     * This method will:
     * 1. sort the names
     * 2. convert the names to uppercase.
     * @param names The list of unsorted names in lowercase.
     * @return The sorted list in uppercase.
     */
    public List<String> orderNamesAndConvertToUppercase(final List<String> names) {
        List<String> sortedNames = names.stream()
        		.sorted()
        		.map(String::toUpperCase)
        		.collect(Collectors.toList());
        return sortedNames;
    }

    /**
     * This adds all the integers in a list together.
     * @param integrers A list of integers
     * @return The total.
     */
    public Integer calculateAddListValues(final List<Integer> integers) {
    	int sum = integers.stream().reduce(0, (a, b) -> a + b);
        return sum;
    }

    /**
     * From the list, filter and find the oldest person.
     *
     * @param people A list of people.
     * @return The oldest person.
     */
    public Optional<Person> findTheOldestPerson(final List<Person> people) {
        Optional<Person> oldestPerson = people.stream().max((p1, p2) -> p1.getAge() - p2.getAge());
        return oldestPerson;
    }

    /**
     * Filters out anyone under 21.
     * @param people A list of people.
     * @return Only people 21 or over.
     */
    public List<Person> findPeople21OrOver(final List<Person> people) {
    	List<Person> filteredPeople = people.stream()
    	.filter(person -> person.getAge() >= 21)
    	.collect(Collectors.toList());
    	/*
    	for(Person p : people) {
    		if(p.getAge() > 20) {
    			filteredPeople.add(p);
    		}
    	}
    	*/
    	return filteredPeople;
    	
    }
    /**
     * Orders the people by age.
     * @param people A list of people to order by age.
     * @return The ordered list of people by age.
     */
    public List<Person> orderByAge(final List<Person> people) {
    	List<Person> sortedPeople = people.stream()
    			.sorted((p1, p2) -> p1.getAge()
    					.compareTo(p2.getAge()))
    			.collect(Collectors.toList());
    	return sortedPeople;
    }
}
