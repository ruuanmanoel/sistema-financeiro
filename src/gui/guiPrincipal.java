package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class guiPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiPrincipal frame = new guiPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public guiPrincipal() {
		setResizable(false);
		setTitle("Sistema Financeiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnModuloRendimento = new JButton("Modulo Rendimento");
		btnModuloRendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new guiModuloRendimento().setVisible(true);
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				};
				
			}
		});
		btnModuloRendimento.setBounds(54, 51, 175, 27);
		contentPane.add(btnModuloRendimento);
		
		JButton btnModuloDespesa = new JButton("Modulo Despesa");
		btnModuloDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new guiModuloDespesa().setVisible(true);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModuloDespesa.setBounds(45, 185, 184, 27);
		contentPane.add(btnModuloDespesa);
		
		JButton btnModuloInvestimento = new JButton("Modulo Investimento");
		btnModuloInvestimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new guiModuloInvestimento().setVisible(true);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModuloInvestimento.setBounds(333, 51, 184, 27);
		contentPane.add(btnModuloInvestimento);
		
		JButton btnFundosOcasionais = new JButton("Fundos Ocasionais");
		btnFundosOcasionais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new guiFundoOcasional().setVisible(true);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFundosOcasionais.setBounds(333, 185, 184, 27);
		contentPane.add(btnFundosOcasionais);
	}

}
