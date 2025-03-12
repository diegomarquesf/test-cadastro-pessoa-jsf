package com.br.cadastropessoa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Contato implements Serializable{
	 private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private String tipo;
	 private String numero;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "pessoa_id")
	 private Pessoa pessoa;
	 
	public Contato() {
		super();
	}
	
	public Contato(Long id, String tipo, String numero, Pessoa pessoa) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.numero = numero;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
