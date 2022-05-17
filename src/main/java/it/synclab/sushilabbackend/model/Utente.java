package it.synclab.sushilabbackend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="utenti")
public class Utente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotBlank
	private String email;
	
	@Default
	@NotNull
	private boolean isGestore=false;
	
	@NotNull
	private int password;
	
	@Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "utente")
    private Set<PiattoUtente> piattiUtenti = new HashSet<>();

}
