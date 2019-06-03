package ar.edu.udemm.springboot.services.serialComm;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class SerialPortReader implements SerialPortEventListener {
	public SerialPortReader(SerialPort serialPort) {
		super();
		this.serialPort = serialPort;
	}

	private SerialPort serialPort;

	public void serialEvent(SerialPortEvent event) {
		if (event.isRXCHAR()) {// If data is available
			if (event.getEventValue() == 10) {// Check bytes count in the input buffer
				// Read data, if 10 bytes available
				try {
					byte buffer[] = serialPort.readBytes(10);
					System.out.println(buffer);
				} catch (SerialPortException ex) {
					System.out.println(ex);
				}
			}
		} else if (event.isCTS()) {// If CTS line has changed state
			if (event.getEventValue() == 1) {// If line is ON
				System.out.println("CTS - ON");
			} else {
				System.out.println("CTS - OFF");
			}
		} else if (event.isDSR()) {/// If DSR line has changed state
			if (event.getEventValue() == 1) {// If line is ON
				System.out.println("DSR - ON");
			} else {
				System.out.println("DSR - OFF");
			}
		}
	}
}