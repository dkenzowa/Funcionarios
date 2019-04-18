package com.funcionarios.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.funcionarios.dto.FuncionarioDTO;
import com.funcionarios.entities.Funcionario;
import com.funcionarios.services.FuncionarioService;

@RestController
@RequestMapping(value="/funcionarios")
public class FuncionarioResource implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FuncionarioService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Funcionario obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FuncionarioDTO>> findAll(){
		List<Funcionario> list = service.findAll();
		List<FuncionarioDTO> listDto = list.stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody FuncionarioDTO objDto, @PathVariable Integer id){
		Funcionario obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody FuncionarioDTO objDto){
		Funcionario obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
