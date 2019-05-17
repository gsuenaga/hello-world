package ar.edu.udemm.springboot.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import ar.edu.udemm.springboot.services.data.Port;

@Controller
public class WebController {

//	@MessageMapping("/hello")
//	@SendTo("/topic/hi")
//	public Hello greeting(User user) throws Exception {
//		return new Hello("Hi, " + user.getName() + "!");
//	}
//	
	@MessageMapping("/hello")
	@SendTo("/topic/hi")
	public String greeting(Port port) throws Exception {
		return "Conectado";
	}
	
}
