/**
 * 
 */
package ar.edu.udemm.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.udemm.springboot.services.serialComm.CommService;

/**
 * @author gsuenaga
 *
 */
@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private CommService commService;

	@GetMapping
	public String home() {
		return "forward:/index.html";
	}

	@GetMapping(path = { "/ports" })
	public String[] getAllPorts() {
		return commService.getAllPorts();
	}
}
