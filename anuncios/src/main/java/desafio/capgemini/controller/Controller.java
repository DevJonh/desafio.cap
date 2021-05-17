package desafio.capgemini.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

import desafio.capgemini.Calculadora;
import desafio.capgemini.dao.AnunciosDao;
import desafio.capgemini.model.Anuncios;

public class Controller {
	
	public Boolean Cadastrar() {
		AnunciosDao dao = new AnunciosDao();
		Anuncios anuncio = new Anuncios();
		Boolean condition = true;

		String nameAnuncio = JOptionPane.showInputDialog("Qual o nome do Anúncio? ");
		anuncio.setNameAnuncio(nameAnuncio);
		String cliente = JOptionPane.showInputDialog("Qual o nome do Cliente? ");
		anuncio.setCliente(cliente);
		while (condition) {
			String invet = JOptionPane.showInputDialog("Qual o valor investido por dia? ");
			if (invet.matches("[0-9]+")) {
				anuncio.setInvestimentoDia(Double.parseDouble(invet));
				condition = false;
			} else {
				JOptionPane.showMessageDialog(null, "Insira apenas números");
			}
		}

		String inicio = JOptionPane.showInputDialog("Quando irá iniciar o anúncio? ");
	
		DateTimeFormatter inicioFormater = DateTimeFormatter.ofPattern("dd/MM/uuuu")
				.withResolverStyle(ResolverStyle.STRICT);
		LocalDate inicioModify = LocalDate.parse(inicio, inicioFormater);
		anuncio.setInicio(inicioModify);

		String termino = JOptionPane.showInputDialog("Quando irá finalizar o anúncio? ");
		
		DateTimeFormatter fimFormater = DateTimeFormatter.ofPattern("dd/MM/uuuu")
				.withResolverStyle(ResolverStyle.STRICT);
		LocalDate terminoModify = LocalDate.parse(termino, fimFormater);
		anuncio.setTermino(terminoModify);

		dao.save(anuncio);

		return true;

	}

	public Boolean Deletar(Long id) {
		try {
			AnunciosDao dao = new AnunciosDao();
			Anuncios anuncio = dao.searchId(id);
			if (anuncio.getId() == id) {
				dao.delete(id);
				return true;

			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean gerarRelatorio(Long id) {
		NumberFormat nf = NumberFormat.getInstance();
		
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		try {
			AnunciosDao dao = new AnunciosDao();
			Anuncios anuncio = dao.searchId(id);

			Calculadora calcular = new Calculadora();
			
			if(calcular.getInvestimento() == 0) {
				calcular.setInvestimento(anuncio.getInvestimentoDia());
			}
			
			double totalInvest = calcular.totalInvestido(anuncio.getInicio(), anuncio.getTermino());
			

			double maxViews = calcular.maxViews(totalInvest);
			

			int maxShared = calcular.shareds(totalInvest);
		

			int maxClicks = calcular.clicks(totalInvest);
			
			
			long dias = ChronoUnit.DAYS.between(anuncio.getInicio(), anuncio.getTermino());

			File file = new File("C:\\relatorios");

			if (!file.exists()) {
				file.mkdir();
			}

			try {
				file = new File("C:\\relatorios\\" + anuncio.getNameAnuncio() + ".txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				
				FileWriter writer = new FileWriter(file);
				
				writer.write("\t\t\t\t\t Relat�rio do An�ncio "+anuncio.getNameAnuncio()+"\n\n");
				writer.write("CLIENTE: ");
				writer.write(anuncio.getCliente()+"\t\t");
				
				writer.write("IN�CIO DA CAMPANHA: ");
				writer.write(anuncio.getInicio()+"\t\t");
				
				writer.write("T�RMINO DA CAMPANHA: ");
				writer.write(anuncio.getTermino()+"\n\n");
				
				writer.write("---------------------------------------------------------------------------------------------------------------------\n\n");
				
				writer.write("TOTAL INVESTIDO: ");
				writer.write("R$ "+nf.format(totalInvest)+"\t\t\t");
				
				writer.write("QUANTIDADE M�XIMA DE VISUALIZA��ES: ");
				writer.write(nf.format((int) maxViews)+"\n\n");
				
				writer.write("QUANTIDADE M�XIMA DE CLICKS: ");
				writer.write(nf.format(maxClicks)+"\t\t");
				
				writer.write("QUANTIDADE M�XIMA DE COMPARTILHAMENTOS: ");
				writer.write(nf.format(maxShared)+"\n\n");
				
				writer.write("DURA��O DA CAMPANHA: ");
				writer.write(dias+"\t\t\t");
				
				writer.write("INVESTIMENTO DI�RIO: ");
				writer.write("R$ "+nf.format(anuncio.getInvestimentoDia())+"\n\n");
				
				writer.write("---------------------------------------------------------------------------------------------------------------------\n");
				
				writer.flush();
				writer.close();
				
				return true;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}

		

	}

}
