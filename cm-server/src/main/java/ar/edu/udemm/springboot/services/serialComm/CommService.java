package ar.edu.udemm.springboot.services.serialComm;

/**
 * @author gsuenaga
 *
 */
public interface CommService {

	String[] getAllPorts();

	String getParity(int parity);
}
