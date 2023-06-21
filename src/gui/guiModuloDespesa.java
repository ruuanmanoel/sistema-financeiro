package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class guiModuloDespesa extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiModuloDespesa frame = new guiModuloDespesa();
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
	public guiModuloDespesa() {
		setTitle("Modulo de Despesa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(278, 0, 60, 17);
		contentPane.add(lblCategoria);
		
		JButton btnCadatrar = new JButton("Cadastrar");
		btnCadatrar.setBounds(115, 36, 105, 27);
		contentPane.add(btnCadatrar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(254, 36, 105, 27);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(393, 36, 105, 27);
		contentPane.add(btnExcluir);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 75, 606, 2);
		contentPane.add(separator);
		
		JLabel lblDespesa = new JLabel("Despesa");
		lblDespesa.setBounds(278, 89, 60, 17);
		contentPane.add(lblDespesa);
		
		JButton btnCadatrar_1 = new JButton("Cadastrar");
		btnCadatrar_1.setBounds(115, 128, 105, 27);
		contentPane.add(btnCadatrar_1);
		
		JButton btnEditar_1 = new JButton("Editar");
		btnEditar_1.setBounds(254, 128, 105, 27);
		contentPane.add(btnEditar_1);
		
		JButton btnExcluir_1 = new JButton("Excluir");
		btnExcluir_1.setBounds(393, 128, 105, 27);
		contentPane.add(btnExcluir_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 167, 606, 2);
		contentPane.add(separator_1);
		
		table = new JTable();
		table.setBounds(12, 198, 606, 228);
		contentPane.add(table);
	}

}
