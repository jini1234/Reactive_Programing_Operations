package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {
	int a;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public static void main(String[] args) {
		method(2);
		DemoApplication demoApplication = new DemoApplication();


		System.out.println("To print to console" + Arrays.toString(args));

		LocalDateTime.now();

		SpringApplication.run(DemoApplication.class, args);
	}

	private static int method(int i) {
		return i+1;
	}


}
