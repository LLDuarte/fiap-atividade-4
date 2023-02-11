package br.com.fiap.atividade4.databasemicrosservice.dto;

import br.com.fiap.atividade4.databasemicrosservice.model.Cliente;
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

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.name = cliente.getName();
		this.celular = cliente.getCelular();
		this.email = cliente.getEmail();
		this.rua = cliente.getRua();
		this.numero = cliente.getNumero();
		this.bairro = cliente.getBairro();
		this.cidade = cliente.getCidade();
	}

}
