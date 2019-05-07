package ar.edu.udemm.springboot.services.data;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface PortRepository extends Repository<Port, Integer> {

    void delete(Port port);

    List<Port> findAll();

    Port findById(int id);

    Port save(Port port);
}
