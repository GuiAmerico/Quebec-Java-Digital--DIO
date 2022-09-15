package com.primeiroprograma.model;

import java.util.Objects;

public class Gato {
	private String nome;
	private String gato;
	private Integer idade;

	public Gato() {

	}

	public Gato(String nome, String gato, Integer idade) {
		this.nome = nome;
		this.gato = gato;
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGato() {
		return gato;
	}

	public void setGato(String gato) {
		this.gato = gato;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gato, idade, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gato other = (Gato) obj;
		return Objects.equals(gato, other.gato) && Objects.equals(idade, other.idade)
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Gato [nome=" + nome + ", gato=" + gato + ", idade=" + idade + "]";
	}

}
