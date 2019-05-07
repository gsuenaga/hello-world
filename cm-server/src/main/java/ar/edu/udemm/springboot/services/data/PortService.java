package ar.edu.udemm.springboot.services.data;

import java.util.List;

public interface PortService {

	Port create(Port port);

	Port delete(int id);

	boolean deleteAll();
	
    List<Port> findAll();

    Port findById(int id);

    Port update(Port user);
}