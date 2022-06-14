package it.synclab.sushilab.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.synclab.sushilab.dto.PiattoDto;
import it.synclab.sushilab.model.Piatto;
import it.synclab.sushilab.service.PiattoService;

@RestController
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@PostMapping("/piatto/{menuId}/{sezioneId}")
	public ResponseEntity<?> aggiungiPiatto(@PathVariable("menuId") Long menuId, @PathVariable("sezioneId") Long sezioneId, @RequestBody PiattoDto piattoDto) throws IOException{
		return piattoService.aggiungiPiatto(menuId, sezioneId, piattoDto);
	}
	
	@GetMapping(value= "/piatto/immagine/{menuId}/{sezioneId}/{piattoId}")
	public ResponseEntity<?> visualizzaImmagine(@PathVariable("menuId") Long menuId, @PathVariable("sezioneId") Long sezioneId, @PathVariable("piattoId") Long piattoId) throws IOException{
		return piattoService.visualizzaImmagine(menuId, sezioneId, piattoId);
	}
	
	@GetMapping("/piatto/{menuId}/{sezioneId}/{piattoId}")
	public ResponseEntity<?> visualizzaPiatto(@PathVariable("menuId") Long menuId, @PathVariable("sezioneId") Long sezioneId, @PathVariable("piattoId") Long piattoId){
		return piattoService.visualizzaPiatto(menuId, sezioneId, piattoId);
	}
	


}
