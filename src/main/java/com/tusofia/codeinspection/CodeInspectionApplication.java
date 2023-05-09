package com.tusofia.codeinspection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class CodeInspectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeInspectionApplication.class, args);
	}

}
