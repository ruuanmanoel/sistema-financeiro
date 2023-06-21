package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import service.Investimento;

public class InvestimentoDAO {
	Connection conn;
	private boolean controle = true;
	private void iniciarBD() throws SQLException, IOException {
		conn = BancoDados.conectar();
	}
	public boolean cadastrarInvestimento(Investimento investimento) throws SQLException, IOException {
		iniciarBD();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("insert into investimento (nome, mensal, ocasional, totalano) values ( ?, ?, ?, ?)");

			st.setString(1, investimento.getInvestimento());
			st.setDouble(2, investimento.getMensal());
			st.setDouble(3, investimento.getOcasional());
			st.setDouble(4, investimento.getTotalAno());

			if(st.executeUpdate()<=0) controle = false;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
		
	}
	public boolean editarInvestimento(Investimento investimento, int id) throws SQLException, IOException {
		iniciarBD();
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("UPDATE investimento SET nome = ?, mensal = ?, ocasional = ?, totalano = ? WHERE id = ?");

			st.setString(1, investimento.getInvestimento());
			st.setDouble(2, investimento.getMensal());
			st.setDouble(3, investimento.getOcasional());
			st.setDouble(4, investimento.getTotalAno());
			st.setInt(5, id);

			if(st.executeUpdate()<=0) controle = false;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
		
	}
	public boolean excluirInvestimento(Investimento investimento, int id) throws SQLException, IOException {
		iniciarBD();
		PreparedStatement st = null;
		try {
			st= conn.prepareStatement("delete from investimento where id = ?");
			st.setInt(1, id);
			if(st.executeUpdate()<=0) controle = false;
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
	}
	public List<Investimento> buscarTodos() throws SQLException, IOException{
			iniciarBD();
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("select * from investimento ");
				rs = st.executeQuery();
				List<Investimento> listaInvestimento = new ArrayList<>();
				
				while(rs.next()) {
					Investimento investimento = new Investimento();
					investimento.setId(rs.getInt("id"));
					investimento.setInvestimento(rs.getString("nome"));
					investimento.setMensal(rs.getDouble("mensal"));
					investimento.setOcasional(rs.getDouble("ocasional"));
					investimento.setTotalAno(rs.getDouble("totalano"));
					listaInvestimento.add(investimento);
				}
				return listaInvestimento;
			}finally {
				BancoDados.finalizarResultSet(rs);
				BancoDados.finalizarStatement(st);
				BancoDados.desconectar();
			}
		}
		
}

