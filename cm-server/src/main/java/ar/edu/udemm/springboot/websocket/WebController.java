package ar.edu.udemm.springboot.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import ar.edu.udemm.springboot.services.data.Port;
import ar.edu.udemm.springboot.services.serialComm.CommService;

@Controller
public class WebController {

	@Autowired
	private CommService commService;

//	@MessageMapping("/hello")
//	@SendTo("/topic/hi")
//	public Hello greeting(User user) throws Exception {
//		return new Hello("Hi, " + user.getName() + "!");
//	}
//	
	@MessageMapping("/hello")
	@SendTo("/topic/hi")
	public String greeting(String x) throws Exception {
		System.out.println("paso por aca");
		return "Conectadoooooo";
//		return commService.connect(port);
	}
	

}
