package com.rr27.lesson4springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Особенности примера
 * работа идет только с учетками покупателей, админской части нет
 *
 */
@SpringBootApplication
public class Lesson4SpringDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lesson4SpringDataApplication.class, args);

	}
}
