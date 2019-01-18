package br.com.eventosapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String form(Evento evento, BindingResult result, RedirectAttributes attributes) {
		System.out.println("Naaaaaao");
		System.out.println(result.hasErrors());
		if(result.hasErrors()) {
			System.out.println("okok");
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrar-evento"; 
		}
		eventoRepository.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
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
		
		Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
		mv.addObject("convidados", convidados);
		
		return mv;
	}
	
	@RequestMapping(value="/evento/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {	
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/evento/{codigo}";
		}
		Evento evento = eventoRepository.findByCodigo(codigo);
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/evento/{codigo}";
	}
}
