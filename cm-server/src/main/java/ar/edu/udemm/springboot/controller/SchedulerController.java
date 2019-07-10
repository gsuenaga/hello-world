package ar.edu.udemm.springboot.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import ar.edu.udemm.springboot.services.serialComm.CommService;

@EnableScheduling
@Controller
public class SchedulerController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private CommService commService;


	@Value("${app.scheduler.tiempo.refresco.milisegundos}")
	private final long cicloDemora = 1000;
	
    @Scheduled(fixedRate = cicloDemora)
    public void greeting() throws InterruptedException {
        if("Conectado".equals(commService.getEstado()) && commService.getMediciones().size()>0) {
//        	commService.getSerialPort().
        	System.out.println("scheduled");
        	List<String> resultado = commService.getMediciones();
        	
        	resultado.removeAll(Arrays.asList("", null));
        	System.out.println("resultado " + resultado);
        	
        	StringBuilder  cleanRes = new StringBuilder();
//        	List<String> cleanRes = new ArrayList<String>();
        	
    		resultado.forEach((temp) -> {
    			System.out.println("sin filtrado " + temp);
    			String x = temp.replaceAll("[^\\w\\s\\p{Punct}]","");

    			System.out.println("filtrado " + x);
    			cleanRes.append(x);
//    			cleanRes.add(x);
    		});
    		
    		System.out.println("limpio " + cleanRes);
        	this.template.convertAndSend("/topic/hi", cleanRes);

	        commService.getMediciones().clear();
//        	this.template.convertAndSend("/topic/hi", "Hello");
        }
    }
}