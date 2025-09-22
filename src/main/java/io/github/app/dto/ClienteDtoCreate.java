package io.github.app.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record ClienteDtoCreate(
		Long id,
		@NotEmpty(message = "o nome não pode ser vazio")
		@Length(min = 2, max = 100, message = "O nome deve ter mais de 2 caracteres") String nome,
		@NotEmpty(message = "o número de documento não pode ser vazio")
		@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}|\\d{2}\\.\\d{3}\\.\\d{3}\\/0001\\-\\d{2}", message = "O documento deve ser um CPF ou CNPJ válido") String documento,
		@Email String email,
		@Pattern(regexp = "\\d{5}\\-\\d{3}", message="O cep não como o exemplo") String cep,
		@Pattern(regexp = "\\d{11}|^$", message = "O telefone não está de acordo com o exemplo")
		String telefone
		) {

}
