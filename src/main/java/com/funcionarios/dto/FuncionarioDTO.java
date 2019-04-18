package com.funcionarios.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.funcionarios.entities.Funcionario;
import com.funcionarios.services.utils.FuncionarioInsert;

@FuncionarioInsert
public class FuncionarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(message="Email inválido")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cpf;
	
	private Integer tipo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cpfOuCnpj;
	
	public FuncionarioDTO() {
	}
	
	public FuncionarioDTO(Funcionario obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		cpf = obj.getCpf();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

}
