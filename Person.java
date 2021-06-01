package com.olympic.cis143.m06.students.homework;

public class Person {

    private Integer age;

    private String name;

    public Person(final Integer age, final String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "{ Age: " + this.getAge() + ", Name: " + this.getName() + " }";
    }
}
