package gui;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import service.Categoria;
import service.Rendimento;

public class guiModuloRendimento extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scrollPane;
	private JSeparator separator_1;
	private JButton btnExcluirRendimento;
	private JButton btnEditarRendimento;
	private JButton btnCadatrarRendimento;
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
					rendimentou.getCategoria().getNome(),
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
		btnCadatrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnCadastrarCategoria();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCadatrar.setBounds(115, 48, 105, 27);
		getContentPane().add(btnCadatrar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(254, 48, 105, 27);
		getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnExcluirCategoria();
				} catch (HeadlessException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnExcluir.setBounds(393, 48, 105, 27);
		getContentPane().add(btnExcluir);
		
		separator = new JSeparator();
		separator.setBounds(12, 87, 606, 2);
		getContentPane().add(separator);
		
		lblRendimento = new JLabel("Rendimento");
		lblRendimento.setBounds(278, 103, 81, 17);
		getContentPane().add(lblRendimento);
		
		btnCadatrarRendimento = new JButton("Cadastrar");
		btnCadatrarRendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnCadastrarRendimento();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
					e1.getMessage();
				}
			}
		});
		btnCadatrarRendimento.setBounds(115, 140, 105, 27);
		getContentPane().add(btnCadatrarRendimento);
		
		btnEditarRendimento = new JButton("Editar");
		btnEditarRendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnEditarRendimento();
				} catch (NumberFormatException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEditarRendimento.setBounds(254, 140, 105, 27);
		btnEditarRendimento.setEnabled(false);
		getContentPane().add(btnEditarRendimento);
		
		btnExcluirRendimento = new JButton("Excluir");
		btnExcluirRendimento.setBounds(393, 140, 105, 27);
		getContentPane().add(btnExcluirRendimento);
		
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
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() != -1) {
                	btnEditarRendimento.setEnabled(true);
                } else {
                	btnEditarRendimento.setEnabled(false);
                }
            }
        });
	}
	private void btnCadastrarCategoria() throws SQLException, IOException {
		String nome = JOptionPane.showInputDialog(null, "Nome da categoria", "Entrada de dados",JOptionPane.QUESTION_MESSAGE);
		if(new Categoria(nome.toUpperCase()).cadastrarCategoria()) 
			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso","Cadastro de categoria", JOptionPane.INFORMATION_MESSAGE);
		else 
			JOptionPane.showMessageDialog(null, "Cadastro não realizado","Cadastro de categoria", JOptionPane.ERROR_MESSAGE);
		
	}
	private void btnExcluirCategoria() throws HeadlessException, SQLException, IOException {
		String nome = JOptionPane.showInputDialog(null, "Nome da categoria", "Entrada de dados",JOptionPane.QUESTION_MESSAGE);
		if(new Categoria(nome.toUpperCase()).excluirCategoria())
			JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso","Exclusão de categoria", JOptionPane.INFORMATION_MESSAGE);
		else 
			JOptionPane.showMessageDialog(null, "Exclusão não realizada","Exclusão de categoria", JOptionPane.ERROR_MESSAGE);
		
	}
	private void btnCadastrarRendimento() throws SQLException, IOException {
		Categoria nome = new Categoria(JOptionPane.showInputDialog(null, "Nome da categoria", "Entrada de dados",JOptionPane.QUESTION_MESSAGE));
		String nomeRende = JOptionPane.showInputDialog(null, "Nome do Rendimento", "Entrada de dados",JOptionPane.QUESTION_MESSAGE);
		double mensal = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor Mensal", "Entrada de dados",JOptionPane.QUESTION_MESSAGE));
		double ocasional = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor Ocasional", "Entrada de dados",JOptionPane.QUESTION_MESSAGE));
		new Rendimento(nome, nomeRende, mensal,ocasional).cadastrarRendimento();
		
	}
	private void btnEditarRendimento() throws NumberFormatException, SQLException, IOException {
		final int getLinha = table.getSelectedRow();
		new Rendimento(
				new Categoria(table.getValueAt(getLinha, 0).toString()),
				table.getValueAt(getLinha, 1).toString(),
				Double.parseDouble(table.getValueAt(getLinha, 2).toString()),
				Double.parseDouble(table.getValueAt(getLinha, 3).toString())
				).editarRendimento(getLinha+1);
		
	}
}
