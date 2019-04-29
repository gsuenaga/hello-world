/**
 * 
 */
package ar.edu.udemm.springboot.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@Autowired
	private CommService commService;

	@Autowired
	private PortService portService;
	
	@GetMapping
	public String home() {
		return "forward:/index.html";
	}

	@GetMapping("/portList")
	public String[] getCommList() {
		//primero debo ver si no esta ya en la BD
		
		String[] ports = commService.getAllPorts();
		System.out.println(Arrays.toString(ports));
		return commService.getAllPorts();
	}
}
