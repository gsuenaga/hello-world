package ar.edu.udemm.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final Logger logger = LoggerFactory.getLogger(SchedulerController.class);
	
	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private CommService commService;

	@Value("${app.scheduler.tiempo.refresco.milisegundos}")
	private final long cicloDemora = 1000;

	@Scheduled(fixedRate = cicloDemora)
	public void greeting() throws InterruptedException {
		if ("Conectado".equals(commService.getEstado())) {
			List<String> resultado = commService.getMediciones();

			if(resultado != null) {
				this.template.convertAndSend("/topic/hi", resultado);
				logger.info("Enviado : " + resultado);
			}
		}
	}
}