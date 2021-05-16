package desafio.capgemini;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Calculadora {

	private double investimento = 1;

	public void setInvestimento(double invest) {
		this.investimento = invest;
	}

	public double getInvestimento() {
		return investimento;
	}

	public double maxViews(double inves) {
		this.setInvestimento(inves);
		int views = (int) (inves * 30);
		double click = Math.ceil(views * 12 / 100);
		int shared = (int) Math.ceil(click * 15 / 100);
		int newViews = shared * 40;

		return views + newViews;
	}

	public double totalInvestido(LocalDate inicioSearch, LocalDate terminoSearch) {
		long dias = ChronoUnit.DAYS.between(inicioSearch, terminoSearch);

		return investimento * dias;

	}

	public int clicks(double inves) {
		int views = (int) (inves * 30);
		double click = Math.ceil(views * 12 / 100);

		return (int) click;
	}

	public int shareds(double inves) {
		int views = (int) (inves * 30);
		double click = Math.ceil(views * 12 / 100);
		int shared = (int) Math.ceil(click * 15 / 100);

		return shared;
	}

}
