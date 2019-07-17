package ar.edu.udemm.springboot.services.serialComm;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ar.edu.udemm.springboot.services.data.Port;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import ar.edu.udemm.springboot.services.serialComm.SerialPortReader;

@Service
public class CommServiceImpl implements CommService {

	private SerialPort serialPort;

	private String estado = "Desconectado";

	private List<String> mediciones = new ArrayList<String>();

	private final Object lock = new Object();

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
		try {
			this.serialPort = new SerialPort(port.getPort());
			if (this.serialPort.openPort() && this.serialPort.setParams(port.getBaudrate(), port.getDatabits(),
					port.getStopbits(), getParityAsNumber(port.getParitybits()))) {
				int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;// Prepare mask
				serialPort.setEventsMask(mask);// Set mask
				serialPort.addEventListener(new SerialPortReader(serialPort, this));// Add SerialPortEventListener
				this.estado = "Conectado";
			}
		} catch (SerialPortException ex) {
			logger.error(ex.getMessage());
		}
		logger.info("estado :" + this.estado);
		return this.estado;
	}

	@Override
	public String disconnect(Port port) {
		try {
			this.serialPort = new SerialPort(port.getPort());
			if (this.serialPort.closePort()) {
				this.estado = "Desconectado";
			} else {
				logger.warn("falló desconexión");
			}
		} catch (SerialPortException ex) {
			logger.error(ex.getMessage());
		}
		logger.info("estado :" + this.estado);
		return this.estado;
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

	@Override
	public String getEstado() {
		return estado;
	}

	@Override
	public SerialPort getSerialPort() {
		return serialPort;
	}

	@Override
	public void addMediciones(List<String> mediciones) {
		synchronized (lock) {
			this.mediciones.addAll(mediciones);
		}
	}

	@Override
	public List<String> getMediciones() {
		if (this.mediciones.size() > 0) {
			synchronized (lock) {
				List<String> temp = new ArrayList<String>(this.mediciones);
				this.mediciones.clear();
				return temp;
			}
		}
		return null;
	}
}
