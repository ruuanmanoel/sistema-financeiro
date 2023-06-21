package gui;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.BancoDados;
import dao.RendimentoDAO;
import service.Rendimento;

public class guiModuloRendimento extends JFrame {
	private JTable table;
	private JScrollPane scrollPane;
	private JSeparator separator_1;
	private JButton btnExcluir_1;
	private JButton btnEditar_1;
	private JButton btnCadatrar_1;
	private JLabel lblRendimento;
	private JSeparator separator;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnCadatrar;
	private JLabel lblCategoria;
	private Rendimento rendimento;

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

	public guiModuloRendimento() throws SQLException, IOException {
		setTitle("Modulo de Rendimento");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(null);
		
		initComponent();
		this.rendimento = new Rendimento();
		this.buscarRendimento();
		
	}
	private void buscarRendimento() throws SQLException, IOException {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.fireTableDataChanged();
		modelo.setRowCount(0);
		
		List<Rendimento> rendimentos = this.rendimento.buscarTodos();
		for(Rendimento rendimentou : rendimentos) {
			modelo.addRow(new Object[] {
					rendimentou.getCategoria(),
					rendimentou.getRendimento(),
					rendimentou.getMensal(),
					rendimentou.getOcasional(),
					rendimentou.getTotalAno()
			});
		}
		
	}
	private void initComponent() {
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(278, 12, 60, 17);
		getContentPane().add(lblCategoria);
		
		btnCadatrar = new JButton("Cadastrar");
		btnCadatrar.setBounds(115, 48, 105, 27);
		getContentPane().add(btnCadatrar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(254, 48, 105, 27);
		getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(393, 48, 105, 27);
		getContentPane().add(btnExcluir);
		
		separator = new JSeparator();
		separator.setBounds(12, 87, 606, 2);
		getContentPane().add(separator);
		
		lblRendimento = new JLabel("Rendimento");
		lblRendimento.setBounds(278, 103, 81, 17);
		getContentPane().add(lblRendimento);
		
		btnCadatrar_1 = new JButton("Cadastrar");
		btnCadatrar_1.setBounds(115, 140, 105, 27);
		getContentPane().add(btnCadatrar_1);
		
		btnEditar_1 = new JButton("Editar");
		btnEditar_1.setBounds(254, 140, 105, 27);
		getContentPane().add(btnEditar_1);
		
		btnExcluir_1 = new JButton("Excluir");
		btnExcluir_1.setBounds(393, 140, 105, 27);
		getContentPane().add(btnExcluir_1);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(12, 179, 606, 2);
		getContentPane().add(separator_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 210, 606, 228);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Categoria", "Rendimento", "Mensal", "Ocasional", "Total Ano"
			}
		));
	}
}
