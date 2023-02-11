package br.com.fiap.atividade4.databasemicrosservice.dto;

import br.com.fiap.atividade4.databasemicrosservice.model.PizzaMontada;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaMontadaDTO {
	private Long id;
	
	private String ingredientes;

	public PizzaMontadaDTO(PizzaMontada p) {
		this.id = p.getId();
		this.ingredientes = p.getIngredientes();
	}

}
