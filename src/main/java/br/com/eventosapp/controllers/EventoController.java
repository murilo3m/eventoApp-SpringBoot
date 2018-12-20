package br.com.eventosapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.eventosapp.models.Evento;
import br.com.eventosapp.repository.EventoRepository;

@Controller 
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@RequestMapping(value="/cadastrar-evento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrar-evento", method=RequestMethod.POST)
	public String form(Evento evento) {
		
		eventoRepository.save(evento);
		
		return "redirect:/cadastrar-evento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = eventoRepository.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
}
