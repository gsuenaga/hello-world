package ar.edu.udemm.springboot.services.data;

import java.util.List;

public interface ReglaService {

	Regla create(Regla regla);

	Regla delete(int id);

	boolean deleteAll();
	
    List<Regla> findAll();

    Regla findById(int id);

    Regla update(Regla regla);
}