package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.FundoOcasionalDAO;

public class FundoOcasional {
	private String fundo;
	private int id;
	private double mensal=0;
	private double ocasional=0;
	private double totalAno;
	private final int ANO = 12;
	
	public FundoOcasional() {
		
	}
	public FundoOcasional(String fundo, double mensal, double ocasional) {
		this.fundo = fundo.toUpperCase();
		this.mensal = mensal;
		this.ocasional = ocasional;
		this.totalAno = calcularTotal();
	}
	private double calcularTotal() {
		return (this.mensal * this.ANO) + this.ocasional;
	}
	public List<FundoOcasional> buscarTodos() throws SQLException, IOException{
		return new FundoOcasionalDAO().buscarTodos();
	}
	public boolean cadastrarFundoOcasional() throws SQLException, IOException {
		return new FundoOcasionalDAO().cadastrarFundoOcasional(this);
	}
	public boolean editarFundoOcasional(int id) throws SQLException, IOException {
		return new FundoOcasionalDAO().editarFundoOcasional(this, id);
	}
	public boolean excluirOcasional(int id) throws SQLException, IOException {
		return new FundoOcasionalDAO().excluirFundoOcasional(this, id);
	}
	public String getFundo() {
		return fundo;
	}
	public void setFundo(String fundo) {
		this.fundo = fundo;
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
