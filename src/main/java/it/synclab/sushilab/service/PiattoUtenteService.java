package it.synclab.sushilab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.synclab.sushilab.model.ValutazioneUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.synclab.sushilab.model.Piatto;
import it.synclab.sushilab.model.PiattoUtente;
import it.synclab.sushilab.model.PiattoUtenteKey;
import it.synclab.sushilab.repository.MenuRepository;
import it.synclab.sushilab.repository.PiattoRepository;
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
	
	@Autowired
	private PiattoRepository repoPiatto;
	
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
		
		boolean trovatoU = repoUtente.existsById(idU);
		
		boolean trovatoP = repoPiatto.existsById(idP);
		
		if(!trovatoP)
			return new ResponseEntity<>("Piatto inesitente", HttpStatus.NO_CONTENT);

		if(!trovatoU)
			return new ResponseEntity<>("Utente inesitente", HttpStatus.NO_CONTENT);
		
		if(voto > 5 || voto < 0)
			return new ResponseEntity<>("Voto deve essere compreso tra 0 e 5", HttpStatus.METHOD_NOT_ALLOWED);

		
		PiattoUtente piatto = new PiattoUtente();
		
		boolean associazione = repo.existsByPiatto_IdAndUtenteId(idP, idU);
				
		if(associazione)
			piatto = repo.findByPiatto_IdAndUtente_Id(idP, idU);
		else {
			piatto.setId(new PiattoUtenteKey(idP, idU));
			piatto.setPiatto(repoPiatto.getById(idP));
			piatto.setUtente(repoUtente.getById(idU));
		}
		
		piatto.setValutazioneUtente(voto);
		
		repo.save(piatto);
		
		//aggiorna valutazione media
		aggiornaValutazioneMedia(idP);
		
		return new ResponseEntity<>("\"valutazione inserita\"", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> getValutazionePiatto(long idU, long idP) {
		
		boolean trovatoU = repo.existsByUtente_Id(idU);
		
		boolean trovatoP = repo.existsByPiatto_Id(idP);
		
		if(!trovatoP)
			return new ResponseEntity<>("\"Piatto inesitente\"", HttpStatus.NO_CONTENT);

		if(!trovatoU)
			return new ResponseEntity<>("\"Utente inesitente\"", HttpStatus.NO_CONTENT);

		float valutazione = repo.findByPiatto_IdAndUtente_Id(idP, idU).getValutazioneUtente();
		
		return ResponseEntity.ok(valutazione);
	}
	
	@Override
	public ResponseEntity<?> aggiungiPreferito(long idU, long idP) {
		
		boolean trovatoU = repoUtente.existsById(idU);
		
		boolean trovatoP = repoPiatto.existsById(idP);
		
		if(!trovatoP)
			return new ResponseEntity<>("Piatto inesitente", HttpStatus.NO_CONTENT);

		if(!trovatoU)
			return new ResponseEntity<>("Utente inesitente", HttpStatus.NO_CONTENT);

		
		PiattoUtente piatto = new PiattoUtente();
		
		boolean associazione = repo.existsByPiatto_IdAndUtenteId(idP, idU);
		
		if(associazione)
			piatto = repo.findByPiatto_IdAndUtente_Id(idP, idU);
		else {
			piatto.setId(new PiattoUtenteKey(idP, idU));
			piatto.setPiatto(repoPiatto.getById(idP));
			piatto.setUtente(repoUtente.getById(idU));
		}
		
		piatto.setPreferito(true);
		
		repo.save(piatto);
		
		return new ResponseEntity<>("Piatto aggiunto ai preferiti", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> rimuoviPreferito(long idU, long idP) {
		
		boolean trovatoU = repoUtente.existsById(idU);
		
		boolean trovatoP = repoPiatto.existsById(idP);
		
		if(!trovatoP)
			return new ResponseEntity<>("Piatto inesitente", HttpStatus.NO_CONTENT);

		if(!trovatoU)
			return new ResponseEntity<>("Utente inesitente", HttpStatus.NO_CONTENT);

		
		PiattoUtente piatto = new PiattoUtente();
		
		boolean associazione = repo.existsByPiatto_IdAndUtenteId(idP, idU);
		
		if(associazione)
			piatto = repo.findByPiatto_IdAndUtente_Id(idP, idU);
		else {
			piatto.setId(new PiattoUtenteKey(idP, idU));
			piatto.setPiatto(repoPiatto.getById(idP));
			piatto.setUtente(repoUtente.getById(idU));
		}
		
		piatto.setPreferito(false);
		
		repo.save(piatto);
	
		return new ResponseEntity<>("Piatto rimosso dai preferiti", HttpStatus.OK);
	}
	
	
	private void aggiornaValutazioneMedia(long idP) {
		List<PiattoUtente> piatti = repo.findByPiatto_Id(idP);
		float somma = 0f;
		for(PiattoUtente piattiUtente : piatti) {
			somma += piattiUtente.getValutazioneUtente();
		}
		float media = somma / piatti.size();
		Piatto piatto = repoPiatto.getById(idP);
		piatto.setValutazioneMedia(media);
		repoPiatto.save(piatto);
	}

	public ResponseEntity<?> getValutazioniUtente(long idUtente) {
		List<PiattoUtente> listPiattoUtente = repo.findByUtente_Id(idUtente);
		List<ValutazioneUtente> list = new ArrayList<>();

		listPiattoUtente.forEach(element -> {
			ValutazioneUtente valutazioneUtente = new ValutazioneUtente();
			valutazioneUtente.idPiatto = element.getPiatto().getId();
			valutazioneUtente.valutazione = element.getValutazioneUtente();
			list.add(valutazioneUtente);
		});
		return ResponseEntity.ok(list);
	}
	
}
