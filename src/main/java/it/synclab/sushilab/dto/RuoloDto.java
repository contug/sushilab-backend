package it.synclab.sushilab.dto;

import it.synclab.sushilab.model.Ruolo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuoloDto {

	private String nome;
	
	public Ruolo toRuolo() {
		
		return Ruolo
				.builder()
				.nome(nome)
				.build();
		
	}
	
}
