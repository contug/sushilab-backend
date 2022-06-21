package it.synclab.sushilab.service;


import org.springframework.http.ResponseEntity;


public interface PiattoUtenteServiceInterface {

	public ResponseEntity<?> getPreferiti(long idM);
	
	public ResponseEntity<?> getPreferitiUtente(long idU);
	
	public ResponseEntity<?> valutazionePiatto(long idU, long idP, float voto);
	
	public ResponseEntity<?> getValutazionePiatto(long idU, long idP);
	
	public ResponseEntity<?> aggiungiPreferito(long idU, long idP);
	
	public ResponseEntity<?> rimuoviPreferito(long idU, long idP);

	public ResponseEntity<?> getValutazioniUtente(long idU);
	
}
