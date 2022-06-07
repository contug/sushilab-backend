package it.synclab.sushilab.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;
import org.hibernate.annotations.Cascade;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
	
	/*
	 * @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	 * org.hibernate.annotations.CascadeType.MERGE,
	 * org.hibernate.annotations.CascadeType.PERSIST })
	 */
	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "piatti_allergeni",
            joinColumns = @JoinColumn(
                    name = "piatto_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "allergene_id", referencedColumnName = "id"
            )
    )
	private Set<Allergene> allergeni=new HashSet<>();
	
	/*
	 * @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	 * org.hibernate.annotations.CascadeType.MERGE,
	 * org.hibernate.annotations.CascadeType.PERSIST })
	 */
	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "piatti_ingredienti",
            joinColumns = @JoinColumn(
                    name = "piatto_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ingrediente_id", referencedColumnName = "id"
            )
    )
	private Set<Ingrediente> ingredienti=new HashSet<>();
	
	@Default
	@NotNull
	private int limite=0;
	 
    @Min(value=0)
    @Max(value=5)
    @NotNull 
    private double valutazioneMedia;
    
    @NotNull 
    private boolean popolare;
    
    @NotNull 
    private boolean consigliato;
	
    @JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sezione_id")
    private Sezione sezione;

    
    @Builder.Default
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "piatto")
    private Set<PiattoUtente> piattiUtenti= new HashSet<>();
    
    @JsonIgnore
    @Builder.Default
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "piatto")
    private Set<Ordine> ordini=new HashSet<>();
    
	/*
	 * @Transient public String getPhotosImagePath() { if (immagine == null || id ==
	 * null) { return null; }
	 * 
	 * return "/user-photos/" + id + "/" + immagine; }
	 */

	/*
	 * public @ResponseBody byte[] getImmagine() throws IOException { InputStream in
	 * = getClass().getResourceAsStream("/user-photos/20/20.png");
	 * System.out.println(in); return IOUtils.toByteArray(in); }
	 */
    
}

