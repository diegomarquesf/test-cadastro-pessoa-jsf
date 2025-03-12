package com.br.cadastropessoa.DAO;

import javax.enterprise.context.ApplicationScoped;
import com.br.cadastropessoa.config.GenericDAO;
import com.br.cadastropessoa.model.Endereco;

@ApplicationScoped
public class EnderecoDAO extends GenericDAO<Endereco> {

    public EnderecoDAO() {
        super(Endereco.class);
    }
}
