package it.synclab.sushilab.model;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fascia {

	private Time oraInizio;
	
	private Time OraFine;
	
}
