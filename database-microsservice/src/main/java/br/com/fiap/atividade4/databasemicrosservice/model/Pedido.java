package br.com.fiap.atividade4.databasemicrosservice.model;

import br.com.fiap.atividade4.databasemicrosservice.dto.PedidoDTO;
import br.com.fiap.atividade4.databasemicrosservice.dto.PizzaDTO;
import br.com.fiap.atividade4.databasemicrosservice.enums.StatusPedido;
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
@Table(name = "tb_pedido")
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(
			name = "tb_pedido_pizzas",
			joinColumns = @JoinColumn(name = "pedido_id"),
			inverseJoinColumns = @JoinColumn(name = "pizza_id"))
	private List<Pizza> pizzas = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<PizzaMontada> pizzaMontadas = new ArrayList<>();

	@ManyToOne()
	private Cliente cliente;

	private StatusPedido status;

}
