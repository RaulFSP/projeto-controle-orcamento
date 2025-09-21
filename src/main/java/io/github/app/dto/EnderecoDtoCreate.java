package io.github.app.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoDtoCreate(
		@JsonAlias(value = {"bairro"})
		String bairro,
		@JsonAlias(value = {"localidade"})
		String cidade, 
		@JsonAlias(value = {"uf"})
		String uf) {

}
