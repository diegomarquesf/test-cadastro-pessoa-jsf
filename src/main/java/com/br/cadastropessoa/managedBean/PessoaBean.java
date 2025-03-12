package com.br.cadastropessoa.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.br.cadastropessoa.model.Pessoa;
import com.br.cadastropessoa.service.PessoaService;

@Named
@RequestScoped
public class PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
    private PessoaService pessoaService;

    private String nomePesquisa;
    private List<Pessoa> pessoas;
    
    public void buscar() {
        pessoas = pessoaService.buscarPorNome(nomePesquisa);
    }
    
    public void remover(Long id) {
        pessoaService.deletar(id);
        buscar();
    }
    
    public List<Pessoa> getPessoas() {
        return pessoas;
    }
    
    public String getNomePesquisa() {
        return nomePesquisa;
    }
    
    public void setNomePesquisa(String nomePesquisa) {
        this.nomePesquisa = nomePesquisa;
    }
}
