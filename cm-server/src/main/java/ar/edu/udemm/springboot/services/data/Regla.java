package ar.edu.udemm.springboot.services.data;

import javax.persistence.*;

@Entity
@Table(name = "regla")
public class Regla {

    @Id
    @Column
    private int id;
    @Column
    private int distancia;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Regla(int id, int distancia) {
		super();
		this.id = id;
		this.distancia = distancia;
	}

	public Regla() {
		super();
		// TODO Auto-generated constructor stub
	}

}
