package io.github.app.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.github.app.domain.cliente.Cliente;
import io.github.app.dto.ClienteDtoCreate;
import io.github.app.mapper.ClienteMapper;
import io.github.app.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;
	
	
	public ClienteService(
			ClienteRepository clienteRepository,
			ClienteMapper clienteMapper) {
		this.clienteRepository = clienteRepository;
		this.clienteMapper = clienteMapper;
	}

	@Cacheable(value = "clientes")
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	@CacheEvict(value = "clientes",allEntries = true)
	public Cliente createCliente(ClienteDtoCreate dtoCreate) {
		Cliente cliente = clienteMapper.fromDtoCreate(dtoCreate);
		return clienteRepository.save(cliente);
	}
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Cliente não encontrado pelo id"));
	}
}
