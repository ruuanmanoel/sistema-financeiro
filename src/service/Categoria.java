package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.CategoriaDAO;

public class Categoria {
	private String nome;
	
	public Categoria(String nome) {
		this.nome = nome.toUpperCase();
	}
	public boolean cadastrarCategoria() throws SQLException, IOException {
		return new CategoriaDAO().cadastrar(this.nome);
	}
	public boolean excluirCategoria() throws SQLException, IOException {
		return new CategoriaDAO().excluir(nome);
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
