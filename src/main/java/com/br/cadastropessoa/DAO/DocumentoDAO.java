package com.br.cadastropessoa.DAO;

import javax.enterprise.context.ApplicationScoped;

import com.br.cadastropessoa.config.GenericDAO;
import com.br.cadastropessoa.model.Documento;

@ApplicationScoped
public class DocumentoDAO extends GenericDAO<Documento> {

	public DocumentoDAO() {
        super(Documento.class);
    }
}
