package io.github.app.dto;

import io.github.app.domain.cliente.NaturezaJuridica;

public record ClienteDtoReadFull(
		Long id,
		String nome,
		String documento,
		String email,
		String cep,
		String telefone,
		String uf,
		String cidade,
		String bairro,
		NaturezaJuridica natureza,
		boolean active) {

}
