package ar.edu.udemm.springboot;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import ar.edu.udemm.springboot.services.data.Port;
import ar.edu.udemm.springboot.services.data.PortService;
import ar.edu.udemm.springboot.services.data.Regla;
import ar.edu.udemm.springboot.services.data.ReglaService;
import ar.edu.udemm.springboot.services.serialComm.CommService;
import jssc.SerialPort;

@Component
class StartupHousekeeper {

	private final Logger logger = LoggerFactory.getLogger(StartupHousekeeper.class);

	@Autowired
	private ReglaService reglaService;

	@Autowired
	private CommService commService;

	@Autowired
	private PortService portService;

	@Value("${app.regla.cantidad.ranuras}")
	private String cantRanuras;


	@EventListener(ContextRefreshedEvent.class)
	void contextRefreshedEvent() {
		configureRegla();
		configurePorts();
	}

	private void configurePorts() {
		String[] ports = commService.getAllPorts();
		List<Port> portsList = portService.findAll();
		if(portsList == null || portsList.size() == 0 || portsList.size() != ports.length) {
			inserteDefaultValuesPorts(ports);
			logger.info("Se ingresan los ports con valores por defecto.");
		}else {
			int cantIgual = 0;
			for(int i=0; i<ports.length; i++) {
				for(int j=0; j<portsList.size(); j++) {
					if (ports[i].equals(portsList.get(j).getPort())) {
						cantIgual++;;
					}
				}
			}
			if(cantIgual != ports.length) {
				portService.deleteAll();
				inserteDefaultValuesPorts(ports);
				logger.info("Se borran los datos y se vuelve a ingresar los ports con valores por defecto.");
			}else {
				logger.info("Se recuperan los datos de los ports desde la BD.");
			}
		}
	}

	private void inserteDefaultValuesPorts(String[] ports) {
		
		if (portService.deleteAll()) {
			logger.info("Se eliminan datos de configuracion de ports.");
		}

		for (int i = 0; i < ports.length; i++) {
			Port port = new Port(ports[i], SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					commService.getParity(SerialPort.PARITY_NONE));
			portService.create(port);
		}
	}

	private void configureRegla() {
		if (Integer.parseInt(cantRanuras) != reglaService.findAll().size()) {

			if (reglaService.deleteAll()) {
				logger.info("Se eliminan datos de configuracion de la regla.");
			}

			insertDefaultValuesRegla();
		}
	}

	private void insertDefaultValuesRegla() {
		reglaService.create(new Regla(1, 44.86));
		reglaService.create(new Regla(2, 44.84));
		reglaService.create(new Regla(3, 44.94));
		reglaService.create(new Regla(4, 45.10));
		reglaService.create(new Regla(5, 45.12));
	}
}