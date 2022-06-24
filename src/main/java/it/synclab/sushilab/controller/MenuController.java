package it.synclab.sushilab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.synclab.sushilab.service.MenuServiceInterface;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MenuController {

	
	@Autowired
	private MenuServiceInterface service;
	
	@GetMapping(path = "/menu/{idMenu}")
	public ResponseEntity<?> getById(@PathVariable("idMenu")long id) {
		return service.getById(id);
	}
	
	@GetMapping(path ="/menu/{idMenu}/fasce")
	public ResponseEntity<?> getFasce(@PathVariable("idMenu")long idM) {
		return service.getFasce(idM);
	}


}
