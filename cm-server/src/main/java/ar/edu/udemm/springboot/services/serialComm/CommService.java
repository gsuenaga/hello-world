package ar.edu.udemm.springboot.services.serialComm;

import java.util.List;

import ar.edu.udemm.springboot.services.data.Port;
import jssc.SerialPort;

/**
 * @author gsuenaga
 *
 */
public interface CommService {

	String[] getAllPorts();

	String getParity(int parity);

	String connect(Port port);

	String disconnect(Port port);

	String getEstado();

	SerialPort getSerialPort();

//	List<String> getMediciones();
	List<Medicion> getMediciones();

	void addMediciones(List<String> x);

//	void addMediciones(Medicion mediciones);

//	List<Medicion> getMediciones2();

}
