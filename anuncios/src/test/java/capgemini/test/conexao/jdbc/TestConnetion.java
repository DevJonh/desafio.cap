package capgemini.test.conexao.jdbc;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import desadio.capgemini.model.Anuncios;
import desafio.capgemini.conexao.SingleConnection;
import desafio.capgemini.dao.AnunciosDao;

public class TestConnetion {

	@Test
	public void initDatabase() {
		SingleConnection.getConection();
	}

	@Test
	public void saveInDatabase() {
		AnunciosDao dao = new AnunciosDao();
		Anuncios anuncio = new Anuncios();

		LocalDate inicio = LocalDate.of(2021, 5, 17);
		LocalDate fim = LocalDate.of(2021, 6, 17);

		anuncio.setNameAnuncio("Curso React");
		anuncio.setCliente("Capgemini");
		anuncio.setInvestimentoDia(35);

		anuncio.setInicio(inicio);
		anuncio.setTermino(fim);

		dao.save(anuncio);

	}

	@Test
	public void initSearch() {
		AnunciosDao dao = new AnunciosDao();

		try {
			List<Anuncios> anuncios;
			try {
				anuncios = dao.search();
				for (Anuncios anuncio : anuncios) {
					System.out.println(anuncio);
					System.out.println("--------------------");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void initSearchId() {
		AnunciosDao dao = new AnunciosDao();
		try {
			try {
				Anuncios anuncio;
				anuncio = dao.searchId(2L);
				System.out.println(anuncio);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initUpdate() {
		AnunciosDao dao = new AnunciosDao();

		try {
			try {
				Anuncios anuncio;
				anuncio = dao.searchId(2L);
				anuncio.setNameAnuncio("React Native");

				dao.update(anuncio);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initDelete() {
		try {
			AnunciosDao dao = new AnunciosDao();
			dao.delete(9L);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
}
