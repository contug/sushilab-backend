package it.synclab.sushilab.dto;

import it.synclab.sushilab.model.Allergene;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllergeneInizialeDto {

	private String nome;
	private String colore;
	
	public Allergene toAllergene() {
		
		return Allergene
				.builder()
				.nome(nome)
				.colore(colore)
				.build();
				
		
	}
	
}
