package desafio.capgemini.model;

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

public class Anuncios {

	private Long id;
	private String nameAnuncio;
	private String cliente;
	private LocalDate inicio;
	private LocalDate termino;
	private double investimentoDia;

	public String getNameAnuncio() {
		return nameAnuncio;
	}

	public void setNameAnuncio(String nameAnuncio) {
		this.nameAnuncio = nameAnuncio;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getTermino() {
		return termino;
	}

	public void setTermino(LocalDate termino) {
		this.termino = termino;
	}

	public double getInvestimentoDia() {
		return investimentoDia;
	}

	public void setInvestimentoDia(double investimentoDia) {
		this.investimentoDia = investimentoDia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Anuncios [id=" + id + ", nameAnuncio=" + nameAnuncio + ", cliente=" + cliente + ", inicio=" + inicio
				+ ", termino=" + termino + ", investimentoDia=" + investimentoDia + "]";
	}

	public Boolean Cadastrar() {
		AnunciosDao dao = new AnunciosDao();
		Boolean condition = true;

		String nameAnuncio = JOptionPane.showInputDialog("Qual o nome do Anúncio? ");
		this.setNameAnuncio(nameAnuncio);
		String cliente = JOptionPane.showInputDialog("Qual o nome do Cliente? ");
		this.setCliente(cliente);
		while (condition) {
			String invet = JOptionPane.showInputDialog("Qual o valor investido por dia? ");
			if (invet.matches("[0-9]+")) {
				this.setInvestimentoDia(Double.parseDouble(invet));
				condition = false;
			} else {
				JOptionPane.showMessageDialog(null, "Insira apenas números");
			}
		}

		String inicio = JOptionPane.showInputDialog("Quando irá iniciar o anúncio? ");
	
		DateTimeFormatter inicioFormater = DateTimeFormatter.ofPattern("dd/MM/uuuu")
				.withResolverStyle(ResolverStyle.STRICT);
		LocalDate inicioModify = LocalDate.parse(inicio, inicioFormater);
		this.setInicio(inicioModify);

		String termino = JOptionPane.showInputDialog("Quando irá finalizar o anúncio? ");
		
		DateTimeFormatter fimFormater = DateTimeFormatter.ofPattern("dd/MM/uuuu")
				.withResolverStyle(ResolverStyle.STRICT);
		LocalDate terminoModify = LocalDate.parse(termino, fimFormater);
		this.setTermino(terminoModify);

		dao.save(this);

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
				
				writer.write("\t\t\t\t\t Relatório do Anúncio "+anuncio.getNameAnuncio()+"\n\n");
				writer.write("CLIENTE: ");
				writer.write(anuncio.getCliente()+"\t\t");
				
				writer.write("INÍCIO DA CAMPANHA: ");
				writer.write(anuncio.getInicio()+"\t\t");
				
				writer.write("TÉRMINO DA CAMPANHA: ");
				writer.write(anuncio.getTermino()+"\n\n");
				
				writer.write("---------------------------------------------------------------------------------------------------------------------\n\n");
				
				writer.write("TOTAL INVESTIDO: ");
				writer.write("R$ "+nf.format(totalInvest)+"\t\t\t");
				
				writer.write("QUANTIDADE MÁXIMA DE VISUALIZAÇÕES: ");
				writer.write(nf.format((int) maxViews)+"\n\n");
				
				writer.write("QUANTIDADE MÁXIMA DE CLICKS: ");
				writer.write(nf.format(maxClicks)+"\t\t");
				
				writer.write("QUANTIDADE MÁXIMA DE COMPARTILHAMENTOS: ");
				writer.write(nf.format(maxShared)+"\n\n");
				
				writer.write("DURAÇÃO DA CAMPANHA: ");
				writer.write(dias+"\t\t\t");
				
				writer.write("INVESTIMENTO DIÁRIO: ");
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
