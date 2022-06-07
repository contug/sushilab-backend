package it.synclab.sushilab.dto;

import java.util.Collections;

import it.synclab.sushilab.model.Ordine;
import it.synclab.sushilab.model.Piatto;
import it.synclab.sushilab.model.Utente;
import it.synclab.sushilab.repository.PiattoRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdineDto {

	private Long piattoId;
	private int count;
	private String note;
	
	public Ordine toOrdine(Utente utente, PiattoRepository piattoRepository) {
		  
		return Ordine 
			  .builder() 
			  .piatto(piattoRepository.getById(piattoId))
			  .count(count)
			  .note(note)
			  .utente(utente)
			  .build();
	  
	}
	
}
