package it.synclab.sushilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.sushilab.model.Allergene;

@Repository
public interface AllergeneRepository extends JpaRepository<Allergene, Long> {

	
	
}
