package io.github.app.mapper;

import org.springframework.stereotype.Component;

import io.github.app.domain.cliente.Cliente;
import io.github.app.domain.cliente.NaturezaJuridica;
import io.github.app.dto.ClienteDtoCreate;
import io.github.app.dto.ClienteDtoRead;
import io.github.app.dto.ClienteDtoReadFull;
import io.github.app.dto.EnderecoDtoCreate;
import io.github.app.service.ViacepApiService;

@Component
public class ClienteMapper {

	private final ViacepApiService viacepApiService;

	private ClienteMapper(ViacepApiService viacepApiService) {
		this.viacepApiService = viacepApiService;
	}

	public ClienteDtoRead toDtoRead(Cliente cliente) {
		return new ClienteDtoRead(cliente.getId(), cliente.getNome(),
				cliente.getDocumento(), cliente.getEmail());
	}

	public ClienteDtoReadFull toDtoReadFull(Cliente cliente) {
		return new ClienteDtoReadFull(cliente.getId(), cliente.getNome(), cliente.getDocumento(), cliente.getEmail(), cliente.getCep(), cliente.getTelefone(), cliente.getUf(), cliente.getCidade(), cliente.getBairro(), cliente.getNaturezaJuridica(),cliente.isActive());
	}
	
	public Cliente fromDtoCreate(ClienteDtoCreate dtoCreate) {
		EnderecoDtoCreate dtoEndereco = viacepApiService
				.getEndereco(dtoCreate.cep());

		return Cliente.builder()
				.bairro(dtoEndereco.bairro())
				.cep(dtoCreate.cep())
				.cidade(dtoEndereco.cidade())
				.documento(dtoCreate.documento())
				.email(dtoCreate.email())
				.id(null)
				.naturezaJuridica(setNaturezaJuridica(dtoCreate.documento()))
				.nome(dtoCreate.nome())
				.telefone(dtoCreate.telefone())
				.uf(dtoEndereco.uf())
				.version(null)
				.active(true)
				.build();
	}
	
	private NaturezaJuridica setNaturezaJuridica(String documento) {
		int tamanho = documento.length();
		if (tamanho == 18) {
			return NaturezaJuridica.PESSOA_JURIDICA;
		} 
		if (tamanho == 14) {
			return NaturezaJuridica.PESSOA_FISICA;
		}
		throw new RuntimeException("Erro de seleção de natureza jurídica");
	}
}
