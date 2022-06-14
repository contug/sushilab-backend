package it.synclab.sushilab.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.synclab.sushilab.service.TavoloServiceInterface;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TavoloController {

	@Autowired
	private TavoloServiceInterface service;
	
	
	@PostMapping(path = "/tavolo")
	public ResponseEntity<?> creaSessione(@RequestBody Optional<Long> idT) {
		long id = 0;
		if(idT.isPresent())
			id = idT.get();
		return service.creaSessione(id);
	}
	
	
	@GetMapping(path = "/tavolo/{idTavolo}")
	public ResponseEntity<?>  ottieniSessione(@PathVariable("idTavolo") long idT) {
		return service.ottieniSessione(idT);
	}
	
	
	@DeleteMapping(path = "tavolo/{idTavolo}")
	public ResponseEntity<?> chiudiSessione(@PathVariable("idTavolo") long idT) {
		return service.chiudiSessione(idT);
	}
}
