package it.synclab.sushilab.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tavoli", uniqueConstraints = @UniqueConstraint(columnNames="qr_code"))
public class Tavolo {

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "qr_code")
	private String qrCode;
	
	@Builder.Default
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "tavoli_menu",
            joinColumns = @JoinColumn(
                    name = "tavolo_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "menu_id", referencedColumnName = "id"
            )
    )
    private Set<Menu> menu = new HashSet<>();
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "tavolo")
	@JsonManagedReference
    private Set<Utente> utenti = new HashSet<>();
	
}
