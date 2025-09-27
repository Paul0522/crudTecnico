package com.example.crudTecnico;

import org.springframework.boot.SpringApplication;

public class TestCrudTecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.from(CrudTecnicoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
