package it.synclab.sushilab.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.synclab.sushilab.dto.IngredienteDto;
import it.synclab.sushilab.dto.UtenteDto;

@Service
public interface UtenteService {

	//public ResponseEntity<?> registrazioneUtente(UtenteDto utenteDto);

	//public ResponseEntity<String> login(UtenteDto utenteDto);

	public ResponseEntity<?> partecipaSessione(Long tavoloId, Long utenteId);

	public ResponseEntity<?> aggiornaBlacklist(Long utenteId, Set<IngredienteDto> ingrediente);

	public ResponseEntity<?> getAllergeni();

	public ResponseEntity<?> getBlacklist(Long idUtente);

	public ResponseEntity<?> eliminaBlacklist(Long idUtente, IngredienteDto ingredienti);
	
}
