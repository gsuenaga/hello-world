package ar.edu.udemm.springboot.services.serialComm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class SerialPortReader implements SerialPortEventListener {
	
	private final Logger logger = LoggerFactory.getLogger(SerialPortReader.class);
			
	private CommService commService;
	
	public SerialPortReader(SerialPort serialPort, CommService commService) {
		super();
		this.serialPort = serialPort;
		this.commService = commService;
	}

	private SerialPort serialPort;

	public void serialEvent(SerialPortEvent event) {
		if (event.isRXCHAR()) {// If data is available
			int amount = event.getEventValue();
			String buffer;
			try {
				buffer = serialPort.readString();// Bytes(amount);
				if (amount > 0) {
					this.commService.getMediciones().add(getFormattedValue(buffer));
					logger.info(getFormattedValue(buffer));
				}
			} catch (SerialPortException e) {
				logger.error("Fallo lectura port", e);
			}
			/*
			 * if (event.getEventValue() == 10) {// Check bytes count in the input buffer //
			 * Read data, if 10 bytes available try { byte buffer[] =
			 * serialPort.readBytes(10); System.out.println(buffer); } catch
			 * (SerialPortException ex) { System.out.println(ex); } }
			 */
		} else if (event.isCTS()) {// If CTS line has changed state
			if (event.getEventValue() == 1) {// If line is ON
				logger.info("CTS - ON");
			} else {
				logger.info("CTS - OFF");
			}
		} else if (event.isDSR()) {/// If DSR line has changed state
			if (event.getEventValue() == 1) {// If line is ON
				logger.info("DSR - ON");
			} else {
				logger.info("DSR - OFF");
			}
		}
	}

	private String getFormattedValue(String buffer) {
		return buffer.replaceAll("F", "").replaceAll("T", "").replaceAll("\\n", "");
	}
	

}