package it.synclab.sushilab.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.sushilab.model.Ordine;
import it.synclab.sushilab.model.OrdineKey;
import it.synclab.sushilab.model.Piatto;
import it.synclab.sushilab.model.Utente;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, OrdineKey> {

	boolean existsByPiattoAndUtente(Piatto piatto, Utente utente);

	Ordine findByPiattoAndUtente(Piatto piatto, Utente utente);

	boolean existsByIdPiattoIdAndIdUtenteId(Long piattoId, Long utenteId);

	Ordine findByIdPiattoIdAndIdUtenteId(Long piattoId, Long utenteId);

	ArrayList<Ordine> getByIdUtenteId(Long idUtente);

}
