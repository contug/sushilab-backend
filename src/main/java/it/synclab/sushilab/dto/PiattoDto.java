package it.synclab.sushilab.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import it.synclab.sushilab.model.Allergene;
import it.synclab.sushilab.model.Ingrediente;
import it.synclab.sushilab.model.Piatto;
import it.synclab.sushilab.model.Sezione;
import it.synclab.sushilab.repository.AllergeneRepository;
import it.synclab.sushilab.repository.IngredienteRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Data
//@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class PiattoDto {

	@Builder
	public PiattoDto(int numero, String variante, String nome, double prezzo, Set<AllergeneDto> allergeniDto,
			Set<IngredienteDto> ingredientiDto, int limite, double valutazioneMedia, boolean popolare,
			boolean consigliato) {
		super();
		this.numero = numero;
		this.variante = variante;
		this.nome = nome;
		this.prezzo = prezzo;
		this.allergeniDto = allergeniDto;
		this.ingredientiDto = ingredientiDto;
		this.limite = limite;
		this.valutazioneMedia = valutazioneMedia;
		this.popolare = popolare;
		this.consigliato = consigliato;
	}

	private int numero;
	
	private String variante;
	
	private String nome;
	
	private double prezzo;
	
	private Set<AllergeneDto> allergeniDto= new HashSet<AllergeneDto>();
	
	private Set<IngredienteDto> ingredientiDto=new HashSet<IngredienteDto>();
	
	private int limite;
	 
    private double valutazioneMedia;
   
    private boolean popolare;
    
    private boolean consigliato;
    
    public Piatto toPiatto(AllergeneRepository allergeneRepository, IngredienteRepository ingredienteRepository) {
    	HashSet<Allergene> allergeni=new HashSet<>();
    	HashSet<Ingrediente> ingredienti=new HashSet<>();
    	for (AllergeneDto allergeneDto : allergeniDto) {
			allergeni.add(allergeneDto.toAllergene(allergeneRepository));	
		}
    	for (IngredienteDto ingredienteDto : ingredientiDto) {
			ingredienti.add(ingredienteDto.toIngrediente(ingredienteRepository));
		}
    	return Piatto
    			.builder()
				.numero(numero)
				.variante(variante)
				.nome(nome)
				.prezzo(prezzo)
				.allergeni(allergeni)
				.ingredienti(ingredienti)
				.limite(limite)
				.valutazioneMedia(valutazioneMedia)
				.popolare(popolare)
				.consigliato(consigliato)
				.build();
		
    	
    }
	
}
