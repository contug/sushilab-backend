package it.synclab.sushilab.repository;

import java.util.List;

import it.synclab.sushilab.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.sushilab.model.PiattoUtente;
import it.synclab.sushilab.model.PiattoUtenteKey;

@Repository
public interface PiattoUtenteRepository extends JpaRepository<PiattoUtente, PiattoUtenteKey> {

	public List<PiattoUtente> findByPreferitoAndPiatto_Sezione_Menu_Id(boolean preferito, long idM);
	
	public List<PiattoUtente> findByPreferitoAndUtente_Id(boolean preferito, long idU);
	
	public List<PiattoUtente> findByPiatto_Id(long idP);
		
	public PiattoUtente findByPiatto_IdAndUtente_Id(long idP, long idU);
	
	public boolean existsByPiatto_Id(long idP);
	
	public boolean existsByUtente_Id(long idU);
	
	public boolean existsByPiatto_IdAndUtenteId(long idP, long idU);

	public List<PiattoUtente> findByUtente_Id(long idU);
}
