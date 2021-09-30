package com.vinicius.clients.crud.crudclients.services.eceptions;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException( String message ) {
		super(message);
	}
}
