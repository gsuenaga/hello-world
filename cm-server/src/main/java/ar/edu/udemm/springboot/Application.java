/**
 * 
 */
package ar.edu.udemm.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author gsuenaga
 *
 */
//@SpringBootApplication
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
        )
public class Application extends SpringBootServletInitializer {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
