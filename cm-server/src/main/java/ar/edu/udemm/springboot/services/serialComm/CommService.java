package ar.edu.udemm.springboot.services.serialComm;

import ar.edu.udemm.springboot.services.data.Port;

/**
 * @author gsuenaga
 *
 */
public interface CommService {

	String[] getAllPorts();

	String getParity(int parity);

	String connect(Port port);
}
