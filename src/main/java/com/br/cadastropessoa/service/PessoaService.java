package com.br.cadastropessoa.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.br.cadastropessoa.DAO.PessoaDAO;
import com.br.cadastropessoa.model.Pessoa;

@RequestScoped
public class PessoaService {
	
	@Inject
	private PessoaDAO pessoaDAO;
	
	
	@Transactional
	public void salvar(Pessoa pessoa) {
		if(pessoa != null) 
            pessoaDAO.salvar(pessoa);
	}

    public Pessoa buscarPorId(Long id) {
    	if(id != null) 
            return pessoaDAO.buscarPorId(id);
    	
        return null;
    }

    public List<Pessoa> buscarTodos() {
        return pessoaDAO.buscarTodos();
    }

    public List<Pessoa> buscarPorNome(String nome) {
    	if (nome != null && !nome.trim().isEmpty()) 
            return pessoaDAO.buscarPorNome(nome);
        
        return List.of();
    }

    @Transactional
    public void atualizar(Pessoa pessoa) {
    	if (pessoa != null && pessoa.getId() != null) 
            pessoaDAO.atualizar(pessoa);
    }

    @Transactional
    public void deletar(Long id) {
    	if(id != null)
    		pessoaDAO.deletar(id);
    }
}
