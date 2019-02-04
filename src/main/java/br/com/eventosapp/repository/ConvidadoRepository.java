package br.com.eventosapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.eventosapp.models.Convidado;
import br.com.eventosapp.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByCpf(long cpf);
}
