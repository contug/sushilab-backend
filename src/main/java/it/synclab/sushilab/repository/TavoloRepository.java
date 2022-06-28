package it.synclab.sushilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.sushilab.model.Tavolo;

@Repository
public interface TavoloRepository extends JpaRepository<Tavolo, Long> {
	
	public boolean existsByQrCode(String qrCode);
	
	public Tavolo findByQrCode(String qrCode);
	
}
