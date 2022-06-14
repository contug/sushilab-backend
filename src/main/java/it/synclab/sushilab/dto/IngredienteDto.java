package it.synclab.sushilab.dto;

import java.util.Iterator;
import java.util.List;

import it.synclab.sushilab.model.Ingrediente;
import it.synclab.sushilab.repository.IngredienteRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredienteDto {

	private String nome;
	
	public Ingrediente toIngrediente(IngredienteRepository ingredienteRepository) {
		List<Ingrediente> ingredienti=ingredienteRepository.findAll();
		for (Ingrediente ingrediente : ingredienti) {
			if(ingrediente.getNome().equalsIgnoreCase(nome)) {
				return Ingrediente
						.builder()
						.id(ingrediente.getId())
						.nome(ingrediente.getNome())
						.build();
			}
		}
		Ingrediente ingrediente=Ingrediente
										.builder()
										.nome(nome)
										.build();
		ingredienteRepository.save(ingrediente);
		return ingrediente;
	}
	
}
