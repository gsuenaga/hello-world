package ar.edu.udemm.springboot.services.serialComm;

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

	String getEstado();

	SerialPort getSerialPort();
}
