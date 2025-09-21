package io.github.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.app.domain.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Optional<Cliente> findByDocumento(String documento);
}
