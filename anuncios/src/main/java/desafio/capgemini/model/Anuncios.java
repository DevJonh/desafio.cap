package desafio.capgemini.model;

import java.time.LocalDate;

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


}
