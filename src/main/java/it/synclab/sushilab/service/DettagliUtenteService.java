package it.synclab.sushilab.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.synclab.sushilab.repository.UtenteRepository;
import it.synclab.sushilab.model.Utente;
import it.synclab.sushilab.dto.UtenteDto;

@Service
public class DettagliUtenteService implements UserDetailsService{
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Utente utente=utenteRepository.findByEmail(email)
				.orElseThrow(()->
						new UsernameNotFoundException("User not foud with email: "+email));
		return new org.springframework.security.core.userdetails.User(utente.getEmail(), utente.getPassword(), new ArrayList<>());
	}
	
	public ResponseEntity<?> save(UtenteDto utenteDto) {
		if(utenteRepository.existsByEmail(utenteDto.getEmail())) { 
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST); 
		}
		Utente utente=utenteDto.toUtente();
		utente.setPassword(bcryptEncoder.encode(utente.getPassword()));  
		utenteRepository.save(utente);
		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}
	
}
