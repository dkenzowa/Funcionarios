package com.funcionarios.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcionarios.dto.FuncionarioDTO;
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
	
	public Funcionario update(Funcionario obj) {
		Funcionario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public Funcionario insert(Funcionario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Funcionario fromDTO(FuncionarioDTO objDto) {
		return new Funcionario(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getCpf());
	}

	private void updateData(Funcionario newObj, Funcionario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
