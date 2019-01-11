package br.com.eventosapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.eventosapp.models.Convidado;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	
}
