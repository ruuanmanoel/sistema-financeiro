package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

import service.Rendimento;

public class RendimentoDAO {
	private Connection conn;

	public RendimentoDAO(Connection conn) {

		this.conn = conn;
	}

	public void cadastrar(Categoria categoria) throws SQLException {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(
					"insert into categoria (nome) values (?)");

			st.setString(1, "OLA");

			st.executeUpdate();

		} finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
	}
	public List<Rendimento> buscarTodos() throws SQLException{
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from rendimento ");
		
			rs = st.executeQuery();
			List<Rendimento> listaRendimento = new ArrayList<>();
			while(rs.next()) {
				Rendimento rendimento = new Rendimento();
				
				rendimento.setRendimento(rs.getString("rendimento"));
				rendimento.setMensal(rs.getDouble("mensal"));
				rendimento.setOcasional(rs.getDouble("ocasional"));
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
