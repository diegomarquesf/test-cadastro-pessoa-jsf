package com.br.cadastropessoa.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.br.cadastropessoa.DAO.ContatoDAO;
import com.br.cadastropessoa.model.Contato;

@RequestScoped
public class ContatoService {
	
    @Inject
    private ContatoDAO contatoDAO;

    @Transactional
    public void salvar(Contato contato) {
    	if(contato != null)
    		contatoDAO.salvar(contato);
    }

    @Transactional
    public void deletar(Long id) {
    	if(id != null)
    		contatoDAO.deletar(id);
    }
}