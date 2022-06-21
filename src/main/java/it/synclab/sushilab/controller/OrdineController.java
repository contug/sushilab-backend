package it.synclab.sushilab.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.synclab.sushilab.dto.OrdineDto;
import it.synclab.sushilab.dto.UtenteDto;
import it.synclab.sushilab.model.Ordine;
import it.synclab.sushilab.model.Piatto;
import it.synclab.sushilab.service.OrdineService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrdineController {
	
	@Autowired
	private OrdineService ordineService;
	
	@PostMapping("/tavolo/{utenteId}")
	public ResponseEntity<?> effettuaOrdine(@PathVariable Long utenteId, @RequestBody Set<OrdineDto> ordini) {
		 return ordineService.effettuaOrdine(utenteId, ordini);
	}
	
	@GetMapping("/tavolo/{idTavolo}/ordini")
	public ResponseEntity<?> getOrdiniTavolo(@PathVariable Long idTavolo){
		return ordineService.getOrdiniTavolo(idTavolo);
	}
	
	@GetMapping("/tavolo/{idUtente}/personali")
	public ResponseEntity<?> getOrdiniPersonali(@PathVariable Long idUtente){
		return ordineService.getOrdiniPersonali(idUtente);
	}


}
