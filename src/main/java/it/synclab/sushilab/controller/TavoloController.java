package it.synclab.sushilab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.synclab.sushilab.service.TavoloServiceInterface;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TavoloController {

	@Autowired
	private TavoloServiceInterface service;
	
	
	@PostMapping(path = "/tavolo/qr/{idTavolo}/{idUtente}")
	public ResponseEntity<?> creaSessione(@PathVariable("idTavolo") Long idTavolo, @PathVariable("idUtente") long idUtente, @RequestBody String qrCode) {
		return service.creaSessione(qrCode, idTavolo, idUtente);
	}
	
	@GetMapping(path = "/tavolo/{idTavolo}/{idUtente}")
	public ResponseEntity<?> ottieniSessione(@PathVariable("idTavolo") long idT, @PathVariable("idUtente") long idUtente) {
		return service.ottieniSessione(idT, idUtente);
	}
	
	@GetMapping(path = "/tavolo/qr/{qrCode}/{idUtente}")
	public ResponseEntity<?> checkSessione(@PathVariable("qrCode") String qrCode, @PathVariable("idUtente") long idUtente) {
		return service.checkSessione(qrCode, idUtente);
	}
	
	@DeleteMapping(path = "tavolo/qr/{idTavolo}")
	public ResponseEntity<?> chiudiSessione(@PathVariable("idTavolo") long idT) {
		return service.chiudiSessione(idT);
	}
}
