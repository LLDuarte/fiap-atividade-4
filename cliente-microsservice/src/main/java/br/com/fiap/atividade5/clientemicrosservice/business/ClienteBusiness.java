package br.com.fiap.atividade5.clientemicrosservice.business;

import br.com.fiap.atividade5.clientemicrosservice.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * The type Cliente business.
 */
@Service
public class ClienteBusiness {
    @Value("${database.baseUrl}")
    private String databaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<ClienteDTO> getAll() {
        ResponseEntity<ClienteDTO[]> clientes
                = restTemplate.getForEntity(databaseUrl + "/clientes", ClienteDTO[].class);

        if(clientes.getBody() != null) {
            return Arrays.asList(clientes.getBody());
        }

        return new ArrayList<>();
    }

    /**
     * Get cliente dto.
     *
     * @param id the id
     * @return the cliente dto
     */
    public ClienteDTO get(Long id) {
        ResponseEntity<ClienteDTO> cliente
                = restTemplate.getForEntity(databaseUrl + "/clientes/{id}", ClienteDTO.class, id);

        return cliente.getBody();
    }

    /**
     * Create cliente dto.
     *
     * @param dto the dto
     * @return the cliente dto
     */
    public ClienteDTO create(ClienteDTO dto) {
        ResponseEntity<ClienteDTO> cliente
                = restTemplate.postForEntity(databaseUrl + "/clientes/{id}", dto, ClienteDTO.class);

        return cliente.getBody();
    }

    /**
     * Update cliente dto.
     *
     * @param dto the dto
     * @return the cliente dto
     */
    public ClienteDTO update(ClienteDTO dto) {
        ResponseEntity<ClienteDTO> cliente
                = restTemplate.postForEntity(databaseUrl + "/cliente/update", dto, ClienteDTO.class);

        return cliente.getBody();
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the response entity
     */
    public ResponseEntity<String> delete(long id) {
        restTemplate.delete(databaseUrl + "/clientes/{id}", id);

        return ResponseEntity.ok("Cliente removido com sucesso!");
    }
}