package br.com.app.zup.xyinc.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.app.zup.xyinc.pojo.Coordenada;
import br.com.app.zup.xyinc.service.CoordenadaService;

@Controller
@RequestMapping(value = "/v1/coordenadas")
public class HttpServiceRequest extends CoordenadasValidations{

	
	@Autowired
	private CoordenadaService esrvice;
	
	
	

	@GetMapping(value = "/cadastrarCoordenada/{nome}/{latitudeParam}/{longitudeParam}", 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Coordenada cadastrarCoordenada(@PathVariable("nome") String nome,
			@PathVariable("latitudeParam") String latitudeParam,
			@PathVariable("longitudeParam") String longitudeParam, HttpServletResponse response) {
		
		
		super.validarParametros(latitudeParam, longitudeParam);
		int latitude = Integer.parseInt(latitudeParam);
		int longitude = Integer.parseInt(longitudeParam);
		Coordenada c = new Coordenada(nome, latitude,longitude);
		
		return esrvice.cadastrarCoordenada(c);
    }
	
	
	
	
	@GetMapping(value = "/listar",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Coordenada> listarCoordenadas(HttpServletResponse response) {
		
		return esrvice.buscarTodos();
    }
	
	
	@GetMapping(value = "/filtrarPOIs/{latitudeParam}/{longitudeParam}/{distanciaMaxParam}", 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Coordenada> filtrarPOIs(			
			@PathVariable("latitudeParam") String latitudeParam,
			@PathVariable("longitudeParam") String longitudeParam, 
			@PathVariable("distanciaMaxParam") String distanciaMaxParam, HttpServletResponse response) {
		
		
		super.validarParametros(latitudeParam, longitudeParam, distanciaMaxParam);
		
		final Integer longitude = Integer.parseInt(longitudeParam);
		final Integer latitude = Integer.parseInt(latitudeParam);
		final Integer distanciaMax = Integer.parseInt(distanciaMaxParam);
		
		
		List<Coordenada> todos = esrvice.buscarTodos();
		
		
		
		todos.removeIf(coordenada -> Math.abs(coordenada.getLatitude()-latitude) > distanciaMax 
				|| Math.abs(coordenada.getLongitude()-longitude) > distanciaMax); 
		
		return todos;
    }

}
