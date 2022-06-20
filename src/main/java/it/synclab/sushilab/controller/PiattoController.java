package it.synclab.sushilab.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.synclab.sushilab.dto.PiattoDto;
import it.synclab.sushilab.model.Piatto;
import it.synclab.sushilab.service.PiattoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@PostMapping("/piatto/{menuId}/{sezioneId}")
	public ResponseEntity<?> aggiungiPiatto(@PathVariable("menuId") Long menuId, @PathVariable("sezioneId") Long sezioneId, @RequestBody PiattoDto piattoDto) throws IOException{
		return piattoService.aggiungiPiatto(menuId, sezioneId, piattoDto);
	}
	
	@GetMapping(value= "/img/{piattoId}")
	public ResponseEntity<?> visualizzaImmagine(@PathVariable("piattoId") Long piattoId) throws IOException{
		return piattoService.visualizzaImmagine(piattoId);
	}
	
	@GetMapping("/piatto/{menuId}/{sezioneId}/{piattoId}")
	public ResponseEntity<?> visualizzaPiatto(@PathVariable("menuId") Long menuId, @PathVariable("sezioneId") Long sezioneId, @PathVariable("piattoId") Long piattoId){
		return piattoService.visualizzaPiatto(menuId, sezioneId, piattoId);
	}
	


}
