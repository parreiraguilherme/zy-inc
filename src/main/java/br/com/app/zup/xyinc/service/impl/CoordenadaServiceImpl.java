package br.com.app.zup.xyinc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.zup.xyinc.pojo.Coordenada;
import br.com.app.zup.xyinc.repository.CoordenadaRepository;
import br.com.app.zup.xyinc.service.CoordenadaService;

@Service
@Transactional
public class CoordenadaServiceImpl implements CoordenadaService{

	
	@Autowired
	private CoordenadaRepository repo;
	
	@Override
	@Query
	public List<Coordenada> buscarTodos() {
		return repo.findAll();
	}

	@Override
	public void remover(Long idCoordenada) {
		repo.deleteById(idCoordenada);
	}

	@Override
	public Coordenada cadastrarCoordenada(Coordenada c) {
		return repo.saveAndFlush(c);
	}

	
}
