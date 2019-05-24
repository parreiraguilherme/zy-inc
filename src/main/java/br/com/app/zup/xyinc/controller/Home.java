package br.com.app.zup.xyinc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Home {
	
	
	@GetMapping(value = {"/","/home","dashboard"})
	public String home(HttpServletRequest request, HttpSession session) throws Throwable {
		//abre a tela de cadastro da nova
		
		return "redirect:/coordenadas/";
    }

}
