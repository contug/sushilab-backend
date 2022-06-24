package it.synclab.sushilab.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="piatti_utenti")
public class PiattoUtente {

	@EmbeddedId
    private PiattoUtenteKey id;
	
	@JsonIgnore
	@ManyToOne
    @MapsId("piattoId")
    @JoinColumn(name = "piatto_id")
    private Piatto piatto;

	@JsonIgnore
    @ManyToOne
    @MapsId("utenteId")
    @JoinColumn(name = "utente_id")
    private Utente utente;
    
    @Min(value=0)
    @Max(value=5)
    @NotNull 
    private float valutazioneUtente;
    
    @Default
    private boolean preferito = false; 
  
    @UpdateTimestamp 
    private Date ultimoOrdine;


    public void setId(PiattoUtenteKey id) {
        this.id = id;
    }

    public Piatto getPiatto() {
        return piatto;
    }

    public void setPiatto(Piatto piaptto) {
        this.piatto = piaptto;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public float getValutazioneUtente() {
        return valutazioneUtente;
    }

    public void setValutazioneUtente(float valutazioneUtente) {
        this.valutazioneUtente = valutazioneUtente;
    }

    public boolean isPreferito() {
        return preferito;
    }

    public void setPreferito(boolean preferito) {
        this.preferito = preferito;
    }

    public Date getUltimoOrdine() {
        return ultimoOrdine;
    }

    public void setUltimoOrdine(Date ultimoOrdine) {
        this.ultimoOrdine = ultimoOrdine;
    }
}
