package br.com.fiap.atividade4.databasemicrosservice.repository;


import br.com.fiap.atividade4.databasemicrosservice.dto.ClienteDTO;
import br.com.fiap.atividade4.databasemicrosservice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface Cliente repository.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	/**
	 * Find by id DTO.
	 *
	 * @param id the id
	 * @return the cliente DTO
	 */
	@Query(value = "SELECT new br.com.fiap.atividade4.databasemicrosservice.dto.ClienteDTO(p) FROM Cliente p WHERE p.id = :id")
	ClienteDTO findByIdDTO(Long id);

	/**
	 * Find all DTO.
	 *
	 * @return the list
	 */
	@Query(value = "SELECT new br.com.fiap.atividade4.databasemicrosservice.dto.ClienteDTO(p) FROM Cliente p")
	List<ClienteDTO> findAllDTO();
}