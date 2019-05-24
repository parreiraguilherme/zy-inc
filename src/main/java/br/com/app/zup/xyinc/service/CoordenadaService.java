package br.com.app.zup.xyinc.service;

import java.util.List;

import br.com.app.zup.xyinc.pojo.Coordenada;

public interface CoordenadaService {

	List<Coordenada> buscarTodos();

	void remover(Long idCoordenada);

	Coordenada cadastrarCoordenada(Coordenada c);

	
	
}
