package br.com.fiap.atividade4.databasemicrosservice.repository;

import br.com.fiap.atividade4.databasemicrosservice.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface PizzaRepository.
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
}