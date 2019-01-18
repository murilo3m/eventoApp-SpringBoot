package br.com.eventosapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Convidado {
	
	@Id
	@NotNull (message = "Campo não estar vazio")
	private long cpf;
	
	@Size(min=2, max=100, message="Tem de ter pelo menos 2 letras") 
	private String nomeConvidado;
	
	@ManyToOne
	private Evento evento;
	
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getNomeConvidado() {
		return nomeConvidado;
	}
	public void setNomeConvidado(String nomeconvidado) {
		this.nomeConvidado = nomeconvidado;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
