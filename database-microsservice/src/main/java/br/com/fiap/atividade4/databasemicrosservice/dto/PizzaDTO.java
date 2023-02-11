package br.com.fiap.atividade4.databasemicrosservice.dto;

import br.com.fiap.atividade4.databasemicrosservice.model.Ingrediente;
import br.com.fiap.atividade4.databasemicrosservice.model.Pizza;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDTO {
	private Long id;
	
	private String name;
	
	private Set<String> ingredients;
	
	public PizzaDTO(Pizza pizza) {
		this.id = pizza.getId();
		this.name = pizza.getName();
		
		Set<String> ingredientes = new HashSet<>();
		
		for(Ingrediente p : pizza.getIngredients()) {
			ingredientes.add(p.getName());
		}
		this.ingredients = ingredientes;
	}

}
