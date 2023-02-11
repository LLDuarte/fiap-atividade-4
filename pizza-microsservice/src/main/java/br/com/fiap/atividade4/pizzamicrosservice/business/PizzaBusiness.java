package br.com.fiap.atividade4.pizzamicrosservice.business;


import br.com.fiap.atividade4.pizzamicrosservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The Class PizzaBusiness.
 */
@Service
public class PizzaBusiness {

	@Value("${database.baseUrl}")
	private String databaseUrl;

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public List<PizzaDTO> getAll() {
		ResponseEntity<PizzaDTO[]> pizzas
				= restTemplate.getForEntity(databaseUrl + "/pizzas", PizzaDTO[].class);

		if(pizzas.getBody() != null) {
			return Arrays.asList(pizzas.getBody());
		}

		return new ArrayList<>();
	}

	/**
	 * Gets the pizza by Id.
	 *
	 * @param id the id
	 * @return the optional
	 */
	public PizzaDTO get(Long id) {
		ResponseEntity<PizzaDTO> pizza
				= restTemplate.getForEntity(databaseUrl + "/pizza/{id}", PizzaDTO.class, id);

		return pizza.getBody();	}

	/**
	 * Creates the.
	 *
	 * @param dto the dto
	 * @return the pizza
	 */
	public PizzaDTO create(PizzaDTO dto) {
		ResponseEntity<PizzaDTO> pizza
				= restTemplate.postForEntity(databaseUrl + "/pizza/create", dto, PizzaDTO.class);

		return pizza.getBody();
	}

	/**
	 * Update.
	 *
	 * @param dto the dto
	 * @return the optional
	 */
	public PizzaDTO update(PizzaDTO dto) {
		ResponseEntity<PizzaDTO> pizza
				= restTemplate.postForEntity(databaseUrl + "/pizza/update", dto, PizzaDTO.class);

		return pizza.getBody();
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	public ResponseEntity<String> delete(long id) {
		restTemplate.delete(databaseUrl + "/pizza/{id}", id);

		return ResponseEntity.ok("Pizza removida com sucesso!");
	}

    public ResponseEntity<Void> novoPedido(PedidoDTO pedido) {
		Objects.requireNonNull(pedido);
		Objects.requireNonNull(pedido.getClienteId());

		ResponseEntity<ClienteDTO> cliente
				= restTemplate.getForEntity(databaseUrl + "/cliente/{id}", ClienteDTO.class, pedido.getClienteId());

		if(cliente.getBody() == null) {
			throw new IllegalArgumentException("Cliente não encontrado");
		}

		if(pedido.getPizzasId() == null && pedido.getNovasPizzas() == null) {
			throw new IllegalArgumentException("Nenhuma pizza selecionada");
		}

		for (PizzaDTO p : pedido.getNovasPizzas()) {
			ResponseEntity<PizzaMontadaDTO> pizzaMontadaDTO
					= restTemplate.postForEntity(databaseUrl + "/pizza/nova-pizza-montada", p, PizzaMontadaDTO.class);
		}

		ResponseEntity<PedidoDTO> response
				= restTemplate.postForEntity(databaseUrl + "/novo-pedido", pedido, PedidoDTO.class);

		return new ResponseEntity<>(HttpStatus.OK);
    }
}