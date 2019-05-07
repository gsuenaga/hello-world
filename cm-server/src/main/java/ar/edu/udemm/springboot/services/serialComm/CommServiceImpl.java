package ar.edu.udemm.springboot.services.serialComm;

import org.springframework.stereotype.Service;

import jssc.SerialPort;
import jssc.SerialPortList;

/**
 * @author gsuenaga
 *
 */
@Service
public class CommServiceImpl implements CommService {

	@Override
	public String[] getAllPorts() {
		return SerialPortList.getPortNames();
	}

	@Override
	public String getParity(int parity) {
		switch (parity) {
		case SerialPort.PARITY_EVEN:
			return "EVEN";
		case SerialPort.PARITY_MARK:
			return "MARK";
		case SerialPort.PARITY_NONE:
			return "NONE";
		case SerialPort.PARITY_ODD:
			return "ODD";
		case SerialPort.PARITY_SPACE:
			return "SPACE";
		default:
			return null;
		}

	}

}
