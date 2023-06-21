package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.DespesaDAO;

public class Despesa {
	private Categoria categoria;
	private String despesa;
	private double mensal;
	private double ocasional;
	private double totalAno;
	private final int ANO = 12;
	
	public Despesa(){}
	public Despesa(Categoria categoria, String despesa, double mensal, double ocasional) {
		this.categoria = categoria;
		this.despesa = despesa.toUpperCase();
		this.mensal = mensal;
		this.ocasional = ocasional;
		this.totalAno = calcularTotal();
	}
	
	private double calcularTotal() {
		return (this.mensal * this.ANO) + this.ocasional;
	}
	public List<Despesa> buscarTodos() throws SQLException, IOException{
		return new DespesaDAO().buscarTodos();
	}
	public boolean cadastrarDespesa() throws SQLException, IOException {
		return new DespesaDAO().cadastrarDespesa(this);
	}
	public boolean editarDespesa(String id) throws SQLException, IOException {
		return new DespesaDAO().editarDespesa(this, id);
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getDespesa() {
		return despesa;
	}
	public void setDespesa(String despesa) {
		this.despesa = despesa;
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
