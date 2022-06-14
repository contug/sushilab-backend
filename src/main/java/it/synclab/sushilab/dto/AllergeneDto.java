package it.synclab.sushilab.dto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.synclab.sushilab.model.Allergene;
import it.synclab.sushilab.repository.AllergeneRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllergeneDto {

	private String nome;

	public Allergene toAllergene(AllergeneRepository allergeneRepository) {
		List<Allergene> allergeni=allergeneRepository.findAll();
		for (Allergene allergene : allergeni) {
			if (allergene.getNome().equalsIgnoreCase(nome)) {
				return Allergene
						.builder()
						.id(allergene.getId())
						.nome(allergene.getNome())
						.colore(allergene.getColore())
						.build();
			}
		}
		return null;
	}
	
}
