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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import service.Categoria;
import service.Despesa;

public class guiModuloDespesa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCategoria;
	private JButton btnCadatrar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JSeparator separator;
	private JLabel lblDespesa;
	private JButton btnCadatrar_1;
	private JButton btnEditar_1;
	private JButton btnExcluir_1;
	private JSeparator separator_1;
	private JTable table;
	private Despesa despesa;

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
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public guiModuloDespesa() throws SQLException, IOException {
		setTitle("Modulo de Despesa");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		initComponents();
		this.despesa = new Despesa();
		this.buscarDespesa();
		
	}
	private void initComponents() {
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(278, 7, 60, 17);
		contentPane.add(lblCategoria);
		
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
		btnCadatrar.setBounds(115, 36, 105, 27);
		contentPane.add(btnCadatrar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(254, 36, 105, 27);
		contentPane.add(btnEditar);
		
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
		btnExcluir.setBounds(393, 36, 105, 27);
		contentPane.add(btnExcluir);
		
		separator = new JSeparator();
		separator.setBounds(12, 75, 606, 2);
		contentPane.add(separator);
		
		lblDespesa = new JLabel("Despesa");
		lblDespesa.setBounds(278, 99, 60, 17);
		contentPane.add(lblDespesa);
		
		btnCadatrar_1 = new JButton("Cadastrar");
		btnCadatrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnCadastrarDespesa();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCadatrar_1.setBounds(115, 128, 105, 27);
		contentPane.add(btnCadatrar_1);
		
		btnEditar_1 = new JButton("Editar");
		btnEditar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnEditarDespesa();
				} catch (NumberFormatException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEditar_1.setEnabled(false);
		btnEditar_1.setBounds(254, 128, 105, 27);
		contentPane.add(btnEditar_1);
		
		btnExcluir_1 = new JButton("Excluir");
		btnExcluir_1.setBounds(393, 128, 105, 27);
		contentPane.add(btnExcluir_1);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(12, 167, 606, 2);
		contentPane.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 198, 606, 228);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Categoria", "Despesa", "Mensal", "Ocasional", "Total Ano"
			}
		));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (table.getSelectedRow() != -1) {
                	btnEditar_1.setEnabled(true);
                } else {
                	btnEditar_1.setEnabled(false);
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
	private void btnCadastrarDespesa() throws SQLException, IOException {
		Categoria nome = new Categoria(JOptionPane.showInputDialog(null, "Nome da categoria", "Entrada de dados",JOptionPane.QUESTION_MESSAGE));
		String nomeRende = JOptionPane.showInputDialog(null, "Nome do Rendimento", "Entrada de dados",JOptionPane.QUESTION_MESSAGE);
		double mensal = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor Mensal", "Entrada de dados",JOptionPane.QUESTION_MESSAGE));
		double ocasional = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor Ocasional", "Entrada de dados",JOptionPane.QUESTION_MESSAGE));
		new Despesa(nome, nomeRende, mensal,ocasional).cadastrarDespesa();
		buscarDespesa();
		
	}
	private void btnEditarDespesa() throws NumberFormatException, SQLException, IOException {
		final int getLinha = table.getSelectedRow();
		String nome = JOptionPane.showInputDialog(null, "Nome da Despesa", "Entrada de dados",JOptionPane.QUESTION_MESSAGE);
		new Despesa(
				new Categoria(table.getValueAt(getLinha, 0).toString()),
				table.getValueAt(getLinha, 1).toString(),
				Double.parseDouble(table.getValueAt(getLinha, 2).toString()),
				Double.parseDouble(table.getValueAt(getLinha, 3).toString())
				).editarDespesa(nome.toUpperCase());
		buscarDespesa();
	}
	private void buscarDespesa() throws SQLException, IOException {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.fireTableDataChanged();
		modelo.setRowCount(0);
		
		List<Despesa> itemDespesa = this.despesa.buscarTodos();
		for(Despesa despesas : itemDespesa) {
			modelo.addRow(new Object[] {
					despesas.getCategoria().getNome(),
					despesas.getDespesa(),
					despesas.getMensal(),
					despesas.getOcasional(),
					despesas.getTotalAno()
			});
		}
		
	}
}
