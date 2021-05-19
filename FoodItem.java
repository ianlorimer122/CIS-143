/**
 * 
 */
package com.olympic.cis143.m05.student.homework.annotations2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author iblor
 *
 */

@SuppressWarnings("unused")
@Retention(RetentionPolicy.RUNTIME)
public @interface FoodItem {
	String getText() default "This is a taco.";
}
