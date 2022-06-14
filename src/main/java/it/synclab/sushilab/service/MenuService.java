package it.synclab.sushilab.service;

import java.sql.Time;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.synclab.sushilab.model.Fascia;
import it.synclab.sushilab.model.Menu;
import it.synclab.sushilab.repository.MenuRepository;
import it.synclab.sushilab.repository.SezioneRepository;

@Service
public class MenuService implements MenuServiceInterface{

	@Autowired
	private MenuRepository repo;
	
	@Autowired
	private SezioneRepository repoSezione;
	
	@Override
	public ResponseEntity<?> getById(long id) {
		
		Optional<Menu> foundM = repo.findById(id);
		
		if(!foundM.isPresent())
			return new ResponseEntity<>("Menu inesitente", HttpStatus.NO_CONTENT);
		
		//return ResponseEntity.ok(foundM.get());
		return ResponseEntity.ok(repoSezione.findByMenu_Id(id));

	}
	
	@Override
	public ResponseEntity<?> getFasce(long id) {
		
		boolean esisteM = repo.existsById(id);
		
		if(!esisteM)
			return new ResponseEntity<>("Menu inesitente", HttpStatus.NO_CONTENT);
		
		Time oraInizio = repo.getById(id).getOraInizio();		
		Time oraFine = repo.getById(id).getOraFine();

		Fascia ore = new Fascia(oraInizio, oraFine);
		
		return ResponseEntity.ok(ore);
	}
}
