package com.br.abgi.microservice.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.abgi.microservice.loja.dto.CompraDTO;
import com.br.abgi.microservice.loja.dto.InfoFornecedorDTO;
import com.br.abgi.microservice.loja.service.CompraService;

@RestController
@RequestMapping("/compra")
public class CompraController {
	
	@Autowired
	private CompraService compraService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String realizaCompra(@RequestBody CompraDTO compra) {
		
		ResponseEntity<InfoFornecedorDTO> response = compraService.realizaCompra(compra);
		System.out.println(response.getBody().getEndereco());
		return response.getBody().getEndereco();
	}

}
