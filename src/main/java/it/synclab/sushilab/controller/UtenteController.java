package it.synclab.sushilab.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.synclab.sushilab.dto.IngredienteDto;
import it.synclab.sushilab.dto.UtenteDto;
import it.synclab.sushilab.service.UtenteService;

@RestController
public class UtenteController {

	@Autowired
	private UtenteService utenteService;
	
	
	@PostMapping("/utente")
	public ResponseEntity<?> registrazioneUtente(@RequestBody UtenteDto utenteDto) {
		 return utenteService.registrazioneUtente(utenteDto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UtenteDto utenteDto) {
		return utenteService.login(utenteDto);
	}
	
	@PostMapping("/tavolo/{idTavolo}/{idUtente}")
	public ResponseEntity<?> partecipaSessione(@PathVariable("idTavolo") Long tavoloId, @PathVariable("idUtente") Long utenteId){
		return utenteService.partecipaSessione(tavoloId, utenteId);
	}
	
	@GetMapping("/blacklist/{idUtente}")
	public ResponseEntity<?> getBlacklist(@PathVariable Long idUtente){
		return utenteService.getBlacklist(idUtente);
	}
	
	@PostMapping("/blacklist/{idUtente}")
	public ResponseEntity<?> aggiornaBlacklist(@PathVariable Long idUtente, @RequestBody Set<IngredienteDto> ingredienti){
		return utenteService.aggiornaBlacklist(idUtente, ingredienti);
	}
	
	@DeleteMapping("/blacklist/{idUtente}")
	public ResponseEntity<?> eliminaBlacklist(@PathVariable Long idUtente, @RequestBody Set<IngredienteDto> ingredienti){
		return utenteService.eliminaBlacklist(idUtente, ingredienti);
	}
	
	@GetMapping("/guida")
	public ResponseEntity<?> getAllergeni(){
		return utenteService.getAllergeni();
	}
}
