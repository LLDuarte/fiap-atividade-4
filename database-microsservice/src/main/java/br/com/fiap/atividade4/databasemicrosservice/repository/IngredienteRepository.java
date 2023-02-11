package br.com.fiap.atividade4.databasemicrosservice.repository;

import br.com.fiap.atividade4.databasemicrosservice.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

	Ingrediente findByName(String ingredientName);
	
}