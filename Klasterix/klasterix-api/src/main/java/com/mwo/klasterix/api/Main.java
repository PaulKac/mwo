package com.mwo.klasterix.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(Main.class, args);
	}

	public static <T> T bean(Class<T> clazz) {
		return context.getBean(clazz);
	}

	public static <T> T bean(String name) {
		return (T) context.getBean(name);
	}
}
