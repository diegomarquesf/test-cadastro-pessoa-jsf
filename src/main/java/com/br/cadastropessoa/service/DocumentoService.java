package com.br.cadastropessoa.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.br.cadastropessoa.DAO.DocumentoDAO;
import com.br.cadastropessoa.model.Documento;

@RequestScoped
public class DocumentoService {
	
    @Inject
    private DocumentoDAO documentoDAO;

    @Transactional
    public void salvar(Documento documento) {
    	if(documento != null)
    		documentoDAO.salvar(documento);
    }

    @Transactional
    public void deletar(Long id) {
    	if(id != null)
    		documentoDAO.deletar(id);
    }
}