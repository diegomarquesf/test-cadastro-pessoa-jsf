package com.br.cadastropessoa.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.br.cadastropessoa.model.Pessoa;
import com.br.cadastropessoa.service.PessoaService;

@Named
@ViewScoped
public class PessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
    private PessoaService pessoaService;

    private String nomePesquisa;
    private List<Pessoa> pessoas;
    
    @PostConstruct
    public void init() {
    	pessoas = new ArrayList<Pessoa>();
    	buscar();
    }
    
    public void buscar() {
    	if(nomePesquisa == null || nomePesquisa.trim().isEmpty()) 
    		pessoas = pessoaService.buscarTodos();
    	else
    		pessoas = pessoaService.buscarPorNome(nomePesquisa);
    }
    
    public void remover(Long id) {
        pessoaService.deletar(id);
        buscar();
        
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pessoa removida com sucesso!"));
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
