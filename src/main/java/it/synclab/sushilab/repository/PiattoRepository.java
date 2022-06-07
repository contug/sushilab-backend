package it.synclab.sushilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.synclab.sushilab.model.Piatto;

@Repository
public interface PiattoRepository extends JpaRepository<Piatto, Long> {

}
