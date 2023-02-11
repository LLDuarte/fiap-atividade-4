package br.com.fiap.atividade5.clientemicrosservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Cliente dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

	private Long id;

	private String name;

	private String celular;

	private String email;

	private String rua;

	private Integer numero;

	private String bairro;

	private String cidade;
}
