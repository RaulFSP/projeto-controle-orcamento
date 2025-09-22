package io.github.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.github.app.domain.cliente.Cliente;
import io.github.app.dto.ClienteDtoCreate;
import io.github.app.dto.ClienteDtoRead;
import io.github.app.dto.ClienteDtoReadFull;
import io.github.app.mapper.ClienteMapper;
import io.github.app.service.ClienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController {

	private final ClienteService clienteService;
	private final ClienteMapper clienteMapper;

	private ClienteController(ClienteService clienteService, ClienteMapper clienteMapper) {
		this.clienteService = clienteService;
		this.clienteMapper = clienteMapper;
	}

	@GetMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("clientes_page");
		List<ClienteDtoRead> clientes =clienteService.findAll().parallelStream().map(m->clienteMapper.toDtoRead(m)).collect(Collectors.toList());
		mv.addObject("clientes", clientes);
		return mv;
	}

	@GetMapping(value = "/new")
	public ModelAndView showClienteForm(ClienteDtoCreate clienteDtoCreate) {
		ModelAndView mv = new ModelAndView("clientes_form");
		mv.addObject("clienteDto", clienteDtoCreate);
		return mv;
	}

	@GetMapping(value = "/{id}")
	public ModelAndView showCliente(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("clientes_show");
		ClienteDtoReadFull clienteDto = clienteMapper.toDtoReadFull(clienteService.findById(id));
		mv.addObject("clienteDto", clienteDto);
		return mv;
	}

	@GetMapping(value = "/{id}/edit")
	public ModelAndView editCliente(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("clientes_form");
		ClienteDtoReadFull clienteDto = clienteMapper.toDtoReadFull(clienteService.findById(id));
		mv.addObject("clienteDto", clienteDto);
		return mv;
	}

	@PostMapping
	public ModelAndView createCliente(@Valid @ModelAttribute("clienteDto") ClienteDtoCreate clienteDtoCreate,
			BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if (result.hasErrors()) {
			mv.setViewName("clientes_form");
			mv.addObject("clienteDto", clienteDtoCreate);
			return mv;
		}
		mv.setViewName("redirect:/clientes");
		clienteService.createCliente(clienteDtoCreate);
		return mv;
	}

	@DeleteMapping(value = "/{id}")
	public String disableCliente(@PathVariable Long id) {
		clienteService.deleteClienteById(id);
		String retorno = "redirect:/clientes/%d".formatted(id);
		return retorno;
	}
}
