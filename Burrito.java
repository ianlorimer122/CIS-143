package com.olympic.cis143.m05.student.homework.annotations2;

@FoodItem
public class Burrito {
	@FoodItem
	private String myField;
	public Burrito(@FoodItem String message) {
		System.out.println(message);
	}
	public static void main(String[] args) {
		Burrito b = new Burrito("This is a burrito.");
	}
}