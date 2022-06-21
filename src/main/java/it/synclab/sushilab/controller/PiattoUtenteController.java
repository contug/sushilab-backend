package it.synclab.sushilab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.synclab.sushilab.service.PiattoUtenteServiceInterface;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PiattoUtenteController {

	
	@Autowired
	private PiattoUtenteServiceInterface service;
	
	
	@GetMapping(path = "/menu/{idMenu}/preferiti")
	public ResponseEntity<?> getPreferiti(@PathVariable("idMenu") long idM) {
		return service.getPreferiti(idM);
	}
	
	
	@GetMapping(path = "/tavolo/{idUtente}/preferiti")
	public ResponseEntity<?> getPreferitiUtente(@PathVariable("idUtente")long idU) {
		return service.getPreferitiUtente(idU);
	}
	
	@PostMapping(path = "/valutazione/{idUtente}/{idPiatto}")
	public ResponseEntity<?> valutazionePiatto(@PathVariable("idUtente") long idU, @PathVariable("idPiatto") long idP, @RequestBody float voto) {
		return service.valutazionePiatto(idU, idP, voto);
	}
	
	
	@GetMapping(path = "/valutazione/{idUtente}/{idPiatto}")
	public ResponseEntity<?> getValutazionePiatto(@PathVariable("idUtente") long idU, @PathVariable("idPiatto") long idP) {
		return service.getValutazionePiatto(idU, idP);
	}
	
	@PostMapping(path = "/tavolo/preferiti/{idUtente}")
	public ResponseEntity<?> aggiungiPreferito(@PathVariable("idUtente") long idU, @RequestBody long idP) {
		return service.aggiungiPreferito(idU, idP);		
	}
	
	@DeleteMapping(path = "/tavolo/preferiti/{idUtente}")
	public ResponseEntity<?> rimuoviPreferito(@PathVariable("idUtente") long idU, @RequestBody long idP) {
		return service.rimuoviPreferito(idU, idP);		
	}

	/*@GetMapping("/piatto/{idUtente}/{idPiatto}")
	public ResponseEntity<?> getPiattoUtente() {

	}*/
	
}
