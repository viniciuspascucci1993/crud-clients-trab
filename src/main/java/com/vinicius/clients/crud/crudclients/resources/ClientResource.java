package com.vinicius.clients.crud.crudclients.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinicius.clients.crud.crudclients.dto.ClientDTO;
import com.vinicius.clients.crud.crudclients.services.ClientService;


@RestController
@RequestMapping( value ="/clients")
public class ClientResource {

	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
			) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		Page<ClientDTO> listCategories = clientService.findAllPaged( pageRequest );
		
		return ResponseEntity.ok().body(listCategories);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById( @PathVariable("id") Long id ) {
		
		ClientDTO dto = clientService.findById( id );
		return ResponseEntity.ok().body(dto) ;
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> createCategory( @RequestBody ClientDTO clientDTO ) {
		
		clientDTO = clientService.insert( clientDTO );
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(clientDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> updateCategory( @PathVariable("id") Long id, @RequestBody ClientDTO clientDto ) {
		
		clientDto = clientService.update(id, clientDto );
		return ResponseEntity.ok().body(clientDto) ;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCategory( @PathVariable("id") Long id ) {
		
		clientService.delete(id );
		return ResponseEntity.noContent().build();
	}
}
