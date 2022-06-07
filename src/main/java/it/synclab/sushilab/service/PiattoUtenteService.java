package it.synclab.sushilab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.synclab.sushilab.model.PiattoUtente;
import it.synclab.sushilab.repository.MenuRepository;
import it.synclab.sushilab.repository.PiattoUtenteRepository;
import it.synclab.sushilab.repository.UtenteRepository;


@Service
public class PiattoUtenteService implements PiattoUtenteServiceInterface{

	@Autowired
	private PiattoUtenteRepository repo;
	
	@Autowired
	private MenuRepository repoMenu;
	
	@Autowired
	private UtenteRepository repoUtente;
	
	@Override
	public ResponseEntity<?> getPreferiti(long idM) {
		
		boolean esisteM = repoMenu.existsById(idM);
		
		if(!esisteM)
			return new ResponseEntity<>("Menu inesitente", HttpStatus.NO_CONTENT);
		
		List<PiattoUtente> preferiti = repo.findByPreferitoAndPiatto_Sezione_Menu_Id(true, idM);

		return ResponseEntity.ok(preferiti);
	}
	
	@Override
	public ResponseEntity<?> getPreferitiUtente(long idU) {
		
		boolean esisteU = repoUtente.existsById(idU);
		
		if(!esisteU)
			return new ResponseEntity<>("Utente inesitente", HttpStatus.NO_CONTENT);
		
		List<PiattoUtente> preferiti = repo.findByPreferitoAndUtente_Id(true, idU);
		
		return ResponseEntity.ok(preferiti);
	}
	
	@Override
	public ResponseEntity<?> valutazionePiatto(long idU, long idP, float voto) {
		
		boolean trovatoU = repo.existsByUtente_Id(idU);
		
		boolean trovatoP = repo.existsByPiatto_Id(idP);
		
		if(!trovatoP)
			return new ResponseEntity<>("Piatto inesitente", HttpStatus.NO_CONTENT);

		if(!trovatoU)
			return new ResponseEntity<>("Utente inesitente", HttpStatus.NO_CONTENT);

		
		PiattoUtente piatto = repo.findByPiatto_IdAndUtente_Id(idP, idU);
		
		piatto.setValutazioneUtente(voto);
		
		repo.save(piatto);
		
		return new ResponseEntity<>("valutazione inserita", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> getValutazionePiatto(long idU, long idP) {
		
		boolean trovatoU = repo.existsByUtente_Id(idU);
		
		boolean trovatoP = repo.existsByPiatto_Id(idP);
		
		if(!trovatoP)
			return new ResponseEntity<>("Piatto inesitente", HttpStatus.NO_CONTENT);

		if(!trovatoU)
			return new ResponseEntity<>("Utente inesitente", HttpStatus.NO_CONTENT);

		float valutazione = repo.findByPiatto_IdAndUtente_Id(idP, idU).getValutazioneUtente();
		
		return ResponseEntity.ok(valutazione);
	}
	
	@Override
	public ResponseEntity<?> aggiungiPreferito(long idU, long idP) {
		
		boolean trovatoU = repo.existsByUtente_Id(idU);
		
		boolean trovatoP = repo.existsByPiatto_Id(idP);
		
		if(!trovatoP)
			return new ResponseEntity<>("Piatto inesitente", HttpStatus.NO_CONTENT);

		if(!trovatoU)
			return new ResponseEntity<>("Utente inesitente", HttpStatus.NO_CONTENT);

		PiattoUtente piatto = repo.findByPiatto_IdAndUtente_Id(idP, idU);

		if(piatto == null)
			return new ResponseEntity<>("Utente inesitente", HttpStatus.NO_CONTENT);
		
		piatto.setPreferito(true);
		
		repo.save(piatto);
		
		return new ResponseEntity<>("Piatto aggiunto ai preferiti", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> rimuoviPreferito(long idU, long idP) {
		
		boolean trovatoU = repo.existsByUtente_Id(idU);
		
		boolean trovatoP = repo.existsByPiatto_Id(idP);
		
		if(!trovatoP)
			return new ResponseEntity<>("Piatto inesitente", HttpStatus.NO_CONTENT);

		if(!trovatoU)
			return new ResponseEntity<>("Utente inesitente", HttpStatus.NO_CONTENT);

		
		PiattoUtente piatto = repo.findByPiatto_IdAndUtente_Id(idP, idU);

		if(piatto == null)
			return new ResponseEntity<>("Utente inesitente", HttpStatus.NO_CONTENT);
		
		piatto.setPreferito(false);
		
		repo.save(piatto);
	
		return new ResponseEntity<>("Piatto rimosso dai preferiti", HttpStatus.OK);
	}
	
}
