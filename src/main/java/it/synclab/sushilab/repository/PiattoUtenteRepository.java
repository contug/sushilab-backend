package it.synclab.sushilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.sushilab.model.PiattoUtente;
import it.synclab.sushilab.model.PiattoUtenteKey;

@Repository
public interface PiattoUtenteRepository extends JpaRepository<PiattoUtente, PiattoUtenteKey> {

}
