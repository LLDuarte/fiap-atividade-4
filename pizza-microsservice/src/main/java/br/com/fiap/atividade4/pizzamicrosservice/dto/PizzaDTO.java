package br.com.fiap.atividade4.pizzamicrosservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDTO {
	private Long id;
	
	private String name;
	
	private Set<String> ingredients;

}
