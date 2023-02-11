package br.com.fiap.atividade4.databasemicrosservice.controller;

import br.com.fiap.atividade4.databasemicrosservice.dto.ClienteDTO;
import br.com.fiap.atividade4.databasemicrosservice.model.Cliente;
import br.com.fiap.atividade4.databasemicrosservice.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@RequestMapping(value = "/database/clientes", method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> getAll() {
		List<ClienteDTO> clientes = this.clienteRepository.findAllDTO();

		return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
	
	/**
	 * Gets the cliente.
	 *
	 * @param id the id
	 * @return the cliente
	 */
	@RequestMapping(value = "/database/cliente/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClienteDTO> get(@PathVariable(value = "id") long id)
    {
        ClienteDTO cliente = this.clienteRepository.findByIdDTO(id);
        
        if(cliente != null)
            return new ResponseEntity<>(cliente, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	/**
	 * Post.
	 *
	 * @param dto the cliente
	 * @return the cliente
	 */
	@RequestMapping(value = "/database/cliente/create", method =  RequestMethod.POST)
	public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto)
    {
		Cliente entity = new Cliente(dto);

		entity = this.clienteRepository.save(entity);

        return new ResponseEntity<ClienteDTO>(new ClienteDTO(entity), HttpStatus.CREATED);
    }

    /**
     * Put.
     *
     * @param newcliente the new cliente
     * @return the response entity
     */
    @RequestMapping(value = "/database/cliente/update", method =  RequestMethod.POST)
    public ResponseEntity<ClienteDTO> update(@RequestBody ClienteDTO newcliente)
    {
		Cliente oldCliente = clienteRepository.findById(newcliente.getId()).orElse(null);

		if (oldCliente == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		oldCliente = new Cliente(newcliente);

		oldCliente = clienteRepository.save(oldCliente);

		return new ResponseEntity<ClienteDTO>(new ClienteDTO(oldCliente), HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/database/cliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable(value = "id") long id)
    {
		Cliente cliente = clienteRepository.findById(id).orElse(null);

		if (cliente == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		clienteRepository.delete(cliente);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}