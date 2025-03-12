package com.br.cadastropessoa.DAO;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import java.util.List;
import com.br.cadastropessoa.config.GenericDAO;
import com.br.cadastropessoa.model.Pessoa;

@ApplicationScoped
public class PessoaDAO extends GenericDAO<Pessoa> {
    
    public PessoaDAO() {
        super(Pessoa.class);
    }

    public List<Pessoa> buscarPorNome(String nome) {
        TypedQuery<Pessoa> query = em.createQuery("SELECT p FROM Pessoa p WHERE p.nome LIKE :nome", Pessoa.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }
}
