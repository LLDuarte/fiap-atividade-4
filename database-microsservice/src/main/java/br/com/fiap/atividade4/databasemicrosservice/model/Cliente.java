package br.com.fiap.atividade4.databasemicrosservice.model;

import br.com.fiap.atividade4.databasemicrosservice.dto.ClienteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_cliente")
public class Cliente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "celular", nullable = false, length = 9)
	private String celular;

	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@Column(name = "rua", nullable = false, length = 100)
	private String rua;

	@Column(name = "numero", nullable = false)
	private Integer numero;

	@Column(name = "bairro", nullable = false, length = 40)
	private String bairro;

	@Column(name = "cidade", nullable = false, length = 35)
	private String cidade;

	@OneToMany(cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente(ClienteDTO dto) {
		this.setName(dto.getName());
		this.setCelular(dto.getCelular());
		this.setEmail(dto.getEmail());
		this.setRua(dto.getRua());
		this.setNumero(dto.getNumero());
		this.setBairro(dto.getBairro());
		this.setCidade(dto.getCidade());
	}
}
