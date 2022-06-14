package it.synclab.sushilab.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
//import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="utenti", uniqueConstraints = @UniqueConstraint(columnNames="email"))
public class Utente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name="utenti_ruoli",
			joinColumns = @JoinColumn(name="utente_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="ruolo_id", referencedColumnName = "id"))
	private Set<Ruolo> ruoli;
	
	@Builder.Default
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "utente")
    private Set<PiattoUtente> piattiUtenti = new HashSet<>();
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "utente")
	private Set<Ordine> ordini=new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tavolo_id")
	@JsonBackReference
    private Tavolo tavolo;
	
	@Builder.Default
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name="blackList",
			joinColumns = @JoinColumn(name="utente_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="ingrediente_id", referencedColumnName = "id"))
	private Set<Ingrediente> blackList=new HashSet<>();
	
	
	public long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Ruolo> getRuoli() {
		return ruoli;
	}
	
	public Set<PiattoUtente> getPiattiUtenti() {
		return piattiUtenti;
	}
	
	public Set<Ordine> getOrdini() {
		return ordini;
	}
	
	public Tavolo getTavolo() {
		return tavolo;
	}
	
	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}
	
	public Set<Ingrediente> getBlackList() {
		return blackList;
	}
	
	public void setBlackList(Set<Ingrediente> blackList) {
		this.blackList = blackList;
	}
	
}
