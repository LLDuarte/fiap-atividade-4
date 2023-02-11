package br.com.fiap.atividade4.databasemicrosservice.controller;

import br.com.fiap.atividade4.databasemicrosservice.dto.ClienteDTO;
import br.com.fiap.atividade4.databasemicrosservice.dto.PizzaDTO;
import br.com.fiap.atividade4.databasemicrosservice.dto.PizzaMontadaDTO;
import br.com.fiap.atividade4.databasemicrosservice.model.Cliente;
import br.com.fiap.atividade4.databasemicrosservice.model.Ingrediente;
import br.com.fiap.atividade4.databasemicrosservice.model.Pizza;
import br.com.fiap.atividade4.databasemicrosservice.model.PizzaMontada;
import br.com.fiap.atividade4.databasemicrosservice.repository.IngredienteRepository;
import br.com.fiap.atividade4.databasemicrosservice.repository.PizzaMontadaRepository;
import br.com.fiap.atividade4.databasemicrosservice.repository.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class PizzaController.
 */
@RestController
@Slf4j
public class PizzaController {
	
	/** The pizza business. */
	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Autowired
	private PizzaMontadaRepository pizzaMontadaRepository;

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@RequestMapping(value = "/database/pizzas", method = RequestMethod.GET)
    public List<PizzaDTO> getAll() {
		return this.pizzaRepository.findAllDTO();
    }
	
	/**
	 * Gets the pizza.
	 *
	 * @param id the id
	 * @return the pizza
	 */
	@RequestMapping(value = "/database/pizza/{id}", method = RequestMethod.GET)
    public ResponseEntity<PizzaDTO> get(@PathVariable(value = "id") long id)
    {
		PizzaDTO pizza = this.pizzaRepository.findByIdDTO(id);

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
	@RequestMapping(value = "/database/pizza/create", method =  RequestMethod.POST)
    public ResponseEntity<PizzaDTO> create(@RequestBody PizzaDTO dto)
    {
		Pizza entity = new Pizza();

		this.mapDtoToEntity(dto, entity);

		entity = this.pizzaRepository.save(entity);

		return new ResponseEntity<PizzaDTO>(new PizzaDTO(entity), HttpStatus.CREATED);
    }

    /**
     * Put.
     *
     * @param newPizza the new pizza
     * @return the response entity
     */
	@RequestMapping(value = "/database/pizza", method =  RequestMethod.POST)
    public ResponseEntity<PizzaDTO> update(@RequestBody PizzaDTO newPizza)
    {
		Pizza entity = pizzaRepository.findById(newPizza.getId())
				.orElse(null);

		if(entity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		entity.getIngredients().clear();

		this.mapDtoToEntity(newPizza, entity);

		entity = pizzaRepository.save(entity);

		return new ResponseEntity<PizzaDTO>(new PizzaDTO(entity), HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the response entity
     */
	@RequestMapping(value = "/database/pizza/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") long id)
    {
		Pizza pizza = pizzaRepository.findById(id).orElse(null);

		if (pizza == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		pizzaRepository.delete(pizza);

		return new ResponseEntity<>(HttpStatus.OK);
    }

	@RequestMapping(value = "/database/pizza/nova-pizza-montada", method =  RequestMethod.POST)
	public ResponseEntity<PizzaMontadaDTO> novaPizzaMontada(@RequestBody PizzaDTO dto)
	{
		PizzaMontada pizzaMontada = new PizzaMontada(dto);

		pizzaMontada = this.pizzaMontadaRepository.save(pizzaMontada);

		return new ResponseEntity<PizzaMontadaDTO>(new PizzaMontadaDTO(pizzaMontada), HttpStatus.CREATED);
	}

	private void mapDtoToEntity(PizzaDTO dto, Pizza entity) {
		entity.setName(dto.getName());

		for(String ingredientName : dto.getIngredients()) {
			Ingrediente ingredient = ingredienteRepository.findByName(ingredientName);
			if (ingredient == null) {
				ingredient = new Ingrediente(ingredientName);
				ingredient.setPizzas(new ArrayList<>());
			}
			entity.getIngredients().add(ingredient);
		}
	}
}