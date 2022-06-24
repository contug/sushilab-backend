package it.synclab.sushilab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.synclab.sushilab.service.PiattoUtenteServiceInterface;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PiattoUtenteController {


    @Autowired
    private PiattoUtenteServiceInterface service;


    @GetMapping(path = "/menu/{idMenu}/preferiti")
    public ResponseEntity<?> getPreferiti(@PathVariable("idMenu") long idM) {
        return service.getPreferiti(idM);
    }

    @GetMapping(path = "/preferiti/{idUtente}/{idPiatto}")
    public ResponseEntity<?> getPreferito(@PathVariable("idUtente") long idUtente, @PathVariable("idPiatto") long idPiatto)  {
        return service.getPreferito(idUtente, idPiatto);
    }


    @GetMapping(path = "preferiti/{idUtente}")
    public ResponseEntity<?> getPreferitiUtente(@PathVariable("idUtente") long idU) {
        return service.getPreferitiUtente(idU);
    }

    @PostMapping(path = "/valutazione/{idUtente}/{idPiatto}")
    public ResponseEntity<?> valutazionePiatto(@PathVariable("idUtente") long idU, @PathVariable("idPiatto") long idP, @RequestBody int valutazione) {
        return service.valutazionePiatto(idU, idP, (float) valutazione);
    }


    @GetMapping(path = "/valutazione/{idUtente}/{idPiatto}")
    public ResponseEntity<?> getValutazionePiatto(@PathVariable("idUtente") long idU, @PathVariable("idPiatto") long idP) {
        return service.getValutazionePiatto(idU, idP);
    }

    @PostMapping(path = "/preferiti/{idUtente}")
    public ResponseEntity<?> aggiungiPreferito(@PathVariable("idUtente") long idU, @RequestBody long idP) {
        return service.aggiungiPreferito(idU, idP);
    }

/*
    @DeleteMapping(path = "/preferiti/{idUtente}/{idPiatto}")
*/

    @PostMapping("/preferiti/{idUtente}/{idPiatto}")
    public ResponseEntity<?> rimuoviPreferito(@PathVariable("idUtente") long idU, @PathVariable("idPiatto") long idPiatto) {
        return service.rimuoviPreferito(idU, idPiatto);
    }

    @GetMapping("/preferiti/{idUtente}/all")
    public ResponseEntity<?> getAllPreferiti(@PathVariable("idUtente") long idUtente) {
        return service.getAllPreferiti(idUtente);
    }

    @GetMapping("/tavolo/valutazioni/{idUtente}")
    public ResponseEntity<?> getValutazioniUtente(@PathVariable("idUtente") long idU) {
        return service.getValutazioniUtente(idU);
    }

}
