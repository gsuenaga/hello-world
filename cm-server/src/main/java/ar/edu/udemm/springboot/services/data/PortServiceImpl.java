package ar.edu.udemm.springboot.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortServiceImpl implements PortService {

    @Autowired
    private PortRepository repository;

    @Override
    public Port create(Port port) {
        return repository.save(port);
    }

    @Override
    public Port delete(int id) {
        Port port = findById(id);
        if(port != null){
            repository.delete(port);
        }
        return port;
    }

    @Override
    public List<Port> findAll() {
        return repository.findAll();
    }

    @Override
    public Port findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Port update(Port port) {
        return repository.save(port);
    }
}
