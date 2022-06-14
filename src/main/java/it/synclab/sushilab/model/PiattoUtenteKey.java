package it.synclab.sushilab.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PiattoUtenteKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "piatto_id")
    private Long piattoId;

    @Column(name = "utente_id")
    private Long utenteId;
	
}
