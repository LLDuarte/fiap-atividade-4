package br.com.fiap.atividade4.pizzamicrosservice.controller;

import br.com.fiap.atividade4.pizzamicrosservice.business.PizzaBusiness;
import br.com.fiap.atividade4.pizzamicrosservice.dto.PedidoDTO;
import br.com.fiap.atividade4.pizzamicrosservice.dto.PizzaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PizzaController {
	
	@Autowired
	private PizzaBusiness pizzaBusiness;
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@RequestMapping(value = "/pizzas", method = RequestMethod.GET)
    public List<PizzaDTO> getAll() {
        return this.pizzaBusiness.getAll();
    }
	
	/**
	 * Gets the pizza.
	 *
	 * @param id the id
	 * @return the pizza
	 */
	@RequestMapping(value = "/pizza/{id}", method = RequestMethod.GET)
    public ResponseEntity<PizzaDTO> get(@PathVariable(value = "id") long id)
    {
		PizzaDTO pizza = this.pizzaBusiness.get(id);

		if(pizza != null)
            return new ResponseEntity<>(pizza, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	/**
	 * Post.
	 *
	 * @param dto the pizza
	 * @return the pizza
	 */
	@RequestMapping(value = "/pizza/create", method =  RequestMethod.POST)
	public ResponseEntity<PizzaDTO> create(@RequestBody PizzaDTO dto)
    {
		PizzaDTO pizza = this.pizzaBusiness.create(dto);

        return new ResponseEntity<>(pizza, HttpStatus.CREATED);
    }

    /**
     * Put.
     *
     * @param newpizza the new pizza
     * @return the response entity
     */
    @RequestMapping(value = "/pizza/update", method =  RequestMethod.POST)
    public ResponseEntity<PizzaDTO> update(@RequestBody PizzaDTO newpizza)
    {
		PizzaDTO pizza = this.pizzaBusiness.update(newpizza);

		return new ResponseEntity<>(pizza, HttpStatus.CREATED);
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/pizza/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable(value = "id") long id)
    {
		return this.pizzaBusiness.delete(id);
	}

	@RequestMapping(value = "/pizza/novo-pedido", method =  RequestMethod.POST)
	public ResponseEntity<Void> novoPedido(@RequestBody PedidoDTO pedido)
	{
		this.pizzaBusiness.novoPedido(pedido);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}