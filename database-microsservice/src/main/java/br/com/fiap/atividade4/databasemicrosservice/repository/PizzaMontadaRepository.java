package br.com.fiap.atividade4.databasemicrosservice.repository;

import br.com.fiap.atividade4.databasemicrosservice.model.PizzaMontada;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface PizzaRepository.
 */
public interface PizzaMontadaRepository extends JpaRepository<PizzaMontada, Long> {
	
}