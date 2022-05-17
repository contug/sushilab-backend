package it.synclab.sushilabbackend.model;

import java.sql.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="piatti_utenti")
public class PiattoUtente {

	@EmbeddedId
    private PiattoUtenteKey id;
	
	@ManyToOne
    @MapsId("piattoId")
    @JoinColumn(name = "piatto_id")
    Piatto piatto;

    @ManyToOne
    @MapsId("utenteId")
    @JoinColumn(name = "utente_id")
    Utente utente;
    
    @Min(value=0)
    @Max(value=5)
    @NotNull 
    private int valutazioneUtente;
    
    @NotNull 
    private boolean preferito; 
  
    @UpdateTimestamp 
    private Date ultimoOrdine;
  
    @NotNull 
    private boolean popolare;
    
    @NotNull 
    private boolean consigliato;
	  
	
}
