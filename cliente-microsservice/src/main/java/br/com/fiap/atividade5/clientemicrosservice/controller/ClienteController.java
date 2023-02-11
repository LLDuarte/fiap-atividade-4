package br.com.fiap.atividade5.clientemicrosservice.controller;

import br.com.fiap.atividade5.clientemicrosservice.business.ClienteBusiness;
import br.com.fiap.atividade5.clientemicrosservice.dto.ClienteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ClienteController {
	
	@Autowired
	private ClienteBusiness clienteBusiness;
	
	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public List<ClienteDTO> getAll() {
        return this.clienteBusiness.getAll();
    }
	
	/**
	 * Gets the cliente.
	 *
	 * @param id the id
	 * @return the cliente
	 */
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClienteDTO> get(@PathVariable(value = "id") long id)
    {
		ClienteDTO cliente = this.clienteBusiness.get(id);

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
	@RequestMapping(value = "/cliente/create", method =  RequestMethod.POST)
	public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto)
    {
		ClienteDTO cliente = this.clienteBusiness.create(dto);

        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    /**
     * Put.
     *
     * @param newcliente the new cliente
     * @return the response entity
     */
    @RequestMapping(value = "/cliente/update", method =  RequestMethod.POST)
    public ResponseEntity<ClienteDTO> update(@RequestBody ClienteDTO newcliente)
    {
		ClienteDTO cliente = this.clienteBusiness.update(newcliente);

		return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable(value = "id") long id)
    {
		return this.clienteBusiness.delete(id);
	}
}