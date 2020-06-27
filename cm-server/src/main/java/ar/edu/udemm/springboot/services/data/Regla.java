package ar.edu.udemm.springboot.services.data;

import javax.persistence.*;

@Entity
@Table(name = "regla")
public class Regla {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Double distancia;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Regla(int id, double d) {
		super();
		this.id = id;
		this.distancia = d;
	}

	public Regla() {
		super();
		// TODO Auto-generated constructor stub
	}

}
