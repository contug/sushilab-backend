package it.synclab.sushilab.service;

import org.springframework.http.ResponseEntity;


public interface TavoloServiceInterface {

	public ResponseEntity<?> creaSessione(String qrCode);
	
	public ResponseEntity<?>  ottieniSessione(long idT);
	
	public ResponseEntity<?> chiudiSessione(long idT);
	
}
