package io.github.app.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.app.dto.EnderecoDtoCreate;

@Service
public class ViacepApiService {

	public EnderecoDtoCreate getEndereco(String cep) {

		try {
			cep = cep.contains("-") ? cep.replace("-", "") : cep;
			HttpClient cliente = HttpClient.newHttpClient();
			HttpRequest req = HttpRequest.newBuilder()
					.uri(URI.create(
							"https://viacep.com.br/ws/%s/json/".formatted(cep)))
					.GET().build();
			HttpResponse<String> resp;
			resp = cliente.send(req, BodyHandlers.ofString());
			ObjectMapper mapper = new ObjectMapper();
			EnderecoDtoCreate endereco = mapper.readValue(resp.body(),
					EnderecoDtoCreate.class);
			return endereco;
		} catch (IOException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
		throw new RuntimeException("Erro de execução no método da api de serviços");
	}
}
