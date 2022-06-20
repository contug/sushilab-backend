package it.synclab.sushilab.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import it.synclab.sushilab.model.Immagine;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import it.synclab.sushilab.dto.PiattoDto;
import it.synclab.sushilab.model.Menu;
import it.synclab.sushilab.model.Piatto;
import it.synclab.sushilab.model.Sezione;
import it.synclab.sushilab.repository.AllergeneRepository;
import it.synclab.sushilab.repository.IngredienteRepository;
import it.synclab.sushilab.repository.MenuRepository;
import it.synclab.sushilab.repository.PiattoRepository;
import it.synclab.sushilab.repository.SezioneRepository;
@Service
public class PiattoServiceImpl implements PiattoService {

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private SezioneRepository sezioneRepository;
	
	@Autowired
	private PiattoRepository piattoRepository;
	
	@Autowired
	private AllergeneRepository allergeneRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Override
	public ResponseEntity<?> aggiungiPiatto(Long menuId, Long sezioneId, PiattoDto piattoDto) throws IOException {
		if(!(menuRepository.existsById(menuId))) {
			return new ResponseEntity<>("Menu inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		
		if(!(sezioneRepository.existsById(sezioneId))) {
			return new ResponseEntity<>("Sezione inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}

		Piatto piatto=piattoDto.toPiatto(allergeneRepository, ingredienteRepository);
		if(piatto.getAllergeni().contains(null)) {
			return new ResponseEntity<>("Allergene inesistente", HttpStatus.BAD_REQUEST);
		}
		piatto.setSezione(sezioneRepository.getById(sezioneId));
		piattoRepository.save(piatto);
		return new ResponseEntity<>("Piatto registrato con successo", HttpStatus.OK);
		
	}


	@Override
	public ResponseEntity<?> visualizzaPiatto(Long menuId, Long sezioneId, Long piattoId) {
		if(!(menuRepository.existsById(menuId))) {
			return new ResponseEntity<>("Menu inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		
		if(!(sezioneRepository.existsById(sezioneId))) {
			return new ResponseEntity<>("Sezione inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		if(!(piattoRepository.existsById(piattoId))) {
			return new ResponseEntity<>("Piatto inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		return ResponseEntity.ok(piattoRepository.findById(piattoId));
	}


	@Override
	public ResponseEntity<?> visualizzaImmagine(Long piattoId) throws IOException {
	/*	if(!(menuRepository.existsById(menuId))) {
			return new ResponseEntity<>("Menu inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		
		if(!(sezioneRepository.existsById(sezioneId))) {
			return new ResponseEntity<>("Sezione inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}*/
		if(!(piattoRepository.existsById(piattoId))) {
			return new ResponseEntity<>("Piatto inesitente", HttpStatus.METHOD_NOT_ALLOWED);
		}
		InputStream in= getClass().getResourceAsStream("/piatto-photos/"+piattoId+"/"+piattoId+".png");
		/*return new ResponseEntity<>(IOUtils.toByteArray(in), HttpStatus.OK);*/
		byte[] imgToByteArray=IOUtils.toByteArray(in);
		String base64 = Base64.getEncoder().encodeToString(imgToByteArray);
		Immagine immagine = new Immagine();
		immagine.img = "data:image/png; base64, " + base64;
		return new ResponseEntity<>(immagine, HttpStatus.OK);
	}
	
	public @ResponseBody byte[] getImmagine() throws IOException {
		InputStream in= getClass().getResourceAsStream("/user-photos/20/20.png");
		System.out.println(in); 
		return IOUtils.toByteArray(in); 
	}

}
