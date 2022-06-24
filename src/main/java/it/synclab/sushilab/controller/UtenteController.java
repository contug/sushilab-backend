package it.synclab.sushilab.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.synclab.sushilab.dto.IngredienteDto;
import it.synclab.sushilab.dto.UtenteDto;
import it.synclab.sushilab.service.UtenteService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	@PostMapping("/tavolo/{idTavolo}/{idUtente}")
	public ResponseEntity<?> partecipaSessione(@PathVariable("idTavolo") Long tavoloId, @PathVariable("idUtente") Long utenteId){
		return utenteService.partecipaSessione(tavoloId, utenteId);
	}
	
	@GetMapping("/blacklist/{idUtente}")
	public ResponseEntity<?> getBlacklist(@PathVariable Long idUtente){
		return utenteService.getBlacklist(idUtente);
	}
	
	@PostMapping("/blacklist/{idUtente}")
	public ResponseEntity<?> aggiornaBlacklist(@PathVariable Long idUtente, @RequestBody IngredienteDto ingrediente){
		return utenteService.aggiornaBlacklist(idUtente, ingrediente);
	}
	
	@DeleteMapping("/blacklist/{idUtente}")
	public ResponseEntity<?> eliminaBlacklist(@PathVariable Long idUtente, @RequestBody IngredienteDto ingredienti){
		return utenteService.eliminaBlacklist(idUtente, ingredienti);
	}
	
	@GetMapping("/guida")
	public ResponseEntity<?> getAllergeni(){
		return utenteService.getAllergeni();
	}
}
