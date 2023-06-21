package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BancoDados;
import dao.RendimentoDAO;

public class Rendimento {
	private Categoria categoria;
	private String rendimento;
	private double mensal;
	private double ocasional;
	private double totalAno;
	private Connection conn;
	private final int ANO = 12;
	
	public Rendimento() {
		
	}
	public Rendimento(Categoria categoria, String rendimento, double mensal, double ocasional) {
		this.categoria = categoria;
		this.rendimento = rendimento;
		this.mensal = mensal;
		this.ocasional = ocasional;
		this.totalAno = calcularTotal();
	}
	
	private double calcularTotal() {
		return (this.mensal * this.ANO) + this.ocasional;
	}
	public List<Rendimento> buscarTodos() throws SQLException, IOException {
		conn = BancoDados.conectar();
		RendimentoDAO rendimentoDAO = new RendimentoDAO(conn);
		return rendimentoDAO.buscarTodos();
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getRendimento() {
		return rendimento;
	}
	public void setRendimento(String rendimento) {
		this.rendimento = rendimento;
	}
	public double getMensal() {
		return mensal;
	}
	public void setMensal(double mensal) {
		this.mensal = mensal;
	}
	public double getOcasional() {
		return ocasional;
	}
	public void setOcasional(double ocasional) {
		this.ocasional = ocasional;
	}
	public double getTotalAno() {
		return totalAno;
	}
	public void setTotalAno(double totalAno) {
		this.totalAno = totalAno;
	}
	
	
}
