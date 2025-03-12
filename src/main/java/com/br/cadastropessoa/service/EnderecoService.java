package com.br.cadastropessoa.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.br.cadastropessoa.DAO.EnderecoDAO;
import com.br.cadastropessoa.model.Endereco;


@RequestScoped
public class EnderecoService {

	@Inject
    private EnderecoDAO enderecoDAO;

    @Transactional
    public void salvar(Endereco endereco) {
        if (endereco != null) 
            enderecoDAO.salvar(endereco);
    }

    @Transactional
    public void deletar(Long id) {
        if (id != null) 
            enderecoDAO.deletar(id);
    }
}