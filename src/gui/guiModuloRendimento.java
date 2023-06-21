package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class guiModuloRendimento extends JFrame {
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiModuloRendimento frame = new guiModuloRendimento();
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
	public guiModuloRendimento() {
		setTitle("Modulo de Rendimento");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(null);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(278, 12, 60, 17);
		getContentPane().add(lblCategoria);
		
		JButton btnCadatrar = new JButton("Cadastrar");
		btnCadatrar.setBounds(115, 48, 105, 27);
		getContentPane().add(btnCadatrar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(254, 48, 105, 27);
		getContentPane().add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(393, 48, 105, 27);
		getContentPane().add(btnExcluir);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 87, 606, 2);
		getContentPane().add(separator);
		
		JLabel lblRendimento = new JLabel("Rendimento");
		lblRendimento.setBounds(278, 103, 81, 17);
		getContentPane().add(lblRendimento);
		
		JButton btnCadatrar_1 = new JButton("Cadastrar");
		btnCadatrar_1.setBounds(115, 140, 105, 27);
		getContentPane().add(btnCadatrar_1);
		
		JButton btnEditar_1 = new JButton("Editar");
		btnEditar_1.setBounds(254, 140, 105, 27);
		getContentPane().add(btnEditar_1);
		
		JButton btnExcluir_1 = new JButton("Excluir");
		btnExcluir_1.setBounds(393, 140, 105, 27);
		getContentPane().add(btnExcluir_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 179, 606, 2);
		getContentPane().add(separator_1);
		
		table = new JTable();
		table.setBounds(12, 210, 606, 228);
		getContentPane().add(table);
		
	}
}
