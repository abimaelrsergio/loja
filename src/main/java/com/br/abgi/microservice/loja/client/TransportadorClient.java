package com.br.abgi.microservice.loja.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.br.abgi.microservice.loja.dto.InfoEntregaDTO;
import com.br.abgi.microservice.loja.dto.VoucherDTO;

@FeignClient("transportador")
public interface TransportadorClient {

	@PostMapping("/entrega")
	VoucherDTO reservaEntrega(InfoEntregaDTO pedidoDTO);
}
