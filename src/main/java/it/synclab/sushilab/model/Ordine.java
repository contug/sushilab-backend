package it.synclab.sushilab.model;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="ordini")
public class Ordine {

	@EmbeddedId
	@Builder.Default
	private OrdineKey id=new OrdineKey();
	
	@ManyToOne
    @MapsId("piattoId")
    @JoinColumn(name = "piatto_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
    Piatto piatto;

	@JsonIgnore
    @ManyToOne
    @MapsId("utenteId")
    @JoinColumn(name = "utente_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Utente utente;
	
	@NotNull
	private int count;
	
	private String note;
	
}
