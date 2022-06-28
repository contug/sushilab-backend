package it.synclab.sushilab.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.synclab.sushilab.dto.IngredienteDto;
import it.synclab.sushilab.dto.UtenteDto;
import it.synclab.sushilab.model.Ingrediente;
import it.synclab.sushilab.model.Utente;
import it.synclab.sushilab.repository.AllergeneRepository;
import it.synclab.sushilab.repository.IngredienteRepository;
import it.synclab.sushilab.repository.TavoloRepository;
import it.synclab.sushilab.repository.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {
	
	
	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private TavoloRepository tavoloRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private AllergeneRepository allergeneRepository;

	/*
	 * @Override public ResponseEntity<?> registrazioneUtente(UtenteDto utenteDto) {
	 * if(utenteRepository.existsByEmail(utenteDto.getEmail())) { return new
	 * ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST); }
	 * 
	 * Utente utente=utenteDto.toUtente();
	 * 
	 * utente.setPassword(passwordEncoder.encode(utente.getPassword()));
	 * 
	 * utenteRepository.save(utente);
	 * 
	 * return new ResponseEntity<>("User registered successfully", HttpStatus.OK); }
	 * 
	 * @Override public ResponseEntity<String> login(UtenteDto utenteDto) {
	 * Authentication authentication=authenticationManager.authenticate(new
	 * UsernamePasswordAuthenticationToken( utenteDto.getEmail(),
	 * utenteDto.getPassword()));
	 * 
	 * SecurityContextHolder.getContext().setAuthentication(authentication);
	 * 
	 * return new ResponseEntity<String>("User signed-in successfully!",
	 * HttpStatus.OK); }
	 */

	@Override
	public ResponseEntity<?> partecipaSessione(Long tavoloId, Long utenteId) {
		if(!(tavoloRepository.existsById(tavoloId))) {
			return new ResponseEntity<>("\"Tavolo inesitente\"", HttpStatus.METHOD_NOT_ALLOWED);
		}
		if(!(utenteRepository.existsById(utenteId))) {
			return new ResponseEntity<>("\"Utente inesitente\"", HttpStatus.METHOD_NOT_ALLOWED);
		}
		Utente utente=utenteRepository.getById(utenteId);
		utente.setTavolo(tavoloRepository.getById(tavoloId));
		utenteRepository.save(utente);
		return new ResponseEntity<>("\"Utente collegato alla sessione\"", HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> esciSessione(Long utenteId) {
		
		if(!(utenteRepository.existsById(utenteId)))
			return new ResponseEntity<>("\"Utente inesitente\"", HttpStatus.METHOD_NOT_ALLOWED);

		Utente utente = utenteRepository.getById(utenteId);
		utente.setTavolo(null);
		utenteRepository.save(utente);
		
		return new ResponseEntity<>("\"Utente disconnesso dalla sessione\"", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> aggiornaBlacklist(Long utenteId, Set<IngredienteDto> ingredientiDto) {
		if(!(utenteRepository.existsById(utenteId))) {
			return new ResponseEntity<>("Utente inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		Set<Ingrediente> ingredienti=new HashSet<>();
		Utente utente=utenteRepository.getById(utenteId);
		for (IngredienteDto ingredienteDto : ingredientiDto) {
			Ingrediente ingrediente=ingredienteDto.toIngrediente(ingredienteRepository);
			if(!(utente.getBlackList().contains(ingrediente))){
				ingredienti.add(ingrediente);
			}
		}
		utente.getBlackList().addAll(ingredienti);
		utenteRepository.save(utente);
		return new ResponseEntity<>("\"Blacklist aggiornata\"", HttpStatus.OK);
	}

	/*
	 * @Override public ResponseEntity<?> aggiornaBlacklist(Long utenteId,
	 * IngredienteDto ingredienteDto) { if(!(utenteRepository.existsById(utenteId)))
	 * { return new ResponseEntity<>("Utente inesitente",
	 * HttpStatus.METHOD_NOT_ALLOWED); }
	 * 
	 * Ingrediente ingrediente=ingredienteDto.toIngrediente(ingredienteRepository);
	 * 
	 * 
	 * Utente utente=utenteRepository.getById(utenteId);
	 * 
	 * Set<Ingrediente> blacklist = utente.getBlackList();
	 * blacklist.add(ingrediente); utente.setBlackList(blacklist);
	 * utenteRepository.save(utente);
	 * 
	 * return new ResponseEntity<>("Blacklist aggiornata", HttpStatus.OK); }
	 */

	@Override
	public ResponseEntity<?> getAllergeni() {
		return new ResponseEntity<>(allergeneRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getIngredienti() {
		return new ResponseEntity<>(ingredienteRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getBlacklist(Long idUtente) {
		if(!(utenteRepository.existsById(idUtente))){
			return new ResponseEntity<>("Utente inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		return new ResponseEntity<>(utenteRepository.getById(idUtente).getBlackList(), HttpStatus.OK);
	}

	/*@Override
	public ResponseEntity<?> eliminaBlacklist(Long idUtente, Set<IngredienteDto> ingredientiDto) {
		if(!(utenteRepository.existsById(idUtente))){
			return new ResponseEntity<>("Utente inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		Set<Ingrediente> ingredienti=new HashSet<>();
		for (IngredienteDto ingredienteDto : ingredientiDto) {
			Ingrediente ingrediente=ingredienteDto.toIngrediente(ingredienteRepository);
			ingredienti.add(ingrediente);
		}
		
		Utente utente=utenteRepository.getById(idUtente);
		utente.getBlackList().removeAll(ingredienti);
		utenteRepository.save(utente);
		return new ResponseEntity<>("Blacklist aggiornata", HttpStatus.OK);
	}*/

	@Override
	public ResponseEntity<?> eliminaBlacklist(Long idUtente, IngredienteDto ingredientiDto) {
		if(!(utenteRepository.existsById(idUtente))){
			return new ResponseEntity<>("Utente inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		Ingrediente ingrediente=ingredientiDto.toIngrediente(ingredienteRepository);
		Utente utente=utenteRepository.getById(idUtente);
		utente.getBlackList().remove(ingrediente);
		utenteRepository.save(utente);
		return new ResponseEntity<>("\"Blacklist aggiornata\"", HttpStatus.OK);
	}

}
