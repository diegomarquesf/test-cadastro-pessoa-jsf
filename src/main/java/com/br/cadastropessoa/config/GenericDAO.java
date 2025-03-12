package com.br.cadastropessoa.config;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDAO<T> {

	@PersistenceContext(unitName = "CadastroPessoaPU")
    protected EntityManager em;

    private final Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void salvar(T entidade) {
        em.persist(entidade);
    }

    public T buscarPorId(Long id) {
        return em.find(entityClass, id);
    }

    public void atualizar(T entidade) {
        em.merge(entidade);
    }

    public void deletar(Long id) {
        T entidade = buscarPorId(id);
        
        if (entidade != null) 
            em.remove(entidade);
    }

    public List<T> buscarTodos() {
        return em.createQuery("FROM " + entityClass.getSimpleName(), entityClass).getResultList();
    }
    
}

    