package desafio.capgemini.program;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import desafio.capgemini.controller.Controller;

@SuppressWarnings("serial")
public class Layout extends JFrame {

	private JPanel panel = new JPanel(new FlowLayout());

	private JButton cadastrar = new JButton("Cadastrar");
	private JButton cadastrarVarios = new JButton("Múltiplos Cadastro");
	private JButton delete = new JButton("Excluir");
	private JButton relatorios = new JButton("Gerar Relatório");

	public Layout() {
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("🔥 Gerenciador de Anuncios");
		setPreferredSize(new Dimension(300, 290));
		setLocationRelativeTo(null);
		setResizable(false);

		cadastrar.setPreferredSize(new Dimension(130, 45));
		panel.add(cadastrar);

		delete.setPreferredSize(new Dimension(130, 45));
		panel.add(delete);

		cadastrarVarios.setPreferredSize(new Dimension(150, 45));
		panel.add(cadastrarVarios);

		relatorios.setPreferredSize(new Dimension(420, 45));
		panel.add(relatorios);

		cadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Controller control = new Controller();

				Boolean status = control.Cadastrar();

				if (status) {
					JOptionPane.showMessageDialog(null, "Anuncio Cadastrado com Sucesso", "Sucesso",
							JOptionPane.DEFAULT_OPTION);
				}

			}
		});

		cadastrarVarios.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Controller control = new Controller();

				Boolean condition = true;

				while (condition) {
					Boolean isSaved = control.Cadastrar();

					if (isSaved) {
						JOptionPane.showMessageDialog(rootPane, "Anúncio Cadastrado com Sucesso!");
						int escolha = JOptionPane.showConfirmDialog(null, "Deseja Cadastrar outro Anúncio? ");

						if (escolha != 0) {
							condition = false;
						}
					} else {
						JOptionPane.showConfirmDialog(rootPane, "Falha ao Cadastrar o anúncio", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});

		delete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Controller control = new Controller();
				Boolean condition = true;

				while (condition) {
					String id = JOptionPane.showInputDialog("Qual o Código do Anuncio ?");
					Boolean isDeleted = control.Deletar(Long.parseLong(id));

					if (isDeleted) {
						JOptionPane.showMessageDialog(rootPane, "Anúncio Excluido com Sucesso!");
						int escolha = JOptionPane.showConfirmDialog(null, "Deseja Excluir outro Anúncio? ");

						if (escolha != 0) {
							condition = false;
						}
					} else {
						JOptionPane.showMessageDialog(rootPane, "Falha ao Exclui o Anúncio", "Error",
								JOptionPane.ERROR_MESSAGE);

						int escolha = JOptionPane.showConfirmDialog(null, "Deseja Excluir outro Anúncio? ");

						if (escolha != 0) {
							condition = false;
						}
					}
				}

			}
		});

		relatorios.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				Controller control = new Controller();
				
				String id = JOptionPane.showInputDialog("Qual o código do Anúncio");
				
				Boolean isSucess = control.gerarRelatorio(Long.parseLong(id));
				
				if(isSucess) {
					JOptionPane.showMessageDialog(null, "Anúncio gerado com sucesso em C:\\relatorios");
				}else {
					JOptionPane.showMessageDialog(null, "Falha ao gerar o relatório");
				}
				
				
			}
		});

		this.add(panel);
		setVisible(true);
	}

	
}
