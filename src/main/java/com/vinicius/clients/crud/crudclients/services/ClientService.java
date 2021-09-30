package com.vinicius.clients.crud.crudclients.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vinicius.clients.crud.crudclients.dto.ClientDTO;
import com.vinicius.clients.crud.crudclients.entities.Client;
import com.vinicius.clients.crud.crudclients.repositories.ClientRepository;
import com.vinicius.clients.crud.crudclients.services.eceptions.ResourceNotFoundException;
@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest ) {
		
		Page<Client> listClients = clientRepository.findAll(pageRequest);
		return listClients.map(x -> new ClientDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById( Long id ) {
		
		Optional<Client> obj = clientRepository.findById(id);
		Client entities = obj.orElseThrow(() -> new ResourceNotFoundException("Client Not Found"));
		
		return new ClientDTO(entities);
	}
	
	public ClientDTO insert( ClientDTO clientDto ) {
		Client entity = new Client();
		copyDtoToEntity(clientDto, entity);
		
		entity = clientRepository.save(entity);
		
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO update( Long id, ClientDTO clientDTO ) {
		
		try {
			Client entity = clientRepository.getOne(id);
			entity.setName(clientDTO.getName());
			entity.setCpf(clientDTO.getCpf());
			entity.setIncome(clientDTO.getIncome());
			entity.setBirthDate(clientDTO.getBirthDate());
			entity.setChildren(clientDTO.getChildren());
			
			entity = clientRepository.save(entity);
			
			return new ClientDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Identifier Not Found " + id);
		}
	}
	
	public void delete(Long id) {
		
		try {
			clientRepository.deleteById(id);
		
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Cannot delete the entity because the Identifier is not exist " + id);
		}
	}

	private void copyDtoToEntity(ClientDTO clientDto, Client entity) {
		
		entity.setName(clientDto.getName());
		entity.setCpf(clientDto.getCpf());
		entity.setIncome(clientDto.getIncome());
		entity.setBirthDate(clientDto.getBirthDate());
		entity.setChildren(clientDto.getChildren());
	}
}
