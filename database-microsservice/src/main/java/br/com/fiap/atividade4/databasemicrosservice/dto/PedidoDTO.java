package br.com.fiap.atividade4.databasemicrosservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

	private Set<Long> pizzasId;

	private Set<Long> novasPizzas;

	private Long clienteId;

}
