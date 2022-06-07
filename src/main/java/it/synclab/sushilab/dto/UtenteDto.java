package it.synclab.sushilab.dto;

import java.util.Collections;

import it.synclab.sushilab.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtenteDto {
	
	private String email;
	private String password;
	
	
	public Utente toUtente() {
	  
		return Utente 
			  .builder() 
			  .email(email)
			  .password(password) 
			  .ruoli(Collections.singleton(new RuoloDto("ROLE_USER").toRuolo())) 
			  .build();
	  
	}
	 
	
}
