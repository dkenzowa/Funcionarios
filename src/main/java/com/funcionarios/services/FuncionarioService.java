package com.funcionarios.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.funcionarios.dto.FuncionarioDTO;
import com.funcionarios.dto.FuncionarioUpdateDTO;
import com.funcionarios.entities.Funcionario;
import com.funcionarios.entities.enums.TipoFuncionario;
import com.funcionarios.repositories.FuncionarioRepository;
import com.funcionarios.services.excepiton.ObjectNotFoundException;

@Service
public class FuncionarioService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private FuncionarioRepository repo;

	public Funcionario find(Integer id) {
		Optional<Funcionario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName()));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public Funcionario insert(Funcionario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Funcionario findByEmail(String email) {
		Funcionario obj = repo.findByEmail(email);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado!");
		}
		
		return obj;
	}

	public Funcionario fromDTO(FuncionarioDTO objDto) {
		return new Funcionario(objDto.getId(), objDto.getNome(), objDto.getEmail(),
				TipoFuncionario.toEnum(objDto.getTipo()), objDto.getCpfOuCnpj());
	}
	
	public Funcionario fromDTO(FuncionarioUpdateDTO objDto) {
		return new Funcionario(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	private void updateData(Funcionario newObj, Funcionario obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
