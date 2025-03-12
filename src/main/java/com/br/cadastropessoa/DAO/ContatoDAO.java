package com.br.cadastropessoa.DAO;

import javax.enterprise.context.ApplicationScoped;

import com.br.cadastropessoa.config.GenericDAO;
import com.br.cadastropessoa.model.Contato;

@ApplicationScoped
public class ContatoDAO extends GenericDAO<Contato>{

	public ContatoDAO() {
		super(Contato.class);
	}
}
