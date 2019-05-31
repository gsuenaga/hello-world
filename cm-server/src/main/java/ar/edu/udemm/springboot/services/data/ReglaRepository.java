package ar.edu.udemm.springboot.services.data;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface ReglaRepository extends Repository<Regla, Integer> {

    void delete(Regla regla);

    List<Regla> findAll();

    Regla findById(int id);

    Regla save(Regla regla);
}
