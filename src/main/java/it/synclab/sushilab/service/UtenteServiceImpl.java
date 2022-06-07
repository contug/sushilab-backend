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
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TavoloRepository tavoloRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private AllergeneRepository allergeneRepository;

	@Override
	public ResponseEntity<?> registrazioneUtente(UtenteDto utenteDto) {
		if(utenteRepository.existsByEmail(utenteDto.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}
		
		Utente utente=utenteDto.toUtente();
		
		utente.setPassword(passwordEncoder.encode(utente.getPassword()));
		
		utenteRepository.save(utente);
		
		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> login(UtenteDto utenteDto) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				utenteDto.getEmail(), utenteDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseEntity<String>("User signed-in successfully!", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> partecipaSessione(Long tavoloId, Long utenteId) {
		if(!(tavoloRepository.existsById(tavoloId))) {
			return new ResponseEntity<>("Tavolo inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		if(!(utenteRepository.existsById(utenteId))) {
			return new ResponseEntity<>("Utente inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		Utente utente=utenteRepository.getById(utenteId);
		utente.setTavolo(tavoloRepository.getById(tavoloId));
		utenteRepository.save(utente);
		return new ResponseEntity<>("Utente collegato alla sessione", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> aggiornaBlacklist(Long utenteId, Set<IngredienteDto> ingredientiDto) {
		if(!(utenteRepository.existsById(utenteId))) {
			return new ResponseEntity<>("Utente inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		Set<Ingrediente> ingredienti=new HashSet<>();
		for (IngredienteDto ingredienteDto : ingredientiDto) {
			Ingrediente ingrediente=ingredienteDto.toIngrediente(ingredienteRepository);
			ingredienti.add(ingrediente);
		}
		Utente utente=utenteRepository.getById(utenteId);
		utente.setBlackList(ingredienti);
		utenteRepository.save(utente);
		return new ResponseEntity<>("Blacklist aggiornata", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllergeni() {
		return new ResponseEntity<>(allergeneRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getBlacklist(Long idUtente) {
		if(!(utenteRepository.existsById(idUtente))){
			return new ResponseEntity<>("Utente inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		return new ResponseEntity<>(utenteRepository.getById(idUtente).getBlackList(), HttpStatus.OK);
	}

	@Override
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
		
		
	}

}
