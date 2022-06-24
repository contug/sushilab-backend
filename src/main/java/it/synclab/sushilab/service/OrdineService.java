package it.synclab.sushilab.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.synclab.sushilab.dto.OrdineDto;

@Service
public interface OrdineService {

	public ResponseEntity<?> effettuaOrdine(Long utenteId, Set<OrdineDto> ordini);

	public ResponseEntity<?> getOrdiniTavolo(Long idTavolo);

	public ResponseEntity<?> getOrdiniPersonali(Long idUtente);

	public ResponseEntity<?> eliminaOrdineInArrivo(long idUtente, long idPiatto);


	public ResponseEntity<?> aggiornaOrdineInArrivo(long idUtente, long idPiatto);
}
