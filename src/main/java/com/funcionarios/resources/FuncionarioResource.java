package com.funcionarios.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/funcionarios")
public class FuncionarioResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public String find() {
		return "REST está funcionado!";
	}
}
