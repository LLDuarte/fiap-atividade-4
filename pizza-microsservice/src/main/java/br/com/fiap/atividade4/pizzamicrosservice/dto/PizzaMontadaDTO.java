package br.com.fiap.atividade4.pizzamicrosservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaMontadaDTO {
	private Long id;
	
	private String ingredientes;
	

}
