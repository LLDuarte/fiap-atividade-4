package br.com.fiap.atividade4.databasemicrosservice.model;

import br.com.fiap.atividade4.databasemicrosservice.dto.PizzaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pizza_montada")
public class PizzaMontada {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name = "ingredients", nullable = false, length = 1000)
	private String ingredientes;

    public PizzaMontada(PizzaDTO pizza) {
        StringBuilder ingredientes = new StringBuilder();

        for(String ingrediente : pizza.getIngredients()) {
            ingredientes.append(ingrediente + " ");
        }

        this.ingredientes = ingredientes.toString();
    }
}
