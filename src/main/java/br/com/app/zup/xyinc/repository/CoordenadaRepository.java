package br.com.app.zup.xyinc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.zup.xyinc.pojo.Coordenada;

@Repository
public interface CoordenadaRepository extends JpaRepository<Coordenada, Long> {

	
}
