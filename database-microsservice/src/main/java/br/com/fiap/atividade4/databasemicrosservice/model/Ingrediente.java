package br.com.fiap.atividade4.databasemicrosservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_ingredient")
public class Ingrediente {
	
	/** The id. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	/** The name. */
	@Column(name = "name", nullable = false, length = 100, unique = true)
	private String name;
	
	/** The pizzas. */
	@ManyToMany(mappedBy = "ingredients")
	private List<Pizza> pizzas;
	
	/**
	 * Instantiates a new ingrediente.
	 *
	 * @param name the name
	 */
	public Ingrediente(String name) {
		this.name = name;
	}
	
}
