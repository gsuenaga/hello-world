package ar.edu.udemm.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import ar.edu.udemm.springboot.services.data.ReglaService;

@Component
class StartupHousekeeper {

	@Autowired
	private ReglaService reglaService;
	
    @EventListener(ContextRefreshedEvent.class)
    void contextRefreshedEvent() {
    	reglaService.findAll();
    	//44.86
    	//44.84
    	//44.94
    	//45.10
    	//45.12
    }
}