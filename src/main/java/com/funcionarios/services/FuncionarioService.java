package com.funcionarios.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcionarios.entities.Funcionario;
import com.funcionarios.repositories.FuncionarioRepository;

@Service
public class FuncionarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Autowired
	private FuncionarioRepository repo;
	
	public Funcionario find(Integer id) {
		Optional<Funcionario> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public List<Funcionario> findAll() {
		return repo.findAll();
	}
}
