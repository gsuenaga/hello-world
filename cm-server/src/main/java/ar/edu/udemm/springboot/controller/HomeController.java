/**
 * 
 */
package ar.edu.udemm.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.udemm.springboot.services.data.Port;
import ar.edu.udemm.springboot.services.data.PortService;
import ar.edu.udemm.springboot.services.serialComm.CommService;

/**
 * 
 * @author gsuenaga
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({ "/ports" })
public class HomeController {

//	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private CommService commService;

	@Autowired
	private PortService portService;

	@GetMapping
	public String home() {
		return "forward:/index.html";
	}

	@GetMapping("/portList")
	public List<Port> getCommList() {
		return portService.findAll();
	}

	@PostMapping
	public Port create(@RequestBody Port port) {
		return portService.create(port);
	}
	
	@PostMapping("/connect")
	public String connect(@RequestBody Port port) {
		return commService.connect(port);
	}
	
	@PostMapping("/disconnect")
	public String disconnect(@RequestBody Port port) {
		return commService.disconnect(port);
	}

}
