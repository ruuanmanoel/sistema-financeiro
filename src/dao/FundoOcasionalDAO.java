package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.FundoOcasional;

public class FundoOcasionalDAO {
	Connection conn;
	private boolean controle = true;
	
	private void iniciarBD() throws SQLException, IOException {
		conn = BancoDados.conectar();
	}
	
	public boolean cadastrarFundoOcasional(FundoOcasional fundo) throws SQLException, IOException {
		iniciarBD();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("insert into despesaocasional (nome, mensal, ocasional, totalano) values ( ?, ?, ?, ?)");
			st.setString(1, fundo.getFundo());
			st.setDouble(2, fundo.getMensal());
			st.setDouble(3, fundo.getOcasional());
			st.setDouble(4, fundo.getTotalAno());
			
			if(st.executeUpdate()<=0) controle = false;
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
	}
	
	public boolean editarFundoOcasional(FundoOcasional fundo, int id) throws SQLException, IOException {
		iniciarBD();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE despesaocasional SET nome = ?, mensal = ?, ocasional = ?, totalano = ? WHERE id = ?");
			st.setString(1, fundo.getFundo());
			st.setDouble(2, fundo.getMensal());
			st.setDouble(3, fundo.getOcasional());
			st.setDouble(4, fundo.getTotalAno());
			st.setInt(5, id);

			if(st.executeUpdate()<=0) controle = false;
		} finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
	}
	
	public boolean excluirFundoOcasional(FundoOcasional fundo, int id) throws SQLException, IOException {
		iniciarBD();
		PreparedStatement st = null;
		try {
			st= conn.prepareStatement("delete from despesaocasional where id = ?");
			st.setInt(1, id);
			if(st.executeUpdate()<=0) controle = false;
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
	}
	public List<FundoOcasional> buscarTodos() throws SQLException, IOException{
			iniciarBD();
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("select * from despesaocasional ");
				rs = st.executeQuery();
				List<FundoOcasional> listaFundo = new ArrayList<>();
				
				while(rs.next()) {
					FundoOcasional fundo = new FundoOcasional();
					fundo.setId(rs.getInt("id"));
					fundo.setFundo(rs.getString("nome"));
					fundo.setMensal(rs.getDouble("mensal"));
					fundo.setOcasional(rs.getDouble("ocasional"));
					fundo.setTotalAno(rs.getDouble("totalano"));
					listaFundo.add(fundo);
				}
				return listaFundo;
			}finally {
				BancoDados.finalizarResultSet(rs);
				BancoDados.finalizarStatement(st);
				BancoDados.desconectar();
			}
		}
}
