package it.synclab.sushilab.service;

import java.sql.Time;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.synclab.sushilab.model.Menu;
import it.synclab.sushilab.model.Tavolo;
import it.synclab.sushilab.repository.MenuRepository;
import it.synclab.sushilab.repository.TavoloRepository;

@Service
public class TavoloService implements TavoloServiceInterface{

	@Autowired
	private TavoloRepository repo;
	
	@Autowired
	private MenuRepository repoMenu;
	
	@Autowired
	private UtenteServiceImpl utenteService;
	
	@Override
	@SuppressWarnings("deprecation")
	public ResponseEntity<?> creaSessione(String qrCode, Long idTavolo, long idUtente) {
		                  
        Calendar rightNow = Calendar.getInstance();
        Time oraAttuale = new Time(rightNow.get(Calendar.HOUR_OF_DAY), rightNow.get(Calendar.MINUTE), rightNow.get(Calendar.SECOND));
		
        Optional<Menu> foundMenu = Optional.of(repoMenu.findByOraInizioLessThanAndOraFineGreaterThan(oraAttuale, oraAttuale));        
                
        
        if(foundMenu == null)
        	return new ResponseEntity<>("\"Menu inesitente\"", HttpStatus.METHOD_NOT_ALLOWED);
        
        Tavolo tavolo = new Tavolo();
        
        Set<Menu> menu = new HashSet<>();
        menu.add(foundMenu.get());
        
        tavolo.setId(idTavolo);
        tavolo.setQrCode(qrCode);
        tavolo.setMenu(menu);
		tavolo = repo.save(tavolo);

		//UtenteServiceImpl utenteService = new UtenteServiceImpl();
		utenteService.partecipaSessione(tavolo.getId(), idUtente);
		
		return new ResponseEntity<>("\"Sessione creata\"", HttpStatus.OK);

	}
	
	@Override
	public ResponseEntity<?>  ottieniSessione(long idT, long idUtente) {
		
		Optional<Tavolo> foundT = repo.findById(idT);
		
		if(!foundT.isPresent())
			return new ResponseEntity<>("\"Sessione inesistente\"", HttpStatus.NO_CONTENT);
		else {
			UtenteService utenteService = new UtenteServiceImpl();
			utenteService.partecipaSessione(idT, idUtente);
		}
			
		return ResponseEntity.ok(foundT.get());
	}
	
	@Override
	public ResponseEntity<?> chiudiSessione(long idT) {
		
		Optional<Tavolo> foundT = repo.findById(idT);
		
		if(!foundT.isPresent())
			return new ResponseEntity<>("\"Sessione inesistente\"", HttpStatus.METHOD_NOT_ALLOWED);
		
		Tavolo tavolo = foundT.get();
		
		tavolo.setQrCode(null);
		
		repo.save(tavolo);

		return new ResponseEntity<>("\"Sessione chiusa\"", HttpStatus.OK);

	}
}
