package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Categoria {
	private Connection conn;

	public Categoria(Connection conn) {

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
}
