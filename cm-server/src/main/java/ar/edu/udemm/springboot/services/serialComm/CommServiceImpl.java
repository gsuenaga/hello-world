package ar.edu.udemm.springboot.services.serialComm;

import org.springframework.stereotype.Service;

import jssc.SerialPortList;

/**
 * @author gsuenaga
 *
 */
@Service
public class CommServiceImpl implements CommService{

	@Override
	public String[] getAllPorts() {
		return SerialPortList.getPortNames();
	}

}
