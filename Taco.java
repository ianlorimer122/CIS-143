package com.olympic.cis143.m05.student.homework.annotations2;

@FoodItem
public class Taco {
	@FoodItem
	private String myField;
	public Taco(@FoodItem String message) {
		System.out.println(message);
	}
	public static void main(String[] args) {
		Taco t = new Taco("This is a taco.");
	}
}