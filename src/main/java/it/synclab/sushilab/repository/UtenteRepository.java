package it.synclab.sushilab.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.sushilab.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

	Optional<Utente> findByEmail(String email);
	Boolean existsByEmail(String email);
	List<Utente> findByTavoloId(Long idTavolo);
	
}
