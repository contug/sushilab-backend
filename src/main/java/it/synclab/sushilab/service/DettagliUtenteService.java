package it.synclab.sushilab.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.synclab.sushilab.model.Ruolo;
import it.synclab.sushilab.model.Utente;
import it.synclab.sushilab.repository.UtenteRepository;

@Service
public class DettagliUtenteService implements UserDetailsService{
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Utente utente=utenteRepository.findByEmail(email)
				.orElseThrow(()->
						new UsernameNotFoundException("User not foud with email: "+email));
		return new org.springframework.security.core.userdetails.User(utente.getEmail(), utente.getPassword(), mapRolesToAuthorities(utente.getRuoli()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities (Set<Ruolo> ruoli){
		
		return ruoli.stream().map(ruolo -> new SimpleGrantedAuthority(ruolo.getNome())).collect(Collectors.toList());
	
	}
	
}
