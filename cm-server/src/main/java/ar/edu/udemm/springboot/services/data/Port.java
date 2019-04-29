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
    private String baudrate;
    @Column
    private String databits;
    @Column
    private String stopbits;
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

	public String getBaudrate() {
		return baudrate;
	}

	public void setBaudrate(String baudrate) {
		this.baudrate = baudrate;
	}

	public String getDatabits() {
		return databits;
	}

	public void setDatabits(String databits) {
		this.databits = databits;
	}

	public String getStopbits() {
		return stopbits;
	}

	public void setStopbits(String stopbits) {
		this.stopbits = stopbits;
	}

	public String getParitybits() {
		return paritybits;
	}

	public void setParitybits(String paritybits) {
		this.paritybits = paritybits;
	}

}
