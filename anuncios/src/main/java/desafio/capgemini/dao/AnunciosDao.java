package desafio.capgemini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import desadio.capgemini.model.Anuncios;
import desafio.capgemini.conexao.SingleConnection;

public class AnunciosDao {

	private Connection connection;

	public AnunciosDao() {
		connection = SingleConnection.getConection();
	}

	public void save(Anuncios anuncio) {
		try {
			String sql = "INSERT INTO public.anuncio(name, cliente, data_inicio, data_fim, investimento_dia)"
					+ "	VALUES (?, ?, ?, ?, ?)";
			

			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, anuncio.getNameAnuncio());
			insert.setString(2, anuncio.getCliente());

			insert.setObject(3, anuncio.getInicio());
			insert.setObject(4, anuncio.getTermino());

			insert.setDouble(5, anuncio.getInvestimentoDia());

			insert.execute();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}

	}

	public List<Anuncios> search() throws SQLException, ParseException {

		List<Anuncios> anuncios = new ArrayList<Anuncios>();

		String sql = "SELECT * FROM anuncio";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		LocalDate inicio;
		LocalDate termino;

		while (result.next()) {
			Anuncios anuncio = new Anuncios();

			inicio = result.getDate("data_inicio").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			termino = result.getTimestamp("data_fim").toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			anuncio.setId(result.getLong("id"));
			anuncio.setNameAnuncio(result.getString("name"));
			anuncio.setCliente(result.getString("cliente"));
			anuncio.setInicio(inicio);
			anuncio.setTermino(termino);
			anuncio.setInvestimentoDia(result.getDouble("investimento_dia"));

			anuncios.add(anuncio);

		}

		return anuncios;
	}

	public Anuncios searchId(Long id) throws SQLException, ParseException {

		Anuncios anuncio = new Anuncios();

		String sql = "SELECT * FROM anuncio WHERE id =" + id;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		LocalDate inicio;
		LocalDate termino;

		while (result.next()) {

			inicio = result.getDate("data_inicio").toLocalDate();
			termino = result.getDate("data_fim").toLocalDate();

			anuncio.setId(result.getLong("id"));
			anuncio.setNameAnuncio(result.getString("name"));
			anuncio.setCliente(result.getString("cliente"));
			anuncio.setInicio(inicio);
			anuncio.setTermino(termino);
			anuncio.setInvestimentoDia(result.getDouble("investimento_dia"));

		}

		return anuncio;
	}
	
	

	public void update(Anuncios anuncio) {
		String sql = "UPDATE anuncio SET name = ? WHERE id = " + anuncio.getId();

		try {
			PreparedStatement up = connection.prepareStatement(sql);

			up.setString(1, anuncio.getNameAnuncio());
			up.execute();

			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public void delete(Long id) {
		try {

			String sql = "DELETE FROM anuncio WHERE id = " + id;

			PreparedStatement prepare = connection.prepareStatement(sql);
			prepare.execute();

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}
}
