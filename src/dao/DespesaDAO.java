package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.Categoria;
import service.Despesa;

public class DespesaDAO {
	Connection conn;
	private boolean controle = true;
	private  void iniciarBD() throws SQLException, IOException {
		conn = BancoDados.conectar();
	}
	public boolean cadastrarDespesa(Despesa despesa) throws SQLException, IOException {
		iniciarBD();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("insert into despesa (categoria, nome, mensal, ocasional, totalano) values (?, ?, ?, ?, ?)");

			st.setString(1, despesa.getCategoria().getNome());
			st.setString(2, despesa.getDespesa());
			st.setDouble(3, despesa.getMensal());
			st.setDouble(4, despesa.getOcasional());
			st.setDouble(5, despesa.getTotalAno());

			if(st.executeUpdate()<=0) controle = false;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
	}
	public boolean editarDespesa(Despesa despesa, String id) throws SQLException, IOException {
		iniciarBD();
		PreparedStatement st = null;

		try {
			
			st = conn.prepareStatement("UPDATE rendimento SET categoria = ?, nome = ?, mensal = ?, ocasional = ? WHERE nome = ?");

			st.setString(1, despesa.getCategoria().getNome());
			st.setString(2, despesa.getDespesa());
			st.setDouble(3, despesa.getMensal());
			st.setDouble(4, despesa.getOcasional());
			st.setString(5, id);

			if(st.executeUpdate()<=0) controle = false;

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
	}
	public List<Despesa> buscarTodos() throws SQLException, IOException{
		iniciarBD();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from despesa ");
			rs = st.executeQuery();
			List<Despesa> listaDespesa = new ArrayList<>();
			
			while(rs.next()) {
				Despesa despesa = new Despesa();
				despesa.setCategoria(new Categoria(rs.getString("categoria")));
				despesa.setDespesa(rs.getString("nome"));
				despesa.setMensal(rs.getDouble("mensal"));
				despesa.setOcasional(rs.getDouble("ocasional"));
				despesa.setTotalAno(rs.getDouble("totalano"));
				listaDespesa.add(despesa);
			}
			return listaDespesa;
		}finally {
			BancoDados.finalizarResultSet(rs);
			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
}
