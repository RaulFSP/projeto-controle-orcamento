package io.github.app.domain.cliente;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column(nullable = false, length = 11)
	private String telefone;
	@Column(nullable = false, length = 18, unique = true)
	private String documento;
	@Column(length = 150)
	private String bairro;
	@Column(length = 150)
	private String cidade;
	@Column(length = 2)
	private String uf;
	@Column(nullable = false, length = 9)
	private String cep;
	@Column(nullable = false, length = 100)
	private String email;
	@Column(name = "natureza")
	@Enumerated(EnumType.STRING)
	private NaturezaJuridica naturezaJuridica;

	@Version
	private Long version;
	
	public Cliente() {
	}
	private Cliente(Builder builder) {
		this.id = builder.id;
		this.nome = builder.nome;
		this.telefone = builder.telefone;
		this.documento = builder.documento;
		this.bairro = builder.bairro;
		this.cidade = builder.cidade;
		this.uf = builder.uf;
		this.cep = builder.cep;
		this.email = builder.email;
		this.naturezaJuridica = builder.naturezaJuridica;
		this.version = builder.version;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public NaturezaJuridica getNaturezaJuridica() {
		return naturezaJuridica;
	}
	public void setNaturezaJuridica(NaturezaJuridica naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone
				+ ", documento=" + documento + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", uf=" + uf + ", cep=" + cep
				+ ", email=" + email + ", naturezaJuridica=" + naturezaJuridica
				+ "]";
	}
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long id;
		private String nome;
		private String telefone;
		private String documento;
		private String bairro;
		private String cidade;
		private String uf;
		private String cep;
		private String email;
		private NaturezaJuridica naturezaJuridica;
		private Long version;
		
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}
		public Builder telefone(String telefone) {
			this.telefone = telefone;
			return this;
		}
		public Builder documento(String documento) {
			this.documento = documento;
			return this;
		}
		public Builder bairro(String bairro) {
			this.bairro = bairro;
			return this;
		}
		public Builder cidade(String cidade) {
			this.cidade = cidade;
			return this;
		}
		public Builder uf(String uf) {
			this.uf = uf;
			return this;
		}
		public Builder cep(String cep) {
			this.cep = cep;
			return this;
		}
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		public Builder naturezaJuridica(NaturezaJuridica naturezaJuridica) {
			this.naturezaJuridica = naturezaJuridica;
			return this;
		}
		public Builder version(Long version) {
			this.version = version;
			return this;
		}
		public Cliente build() {
			return new Cliente(this);
		}
	}
}
