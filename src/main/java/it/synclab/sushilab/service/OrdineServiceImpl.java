package it.synclab.sushilab.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.synclab.sushilab.model.*;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.synclab.sushilab.dto.OrdineDto;
import it.synclab.sushilab.repository.OrdineRepository;
import it.synclab.sushilab.repository.PiattoRepository;
import it.synclab.sushilab.repository.TavoloRepository;
import it.synclab.sushilab.repository.UtenteRepository;

@Service
public class OrdineServiceImpl implements OrdineService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private OrdineRepository ordineRepository;
	
	@Autowired
	private PiattoRepository piattoRepository;

	@Autowired
	private TavoloRepository tavoloRepository;
	
	@Override
	public ResponseEntity<?> effettuaOrdine(Long utenteId, Set <OrdineDto> ordini) {
		if(!(utenteRepository.existsById(utenteId))) {
			return new ResponseEntity<>("Utente inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		for (OrdineDto ordineDto : ordini) {
			if(!(piattoRepository.existsById(ordineDto.getPiattoId()))) {
				return new ResponseEntity<>("Piatto inesitente", HttpStatus.METHOD_NOT_ALLOWED);
			}
			if(ordineRepository.existsByIdPiattoIdAndIdUtenteId(ordineDto.getPiattoId(), utenteId)) {
				Ordine ordine=ordineRepository.findByIdPiattoIdAndIdUtenteId(ordineDto.getPiattoId(), utenteId);
				ordine.setCount(ordine.getCount()+ordineDto.getCount());
				if(!(ordineDto.getNote().isBlank()&&!(ordine.getNote().isBlank()))){
					ordine.setNote(ordine.getNote()+"; "+ordineDto.getNote());
				}
				if(!(ordineDto.getNote().isBlank())&&(ordine.getNote().isBlank())){
					ordine.setNote(ordineDto.getNote());
				}
				ordineRepository.save(ordine);
			}
			else {
				ordineRepository.save(ordineDto.toOrdine(utenteRepository.getById(utenteId), piattoRepository));
			}
		}
		return new ResponseEntity<>("\"Ordini registrati con successo\"", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getOrdiniPersonali(Long idUtente) {
		/*if(!(utenteRepository.existsById(idUtente))) {
			return new ResponseEntity<>("Utente inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}*/

		List<Ordine> ordini = ordineRepository.getByIdUtenteId(idUtente);
		List<OrdineDettaglio> ordiniFinale = new ArrayList<>();
		ordini.forEach(element -> {
			OrdineDettaglio ordineDettaglio = new OrdineDettaglio();
			ordineDettaglio.piatto = piattoRepository.getById(element.getId().getPiattoId());
			ordineDettaglio.molteplicita = element.getCount();
			ordineDettaglio.note = element.getNote();
			ordiniFinale.add(ordineDettaglio);
		});
		return new ResponseEntity<>(ordiniFinale, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getOrdiniTavolo(Long idTavolo) {
		if(!(tavoloRepository.existsById(idTavolo))) {
			return new ResponseEntity<>("Tavolo inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		//Utente utenteExample=new Utente();
		//Tavolo tavolo=new Tavolo();
		//tavolo.setId(idTavolo);
		//utenteExample.setTavolo(tavolo);
		//Example <Utente> example=Example.of
		List<Utente> utenti=utenteRepository.findByTavoloId(idTavolo);
		List<Ordine> ordini=new ArrayList<>();
		for (Utente utente : utenti) {
			ordini.addAll(ordineRepository.getByIdUtenteId(utente.getId()));
		}
		return new ResponseEntity<>(ordini, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> eliminaOrdineInArrivo(long idUtente, long idPiatto) {
		if(!ordineRepository.existsById(new OrdineKey(idPiatto, idUtente)))
			return new ResponseEntity<>("\"Ordine inesitente\"", HttpStatus.METHOD_NOT_ALLOWED);
		ordineRepository.deleteById(new OrdineKey(idPiatto, idUtente));
		return new ResponseEntity<>("\"Ordine cancellato\"", HttpStatus.OK);

	}

	public ResponseEntity<?> aggiornaOrdineInArrivo(long idUtente, long idPiatto) {
		if(!ordineRepository.existsById(new OrdineKey(idPiatto, idUtente)))
			return new ResponseEntity<>("\"Ordine inesitente\"", HttpStatus.METHOD_NOT_ALLOWED);
		Ordine ordine = ordineRepository.getById(new OrdineKey(idPiatto, idUtente));
		ordine.setCount(ordine.getCount()-1);
		ordineRepository.save(ordine);
		return new ResponseEntity<>("\"Ordine cancellato\"", HttpStatus.OK);
	}

}
