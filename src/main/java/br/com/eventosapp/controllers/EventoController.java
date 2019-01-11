package br.com.eventosapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.eventosapp.models.Convidado;
import br.com.eventosapp.models.Evento;
import br.com.eventosapp.repository.ConvidadoRepository;
import br.com.eventosapp.repository.EventoRepository;

@Controller 
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ConvidadoRepository convidadoRepository;
	
	@RequestMapping(value="/cadastrar-evento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrar-evento", method=RequestMethod.POST)
	public String form(Evento evento) {
		
		eventoRepository.save(evento);
		
		return "redirect:/cadastrar-evento";
	}
	
	@RequestMapping("/evento")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = eventoRepository.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@RequestMapping(value="/evento/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = eventoRepository.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		return mv;
	}
	
	@RequestMapping(value="/evento/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado) {
		Evento evento = eventoRepository.findByCodigo(codigo);
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);
		return "redirect:/evento/{codigo}";
	}
}
