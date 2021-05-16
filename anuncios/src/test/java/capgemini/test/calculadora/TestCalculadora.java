package capgemini.test.calculadora;

import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Test;

import desafio.capgemini.Calculadora;
import desafio.capgemini.dao.AnunciosDao;
import desafio.capgemini.model.Anuncios;

public class TestCalculadora {

	@Test
	public void calculo() {
		AnunciosDao dao = new AnunciosDao();
		try {
			Anuncios anuncio = dao.searchId(10L);
			Calculadora calcular = new Calculadora();
			
			if(calcular.getInvestimento() == 0) {
				calcular.setInvestimento(anuncio.getInvestimentoDia());
			}
			
			double totalInvest = calcular.totalInvestido(anuncio.getInicio(), anuncio.getTermino());
			System.out.println(totalInvest);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

		
	}
}
