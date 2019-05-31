package ar.edu.udemm.springboot.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReglaServiceImpl implements ReglaService {

	@Autowired
	private ReglaRepository repository;

	@Override
	public Regla create(Regla regla) {
		return repository.save(regla);
	}

	@Override
	public Regla delete(int id) {
		Regla regla = findById(id);
		if (regla != null) {
			repository.delete(regla);
		}
		return regla;
	}

	@Override
	public List<Regla> findAll() {
		return repository.findAll();
	}

	@Override
	public Regla findById(int id) {
		return repository.findById(id);
	}

	@Override
	public Regla update(Regla regla) {
		return repository.save(regla);
	}

	@Override
	public boolean deleteAll() {
		List<Regla> reglas = findAll();

		reglas.forEach(regla -> {
			delete(regla.getId());
		});

		return true;
	}
}
