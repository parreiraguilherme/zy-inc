package br.com.app.zup.xyinc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.app.zup.xyinc.pojo.Coordenada;
import br.com.app.zup.xyinc.service.CoordenadaService;

/**
 * @author Guilherme Parreira
 */
@Controller
@RequestMapping(value = "/coordenadas")
public class Coordenadas extends CoordenadasValidations{
	
	@Autowired
	private CoordenadaService esrvice;
	
	@GetMapping(value = {"/","/listar"})
	public ModelAndView listarCoordenadas(HttpServletRequest request, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView("listagem");
		modelAndView.addObject("coordenadas", esrvice.buscarTodos());
		return modelAndView;
    }

	@GetMapping(value = {"/nova"})
	public ModelAndView prepararNovaCoordenada(HttpServletRequest request, HttpSession session) throws Exception {
		//abre a tela de cadastro da nova
		
		ModelAndView modelAndView = new ModelAndView("nova_coordenada");
		return modelAndView;
	}
	
	
	@PostMapping(value="/removerCoordenada")
	public ModelAndView removerCoordenada(HttpServletRequest request, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView("listagem");
		esrvice.remover(Long.parseLong(request.getParameter("idCoordenada")));
		modelAndView.addObject("coordenadas", esrvice.buscarTodos());
		
		return modelAndView;
    }
	
	
	@PostMapping(value="/cadastrarCoordenada")
	public ModelAndView cadastrarCoordenada(HttpServletRequest request, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView("listagem");
		String nome = request.getParameter("nome");
		String latitudeParam = request.getParameter("latitude");
		String longitudeParam = request.getParameter("longitude");
		
		super.validarParametros(latitudeParam, longitudeParam);
		int latitude = Integer.parseInt(latitudeParam);
		int longitude = Integer.parseInt(longitudeParam);
		Coordenada c = new Coordenada(nome, latitude,longitude);
		esrvice.cadastrarCoordenada(c);
		
		
		modelAndView.addObject("coordenadas", esrvice.buscarTodos());
		
		return modelAndView;
    }
	
	
	
	@PostMapping(value="/filtrarPOI")
	public ModelAndView filtrarPOI(HttpServletRequest request, HttpSession session) {
		
		ModelAndView modelAndView = new ModelAndView("listagem");
		
		String distanciaParam = request.getParameter("distanciaPOI");
		String latitudeParam = request.getParameter("filtroLatitude");
		String longitudeParam = request.getParameter("filtroLongitude");


		super.validarParametros(latitudeParam, longitudeParam, longitudeParam);
		final Integer latitude = Integer.parseInt(latitudeParam);
		final Integer longitude = Integer.parseInt(longitudeParam);
		final Integer distancia = Integer.parseInt(distanciaParam);
		
		
		List<Coordenada> todos = esrvice.buscarTodos();
		
		todos.removeIf(coordenada -> Math.abs(coordenada.getLatitude()-latitude) > distancia 
				|| Math.abs(coordenada.getLongitude()-longitude) > distancia);
		
		modelAndView.addObject("coordenadas", todos);
		
		return modelAndView;
    }
	
	
}
