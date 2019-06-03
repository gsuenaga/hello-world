package ar.edu.udemm.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    
//    @Scheduled(fixedRate = 1000)
    public void greeting() throws InterruptedException {
        if("Conectado".equals(commService.getEstado())) {
//        	commService.getSerialPort().
        	System.out.println("scheduled");
        	this.template.convertAndSend("/topic/hi", "Hello");
        }
    }
}