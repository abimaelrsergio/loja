package com.br.abgi.microservice.loja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.abgi.microservice.loja.client.FornecedorClient;
import com.br.abgi.microservice.loja.dto.CompraDTO;
import com.br.abgi.microservice.loja.dto.InfoFornecedorDTO;
import com.br.abgi.microservice.loja.dto.InfoPedidoDTO;
import com.br.abgi.microservice.loja.model.Compra;

@Service
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private FornecedorClient fornecedorClient;

	public Compra realizaCompra(CompraDTO compra) {

		String estado = compra.getEndereco().getEstado();
		
		LOG.info("Buscando informacoes do fornecedor de {}", estado);
		
		InfoFornecedorDTO fornecedorDTO = fornecedorClient.getInfoPorEstado(estado);
		
		LOG.info("Realizando um pedido");
		
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
		
		LOG.info("O endereco foi: {}", fornecedorDTO.getEndereco());
		System.out.println(fornecedorDTO.getEndereco());
		
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
		
		
		return compraSalva;
	}

}
