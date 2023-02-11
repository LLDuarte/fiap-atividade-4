package br.com.fiap.atividade4.databasemicrosservice.repository;

import br.com.fiap.atividade4.databasemicrosservice.dto.PizzaDTO;
import br.com.fiap.atividade4.databasemicrosservice.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The Interface PizzaRepository.
 */
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
	
    /**
     * Find by id DTO.
     *
     * @param id the id
     * @return the pizza DTO
     */
    @Query(value = "SELECT new br.com.fiap.atividade4.databasemicrosservice.dto.PizzaDTO(p) FROM Pizza p WHERE p.id = :id")
    PizzaDTO findByIdDTO(Long id);
    
    /**
     * Find all DTO.
     *
     * @return the list
     */
    @Query(value = "SELECT new br.com.fiap.atividade4.databasemicrosservice.dto.PizzaDTO(p) FROM Pizza p")
    List<PizzaDTO> findAllDTO();
	
}