package it.synclab.sushilab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import it.synclab.sushilab.dto.AllergeneInizialeDto;
import it.synclab.sushilab.repository.AllergeneRepository;

@SpringBootApplication
public class SushilabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SushilabApplication.class, args);
    }

    @Autowired
    private AllergeneRepository allergeneRepository;
    
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartUp() {
    	if(allergeneRepository.count()==0) {
    		allergeneRepository.save(new AllergeneInizialeDto("glutine", "#ff4500").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("latticini", "#a52a2a").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("crostacei", "#00ffff").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("uova", "#ffa500").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("pesce", "#0000ff").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("arachidi", "#d2691e").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("soia", "#32cd32").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("frutta secca", "#ff0000").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("sedano", "#7cfc00").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("senape", "#daa520").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("sesamo", "#d2b48c").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("solfiti", "#c71585").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("molluschi", "#7fffd4").toAllergene());
			allergeneRepository.save(new AllergeneInizialeDto("lupini", "#ffff00").toAllergene());
    	}
    }
    
}
