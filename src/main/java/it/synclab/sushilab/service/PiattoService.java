package it.synclab.sushilab.service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.synclab.sushilab.dto.PiattoDto;
import it.synclab.sushilab.model.Piatto;

@Service
public interface PiattoService {

	ResponseEntity<?> aggiungiPiatto(Long menuId, Long sezioneId, PiattoDto piattoDto) throws IOException;


	ResponseEntity<?> visualizzaPiatto(Long menuId, Long sezioneId, Long piattoId);


	ResponseEntity<?> visualizzaImmagine(Long menuId, Long sezioneId, Long piattoId) throws IOException;

}
