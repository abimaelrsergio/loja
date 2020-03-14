package com.br.abgi.microservice.loja.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.br.abgi.microservice.loja.dto.CompraDTO;
import com.br.abgi.microservice.loja.dto.InfoFornecedorDTO;

@Service
public class CompraService {

	public ResponseEntity<InfoFornecedorDTO> realizaCompra(CompraDTO compra) {

		RestTemplate client = new RestTemplate();
		String estado = compra.getEndereco().getEstado();
		ResponseEntity<InfoFornecedorDTO> responseEntity = client.exchange("http://localhost:8081/info/" + estado, HttpMethod.GET, null, InfoFornecedorDTO.class);
		return responseEntity;
	}

}
