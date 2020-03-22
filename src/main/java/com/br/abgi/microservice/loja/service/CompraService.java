package com.br.abgi.microservice.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.abgi.microservice.loja.client.FornecedorClient;
import com.br.abgi.microservice.loja.dto.CompraDTO;
import com.br.abgi.microservice.loja.dto.InfoFornecedorDTO;
import com.br.abgi.microservice.loja.dto.InfoPedidoDTO;
import com.br.abgi.microservice.loja.model.Compra;

@Service
public class CompraService {

	@Autowired
	private FornecedorClient fornecedorClient;

	public Compra realizaCompra(CompraDTO compra) {

		InfoFornecedorDTO fornecedorDTO = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		
		InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
		
		System.out.println(fornecedorDTO.getEndereco());
		
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
		
		
		return compraSalva;
	}

}
