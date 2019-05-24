package br.com.app.zup.xyinc.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;

import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.app.zup.xyinc.pojo.Coordenada;
import br.com.app.zup.xyinc.service.CoordenadaService;

/**
 * @author Guilherme Parreira
 */
@Controller
@RequestMapping(value = "/coordenadas")
public class Coordenadas {
	
	@Autowired
	private CoordenadaService esrvice;
	
	private Pattern caracterPattern = Pattern.compile("[a-zA-Z]");
	
	
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
		
		validarParametros(latitudeParam, longitudeParam);
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


		validarParametros(latitudeParam, longitudeParam, longitudeParam);
		final Integer latitude = Integer.parseInt(latitudeParam);
		final Integer longitude = Integer.parseInt(longitudeParam);
		final Integer distancia = Integer.parseInt(distanciaParam);
		
		
		List<Coordenada> todos = esrvice.buscarTodos();
		
		todos.removeIf(coordenada -> Math.abs(coordenada.getLatitude()-latitude) > distancia 
				|| Math.abs(coordenada.getLongitude()-longitude) > distancia);
		
		modelAndView.addObject("coordenadas", todos);
		
		return modelAndView;
    }
	
	
	
	
	@GetMapping(value = "/service/cadastrarCoordenada/{nome}/{latitudeParam}/{longitudeParam}", 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Coordenada cadastrarCoordenada(@PathVariable("nome") String nome,
			@PathVariable("latitudeParam") String latitudeParam,
			@PathVariable("longitudeParam") String longitudeParam, HttpServletResponse response) {
		
		
		validarParametros(latitudeParam, longitudeParam);
		int latitude = Integer.parseInt(latitudeParam);
		int longitude = Integer.parseInt(longitudeParam);
		Coordenada c = new Coordenada(nome, latitude,longitude);
		
		return esrvice.cadastrarCoordenada(c);
    }
	
	
	
	
	@GetMapping(value = "/service/listar",
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Coordenada> listarCoordenadas(HttpServletResponse response) {
		
		return esrvice.buscarTodos();
    }
	
	
	@GetMapping(value = "/service/filtrarPOIs/{latitudeParam}/{longitudeParam}/{distanciaMaxParam}", 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Coordenada> filtrarPOIs(			
			@PathVariable("latitudeParam") String latitudeParam,
			@PathVariable("longitudeParam") String longitudeParam, 
			@PathVariable("distanciaMaxParam") String distanciaMaxParam, HttpServletResponse response) {
		
		
		this.validarParametros(latitudeParam, longitudeParam, distanciaMaxParam);
		
		final Integer longitude = Integer.parseInt(longitudeParam);
		final Integer latitude = Integer.parseInt(latitudeParam);
		final Integer distanciaMax = Integer.parseInt(distanciaMaxParam);
		
		
		List<Coordenada> todos = esrvice.buscarTodos();
		
		
		
		todos.removeIf(coordenada -> Math.abs(coordenada.getLatitude()-latitude) > distanciaMax 
				|| Math.abs(coordenada.getLongitude()-longitude) > distanciaMax); 
		
		return todos;
    }

	/**
	 * @param latitudeParam
	 * @param longitudeParam
	 * @param distanciaMaxParam
	 */
	private void validarParametros(String latitudeParam, String longitudeParam, String distanciaMaxParam) {
		this.validarParametros(latitudeParam, longitudeParam);
		
		if(StringHelper.isEmpty(distanciaMaxParam)) {
			throw new ValidationException("Distancia não informada");
		}
		
		if(caracterPattern.matcher(distanciaMaxParam).find()) {
			throw new ValidationException("Somente numeros deve ser informados para a Distancia");
		}
		if((Integer.parseInt(distanciaMaxParam)) <= 0) {
			throw new ValidationException("Distancia deve ser um valor inteiro maior que zero");
		}		
	}

	/**
	 * @param latitudeParam
	 * @param longitudeParam
	 */
	private void validarParametros(String latitudeParam, String longitudeParam) {
		if(StringHelper.isEmpty(latitudeParam)) {
			throw new ValidationException("Latitude não informada");
		}
		if(caracterPattern.matcher(latitudeParam).find()) {
			throw new ValidationException("Somente numeros deve ser informados para a Latitude");
		}
		if((Integer.parseInt(latitudeParam)) <= 0) {
			throw new ValidationException("Latitude deve ser um valor inteiro maior que zero");
		}
		
		if(StringHelper.isEmpty(longitudeParam)) {
			throw new ValidationException("Longitude não informada");
		}
		if(caracterPattern.matcher(longitudeParam).find()) {
			throw new ValidationException("Somente numeros deve ser informados para a Longitude");
		}
		if((Integer.parseInt(longitudeParam)) <= 0) {
			throw new ValidationException("Longitude deve ser um valor inteiro maior que zero");
		}
	}

	
	
}
