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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.udemm.springboot.services.data.Port;
import ar.edu.udemm.springboot.services.data.PortService;
import ar.edu.udemm.springboot.services.serialComm.CommService;
import jssc.SerialPort;

/**
 * 
 * @author gsuenaga
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({ "/ports" })
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
		String[] ports = commService.getAllPorts();
		// primero debo ver si no esta ya en la BD
		List<Port> portsList = portService.findAll();
		if (portsList != null && portsList.size() > 0 && ports.length == portsList.size()) {

			return portsList;

		} else {
			portService.deleteAll();
			portsList.clear();

			for (int i = 0; i < ports.length; i++) {
				Port port = new Port(ports[i], SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						commService.getParity(SerialPort.PARITY_NONE));
				portService.create(port);
				portsList.add(port);
			}
		}

		return portsList;
	}

	@PostMapping
	public Port create(@RequestBody Port port) {
		return portService.create(port);
	}
	
	@PostMapping("/connect")
	public String connect(@RequestBody Port port) {
		return commService.connect(port);
	}
	
}
