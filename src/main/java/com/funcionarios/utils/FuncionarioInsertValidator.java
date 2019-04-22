package com.funcionarios.utils;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.funcionarios.dto.FuncionarioDTO;
import com.funcionarios.entities.Funcionario;
import com.funcionarios.entities.enums.TipoFuncionario;
import com.funcionarios.repositories.FuncionarioRepository;
import com.funcionarios.resources.exception.FieldMessage;
import com.funcionarios.services.utils.FuncionarioInsert;

public class FuncionarioInsertValidator implements ConstraintValidator<FuncionarioInsert, FuncionarioDTO>{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Override
	public void initialize(FuncionarioInsert ann) {
	}
	
	@Override
	public boolean isValid(FuncionarioDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoFuncionario.PESSOAFISICA.getCod()) && !ValidatorCpfCnpj.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(TipoFuncionario.PESSOAJURIDICA.getCod()) && !ValidatorCpfCnpj.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Funcionario aux = funcionarioRepository.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
