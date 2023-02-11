package br.com.fiap.atividade4.databasemicrosservice.controller;

import br.com.fiap.atividade4.databasemicrosservice.dto.PedidoDTO;
import br.com.fiap.atividade4.databasemicrosservice.enums.StatusPedido;
import br.com.fiap.atividade4.databasemicrosservice.model.Pedido;
import br.com.fiap.atividade4.databasemicrosservice.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The Class PizzaController.
 */
@RestController
@Slf4j
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	private PizzaMontadaRepository pizzaMontadaRepository;

	/**
	 * Post.
	 *
	 * @param pedido the pizza
	 * @return the pizza
	 */
	@RequestMapping(value = "/database/novo-pedido", method =  RequestMethod.POST)
    public ResponseEntity<Void> novoPedido(@RequestBody PedidoDTO pedido)
    {
		Pedido entity = new Pedido();

		entity.setCliente(this.clienteRepository.getById(pedido.getClienteId()));

		for (Long id : pedido.getPizzasId()) {
			this.pizzaRepository.findById(id)
					.ifPresent(pizza -> entity.getPizzas().add(pizza));
		}

		for (Long id : pedido.getNovasPizzas()) {
			this.pizzaMontadaRepository.findById(id)
					.ifPresent(pizzaMontada -> entity.getPizzaMontadas().add(pizzaMontada));
		}

		entity.setStatus(StatusPedido.EM_PREPARO);

		this.pedidoRepository.save(entity);

		return new ResponseEntity<>(HttpStatus.OK);
    }
}