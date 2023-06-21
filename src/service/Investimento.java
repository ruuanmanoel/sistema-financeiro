package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.InvestimentoDAO;


public class Investimento {
	private String investimento;
	private int id;
	private double mensal=0;
	private double ocasional=0;
	private double totalAno;
	private final int ANO = 12;
	
	public Investimento() {
		
	}
	public Investimento(String investimento, double mensal, double ocasional) {
		this.investimento = investimento.toUpperCase();
		this.mensal = mensal;
		this.ocasional = ocasional;
		this.totalAno  = calcularTotal();
	}
	private double calcularTotal() {
		return (this.mensal * this.ANO) + this.ocasional;
	}
	public List<Investimento> buscarTodos() throws SQLException, IOException{
		return new InvestimentoDAO().buscarTodos();
	}
	public boolean cadastrarRendimento() throws SQLException, IOException {
		return new InvestimentoDAO().cadastrarInvestimento(this);
	}
	public boolean editarInvestimento(int id) throws SQLException, IOException {
		return new InvestimentoDAO().editarInvestimento(this, id);
	}
	public boolean excluirInvestimento(int id) throws SQLException, IOException {
		return new InvestimentoDAO().excluirInvestimento(this, id);
	}
	public String getInvestimento() {
		return investimento;
	}
	public void setInvestimento(String investimento) {
		this.investimento = investimento;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
