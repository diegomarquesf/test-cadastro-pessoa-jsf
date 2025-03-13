package com.br.cadastropessoa.managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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
@ViewScoped
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
    	if (pessoa == null) 
            pessoa = new Pessoa();
        
        pessoa.setDocumentos(pessoa.getDocumentos() != null ? pessoa.getDocumentos() : new ArrayList<>());
        pessoa.setEnderecos(pessoa.getEnderecos() != null ? pessoa.getEnderecos() : new ArrayList<>());
        pessoa.setContatos(pessoa.getContatos() != null ? pessoa.getContatos() : new ArrayList<>());

        documento = new Documento();
        endereco = new Endereco();
        contato = new Contato();
    }
    
    public void novo() {
    	pessoa = new Pessoa();
    	pessoa.setDocumentos(new ArrayList<Documento>());
    	pessoa.setEnderecos(new ArrayList<Endereco>());
    	pessoa.setContatos(new ArrayList<Contato>());
    	
    	documento = new Documento();
        endereco = new Endereco();
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
    	
    	try {
            FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("listar-pessoa.xhtml");
        } catch (IOException e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Falha ao redirecionar: " + e.getMessage());
        }
    }
    
    public void editar(Long id) throws IOException {
    	pessoa = pessoaService.buscarPorId(id);
    	
    	if(pessoa != null) {
    		try {
                FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("cadastro-pessoa.xhtml");
            } catch (IOException e) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Falha ao redirecionar: " + e.getMessage());
            }
    	}
    }
    
    public void adicionarDocumento() {
    	if (documento.getTipo() != null && !documento.getTipo().isEmpty() &&
    			documento.getNumero() != null && !documento.getNumero().isEmpty()) {
    		
    		Documento novoDocumento = new Documento();
    		novoDocumento.setTipo(documento.getTipo());
    		novoDocumento.setNumero(documento.getNumero());
    		novoDocumento.setPessoa(pessoa);
    		
    		pessoa.getDocumentos().add(novoDocumento);
    		documento = new Documento();
    		
    		addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Documento adicionado!");
    	} else {
    		addMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Preencha todos os campos do documento!");
        }
    }
    
    public void removerDocumento(Documento doc) {
    	pessoa.getDocumentos().remove(doc);
    	
    	if(doc.getId() != null)
    		documentoService.deletar(doc.getId());
    	
    	addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Documento removido!");
    	
    	FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("formCadastro:tabelasDocumentos");
    }
    
    public void adicionarEndereco() {
    	if (endereco.getLogradouro() != null && !endereco.getLogradouro().isEmpty() &&
    			endereco.getCidade() != null && !endereco.getCidade().isEmpty()){
    		
    		Endereco novoEndereco = new Endereco();
    		novoEndereco.setLogradouro(endereco.getLogradouro());
    		novoEndereco.setNumero(endereco.getNumero());
    		novoEndereco.setCidade(endereco.getCidade());
    		novoEndereco.setPessoa(pessoa);
    		
    		pessoa.getEnderecos().add(novoEndereco);
    		endereco = new Endereco();
    		
    		addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Endereco adicionado!");
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
    		
    		Contato novoContato = new Contato();
    		novoContato.setTipo(contato.getTipo());
    		novoContato.setNumero(contato.getNumero());
    		novoContato.setPessoa(pessoa);
    		
    		pessoa.getContatos().add(novoContato);
    		contato = new Contato();
    		
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
