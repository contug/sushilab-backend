package it.synclab.sushilab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.sushilab.model.Sezione;

@Repository
public interface SezioneRepository extends JpaRepository<Sezione, Long> {

	List<Sezione> findByMenu_Id(long idM);
	
}
