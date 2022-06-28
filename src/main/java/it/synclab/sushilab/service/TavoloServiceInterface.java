package it.synclab.sushilab.service;

import org.springframework.http.ResponseEntity;


public interface TavoloServiceInterface {

	public ResponseEntity<?> creaSessione(String qrCode, Long idTavolo, long idUtente);
	
	public ResponseEntity<?> ottieniSessione(long idT, long idUtente);
	
	public ResponseEntity<?> checkSessione(String qrCode, long idUtente);
	
	public ResponseEntity<?> chiudiSessione(long idT);
	
}
