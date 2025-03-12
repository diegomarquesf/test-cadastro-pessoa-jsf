package com.br.cadastropessoa.managedBean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.br.cadastropessoa.model.Contato;
import com.br.cadastropessoa.model.Documento;
import com.br.cadastropessoa.model.Endereco;
import com.br.cadastropessoa.model.Pessoa;
import com.br.cadastropessoa.service.ContatoService;
import com.br.cadastropessoa.service.DocumentoService;
import com.br.cadastropessoa.service.EnderecoService;
import com.br.cadastropessoa.service.PessoaService;

@Named("cadastroPessoaBean")
@RequestScoped
public class CadastroPessoaBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
    private PessoaService pessoaService;
	
	@Inject
    private DocumentoService documentoService;

    @Inject
    private EnderecoService enderecoService;

    @Inject
    private ContatoService contatoService;

    private Pessoa pessoa;
    private Documento documento;
    private Endereco endereco;
    private Contato contato;
    
    @PostConstruct
    public void init() {
    	pessoa = new Pessoa();
    	pessoa.setDocumentos(new ArrayList<Documento>());
        pessoa.setEnderecos(new ArrayList<Endereco>());
        pessoa.setContatos(new ArrayList<Contato>());
        
    	endereco = new Endereco();
    	documento = new Documento();
    	contato = new Contato();
    }

    public void salvar() {
    	if(pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
    		addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "O campo nome é necessário!");
    		return;
    	}
    	
    	pessoaService.salvar(pessoa);
    	addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pessoa cadastrada com sucesso!");
    	
    	init();
    }
    
    public void adicionarDocumento() {
    	if (documento.getTipo() != null && !documento.getTipo().isEmpty() &&
    			documento.getNumero() != null && !documento.getNumero().isEmpty()) {
    		
    		documento.setPessoa(pessoa);
    		pessoa.getDocumentos().add(documento);
    		
    		addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Documento adicionado!");
    		documento = new Documento();
    	} else {
    		addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Preencha todos os campos do documento!");
        }
    }
    
    public void removerDocumento(Documento doc) {
    	pessoa.getDocumentos().remove(doc);
    	
    	if(doc.getId() != null)
    		documentoService.deletar(doc.getId());
    	
    	addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Documento removido!");
    }
    
    public void adicionarEndereco() {
    	if (endereco.getLogradouro() != null && !endereco.getLogradouro().isEmpty() &&
    			endereco.getCidade() != null && !endereco.getCidade().isEmpty()){
    		
    		endereco.setPessoa(pessoa);
    		pessoa.getEnderecos().add(endereco);
    		addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Endereco adicionado!");
    		
    		endereco = new Endereco();
    	} else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Preencha os campos obrigatórios!");
        }
    }
    
    public void removerEndereco(Endereco end) {
    	pessoa.getEnderecos().remove(end);
    	
    	if(end.getId() != null)
    		enderecoService.deletar(end.getId());
    	
    	addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Endereço removido!");
    }
    
    public void adicionarContato() {
    	if (contato.getTipo() != null && !contato.getTipo().isEmpty() &&
    			contato.getNumero() != null && !contato.getNumero().isEmpty()){
    		
    		contato.setPessoa(pessoa);
    		pessoa.getContatos().add(contato);
    		
    		addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Contato adicionado!");
    	} else {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Preencha os campos obrigatórios!");
        }
    }
    
    public void removerContato(Contato cont) {
    	pessoa.getContatos().remove(cont);
    	
    	if(cont.getId() != null)
    		contatoService.deletar(cont.getId());
    	
    	addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Contato removido!");
    }
    
	public String irParaPesquisa() {
        return "pesquisa-pessoa?faces-redirect=true";
    }
    
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
}
