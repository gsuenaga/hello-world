package ar.edu.udemm.springboot.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class WebController {


	@MessageMapping("/hello")
	@SendTo("/topic/hi")
	public String greeting(String x) throws Exception {
		System.out.println("paso por aca");
		return "Conectadoooooo";
	}
	

}
