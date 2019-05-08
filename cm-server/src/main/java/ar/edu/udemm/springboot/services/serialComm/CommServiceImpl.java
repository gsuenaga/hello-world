package ar.edu.udemm.springboot.services.serialComm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ar.edu.udemm.springboot.services.data.Port;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * @author gsuenaga
 *
 */
@Service
public class CommServiceImpl implements CommService {

	SerialPort serialPort;
	
	private final Logger logger = LoggerFactory.getLogger(CommService.class);

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

	@Override
	public String connect(Port port) {
		String res = "Desconectado";
		try {
			this.serialPort = new SerialPort(port.getPort());
			if (this.serialPort.openPort() && this.serialPort.setParams(port.getBaudrate(), port.getDatabits(),
					port.getStopbits(), getParityAsNumber(port.getParitybits()))) {
				res = "Conectado";
			}
		} catch (SerialPortException ex) {
			logger.error(ex.getMessage());
		}
		logger.info(res);
		return res;
	}

	private int getParityAsNumber(String paritybits) {
		if ("EVEN".equals(paritybits)) {
			return SerialPort.PARITY_EVEN;
		} else if ("MARK".equals(paritybits)) {
			return SerialPort.PARITY_MARK;
		} else if ("NONE".equals(paritybits)) {
			return SerialPort.PARITY_NONE;
		} else if ("MARK".equals(paritybits)) {
			return SerialPort.PARITY_MARK;
		} else if ("ODD".equals(paritybits)) {
			return SerialPort.PARITY_ODD;
		} else if ("SPACE".equals(paritybits)) {
			return SerialPort.PARITY_SPACE;
		}

		return 0;
	}

}
