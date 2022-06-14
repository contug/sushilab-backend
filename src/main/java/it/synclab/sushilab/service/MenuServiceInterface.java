package it.synclab.sushilab.service;

import org.springframework.http.ResponseEntity;

public interface MenuServiceInterface {

	public ResponseEntity<?> getById(long id);
	
	public ResponseEntity<?> getFasce(long id);
}
