/**
 * 
 */
package ar.edu.udemm.springboot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.udemm.springboot.services.data.Port;
import ar.edu.udemm.springboot.services.data.PortService;
import ar.edu.udemm.springboot.services.data.Regla;
import ar.edu.udemm.springboot.services.data.ReglaService;
import ar.edu.udemm.springboot.services.serialComm.CommService;

/**
 * 
 * @author gsuenaga
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({ "/regla" })
public class ReglaController {

//	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ReglaService reglaService;

	@GetMapping
	public String home() {
		return "forward:/index.html";
	}

	@GetMapping("/reglaList")
	public List<Regla> getReglaList() {
		return reglaService.findAll();
	}
	
	@PostMapping
	public boolean update(@RequestBody List<Regla> regla) {
		return reglaService.update(regla);
	}
//	@GetMapping("/portList")
//	public List<Port> getCommList() {
//		return portService.findAll();
//	}
//
//	@PostMapping
//	public Port create(@RequestBody Port port) {
//		return portService.create(port);
//	}
//	
//	@PostMapping("/connect")
//	public String connect(@RequestBody Port port) {
//		return commService.connect(port);
//	}
//	
//	@PostMapping("/disconnect")
//	public String disconnect(@RequestBody Port port) {
//		return commService.disconnect(port);
//	}
	
}
