package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoriaDAO {
	private Connection conn;
	private boolean controle = true;
	
	private void inicializaBD() throws SQLException, IOException {
		conn = BancoDados.conectar();
	}
	

	public boolean cadastrar(String categoria) throws SQLException, IOException {
		inicializaBD();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(
					"insert into categoria (nome) values (?)");

			st.setString(1, categoria);

			if(st.executeUpdate() <=0) controle = false;

		}catch(SQLException e) {
			controle = false;
		}
			finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
	}
	public boolean excluir(String categoria) throws SQLException, IOException {
		inicializaBD();
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(

					"DELETE FROM categoria WHERE nome = ?");

			st.setString(1, categoria);

			if(st.executeUpdate() <= 0) controle = false;

		}
			finally {

			BancoDados.finalizarStatement(st);
			BancoDados.desconectar();
		}
		return controle;
	}
}
