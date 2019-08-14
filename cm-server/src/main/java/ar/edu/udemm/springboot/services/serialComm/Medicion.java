package ar.edu.udemm.springboot.services.serialComm;

//@Component
public class Medicion {

	public Medicion(long id, String medicion1, String medicion2, String medicion3, String medicion4, String medicion5) {
		super();
		this.id = id;
		this.medicion1 = medicion1;
		this.medicion2 = medicion2;
		this.medicion3 = medicion3;
		this.medicion4 = medicion4;
		this.medicion5 = medicion5;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMedicion1() {
		return medicion1;
	}

	public void setMedicion1(String medicion1) {
		this.medicion1 = medicion1;
	}

	public String getMedicion2() {
		return medicion2;
	}

	public void setMedicion2(String medicion2) {
		this.medicion2 = medicion2;
	}

	public String getMedicion3() {
		return medicion3;
	}

	public void setMedicion3(String medicion3) {
		this.medicion3 = medicion3;
	}

	public String getMedicion4() {
		return medicion4;
	}

	public void setMedicion4(String medicion4) {
		this.medicion4 = medicion4;
	}

	public String getMedicion5() {
		return medicion5;
	}

	public void setMedicion5(String medicion5) {
		this.medicion5 = medicion5;
	}

	private long id;
	private String medicion1;
	private String medicion2;
	private String medicion3;
	private String medicion4;
	private String medicion5;
}
