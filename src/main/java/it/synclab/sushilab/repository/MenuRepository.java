package it.synclab.sushilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.sushilab.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

	
	
}
