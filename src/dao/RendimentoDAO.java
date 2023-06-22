package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import service.Categoria;
import service.Rendimento;

public class RendimentoDAO {
	private Connection conn;
	private boolean controle = true;

	private  void iniciarBD() throws SQLException, IOException {
		conn = BancoDados.conectar();
	}
	

	public boolean cadastrarRendimento(Rendimento rendimento) throws SQLException, IOException {
		iniciarBD();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("insert into rendimento (categoria, nome, mensal, ocasional, totalano) values (?, ?, ?, ?, ?)");

			st.setString(1, rendimento.getCategoria().getNome());
			st.setString(2, rendimento.getRendimento());
			st.setDouble(3, rendimento.getMensal());
			st.setDouble(4, rendimento.getOcasional());
			st.setDouble(5, rendimento.getTotalAno());

			if(st.executeUpdate()<=0) controle = false;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
	}
	public boolean editarRendimento(Rendimento rendimento, int id) throws SQLException, IOException {
		iniciarBD();
		PreparedStatement st = null;

		try {
			
			st = conn.prepareStatement("UPDATE rendimento SET categoria = ?, nome = ?, mensal = ?, ocasional = ?, totalano = ? WHERE id = ?");

			st.setString(1, rendimento.getCategoria().getNome());
			st.setString(2, rendimento.getRendimento());
			st.setDouble(3, rendimento.getMensal());
			st.setDouble(4, rendimento.getOcasional());
			st.setDouble(5, rendimento.getTotalAno());
			st.setInt(6, id);

			if(st.executeUpdate()<=0) controle = false;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
	}
	
	public boolean excluirRendimento(Rendimento rendimento, int id) throws SQLException, IOException {
		iniciarBD();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("delete from rendimento where id = ?");
			st.setInt(1, id);
			if(st.executeUpdate()<=0) controle = false;
		}finally {
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
	}
	
	public List<Rendimento> buscarTodos() throws SQLException, IOException{
		conn = BancoDados.conectar();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from rendimento ");
		
			rs = st.executeQuery();
			List<Rendimento> listaRendimento = new ArrayList<>();
			while(rs.next()) {
				Rendimento rendimento = new Rendimento();
				rendimento.setId(rs.getInt("id"));
				rendimento.setCategoria(new Categoria(rs.getString("categoria")));
				rendimento.setRendimento(rs.getString("nome"));
				rendimento.setMensal(rs.getDouble("mensal"));
				rendimento.setOcasional(rs.getDouble("ocasional"));
				rendimento.setTotalAno(rs.getDouble("totalano"));
				listaRendimento.add(rendimento);
			}
			return listaRendimento;
		}finally {
			BancoDados.finalizarResultSet(rs);
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
}
