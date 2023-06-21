package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.RendimentoDAO;

public class Rendimento {
	private Categoria categoria;
	private RendimentoDAO rendimentoDAO;
	private String rendimento;
	private double mensal;
	private double ocasional;
	private double totalAno;
	private final int ANO = 12;
	
	public Rendimento() {
		rendimentoDAO = new RendimentoDAO();	
	}
	public Rendimento(Categoria categoria, String rendimento, double mensal, double ocasional) {
		this.categoria = categoria;
		this.rendimento = rendimento.toUpperCase();
		this.mensal = mensal;
		this.ocasional = ocasional;
		this.totalAno = calcularTotal();
		rendimentoDAO = new RendimentoDAO();
	}
	
	private double calcularTotal() {
		return (this.mensal * this.ANO) + this.ocasional;
	}
	public List<Rendimento> buscarTodos() throws SQLException, IOException{
		return this.rendimentoDAO.buscarTodos();
	}
	public boolean cadastrarRendimento() throws SQLException, IOException {
		return new RendimentoDAO().cadastrarRendimento(this);
	}
	public boolean editarRendimento(int id) throws SQLException, IOException {
		return new RendimentoDAO().editarRendimento(this, id);
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
