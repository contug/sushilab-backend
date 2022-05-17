package it.synclab.sushilabbackend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="piatti")
public class Piatto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private int numero;
	
	@NotBlank
	private String variante;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private double prezzo;
	
	@NotNull
	private String [] allergeni;
	
	@NotEmpty
	private String [] ingredienti;
	
	@Default
	@NotNull
	private int limite=0;
	 
    @Min(value=0)
    @Max(value=5)
    @NotNull 
    private double valutazioneMedia;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sezione_id")
    private Sezione sezione;

    @NotBlank 
    private String immagine;
	 
    @NotNull 
    private String alt;
    
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "piatto")
    private Set<PiattoUtente> piattiUtenti= new HashSet<>();
	 
}

