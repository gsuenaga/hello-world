package ar.edu.udemm.springboot.services.data;

import javax.persistence.*;

@Entity
@Table(name = "port")
public class Port {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String port;
    @Column
    private int baudrate;
    @Column
    private int databits;
    @Column
    private int stopbits;
    @Column
    private String paritybits;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getBaudrate() {
		return baudrate;
	}

	public void setBaudrate(int baudrate) {
		this.baudrate = baudrate;
	}

	public int getDatabits() {
		return databits;
	}

	public void setDatabits(int databits) {
		this.databits = databits;
	}

	public int getStopbits() {
		return stopbits;
	}

	public void setStopbits(int stopbits) {
		this.stopbits = stopbits;
	}

	public String getParitybits() {
		return paritybits;
	}

	public void setParitybits(String paritybits) {
		this.paritybits = paritybits;
	}

	public Port(String port, int baudrate, int databits, int stopbits, String paritybits) {
		super();
		this.port = port;
		this.baudrate = baudrate;
		this.databits = databits;
		this.stopbits = stopbits;
		this.paritybits = paritybits;
	}

	public Port() {
		super();
		// TODO Auto-generated constructor stub
	}

}
