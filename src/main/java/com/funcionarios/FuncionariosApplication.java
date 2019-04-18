package com.funcionarios;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.funcionarios.entities.Funcionario;
import com.funcionarios.repositories.FuncionarioRepository;

@SpringBootApplication
public class FuncionariosApplication implements CommandLineRunner {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FuncionariosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
