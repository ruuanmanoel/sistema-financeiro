package gui;

import java.awt.EventQueue;
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
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import service.Investimento;	


public class guiModuloInvestimento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnCadastrar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JLabel lblInvestimento;
	private Investimento investimento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiModuloInvestimento frame = new guiModuloInvestimento();
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
	public guiModuloInvestimento() throws SQLException, IOException {
		setTitle("Investimento ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		initComponents();
		this.investimento = new Investimento();
		this.buscarInvestimento();
		
	}
	private void initComponents() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 267, 606, 171);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Investimento", "Mensal", "Ocasional", "Total Anual"
			}
		));
		 TableColumnModel columnModel = table.getColumnModel();
	     TableColumn column = columnModel.getColumn(0);
	     column.setMinWidth(0);
	     column.setMaxWidth(0);
	     column.setWidth(0);
	     column.setPreferredWidth(0);
	     
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnCadastrarInvestimento();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(127, 57, 105, 27);
		contentPane.add(btnCadastrar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnEditarInvestimento();
				} catch (NumberFormatException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEditar.setBounds(244, 57, 105, 27);
		contentPane.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnExcluirInvestimento();
				} catch (NumberFormatException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnExcluir.setBounds(361, 57, 105, 27);
		contentPane.add(btnExcluir);
		
		lblInvestimento = new JLabel("Investimento");
		lblInvestimento.setBounds(257, 12, 105, 17);
		contentPane.add(lblInvestimento);
	}
	private void buscarInvestimento() throws SQLException, IOException {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.fireTableDataChanged();
		modelo.setRowCount(0);
		
		List<Investimento> investimentos = this.investimento.buscarTodos();
		for(Investimento itemIvestimento: investimentos) {
			modelo.addRow(new Object[] {
					itemIvestimento.getId(),
					itemIvestimento.getInvestimento(),
					itemIvestimento.getMensal(),
					itemIvestimento.getOcasional(),
					itemIvestimento.getTotalAno()
					
			});
		}
	}
	private void btnCadastrarInvestimento() throws SQLException, IOException {
		String nome = JOptionPane.showInputDialog(null, "Nome do Investimento", "Entrada de dados",JOptionPane.QUESTION_MESSAGE);
		double mensal = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor Mensal", "Entrada de dados",JOptionPane.QUESTION_MESSAGE));
		double ocasional = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor Ocasional", "Entrada de dados",JOptionPane.QUESTION_MESSAGE));
		if(new Investimento(nome,mensal,ocasional).cadastrarRendimento())
			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso","Cadastro de investimento", JOptionPane.INFORMATION_MESSAGE);
		else 
			JOptionPane.showMessageDialog(null, "Cadastro não realizada","Cadastro de investimento", JOptionPane.ERROR_MESSAGE);
		buscarInvestimento();
		
	}
	private void btnEditarInvestimento() throws NumberFormatException, SQLException, IOException {
		final int getLinha = table.getSelectedRow();
		if(
			new Investimento(
			table.getValueAt(getLinha, 1).toString(),
			Double.parseDouble(table.getValueAt(getLinha, 2).toString()),
			Double.parseDouble(table.getValueAt(getLinha, 3).toString())
			).editarInvestimento(Integer.parseInt(table.getValueAt(getLinha, 0).toString())
		)) { 
			JOptionPane.showMessageDialog(null, "Editado com sucesso","Edição de categoria", JOptionPane.INFORMATION_MESSAGE);
			}
		else 
			JOptionPane.showMessageDialog(null, "Edição não realizada","Edição de categoria", JOptionPane.ERROR_MESSAGE); 
	}
	private void btnExcluirInvestimento() throws NumberFormatException, SQLException, IOException {
		final int getLinha = table.getSelectedRow();
		if(new Investimento().excluirInvestimento(Integer.parseInt(table.getValueAt(getLinha, 0).toString())))
			JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso","Exclusão de categoria", JOptionPane.INFORMATION_MESSAGE);
		else 
			JOptionPane.showMessageDialog(null, "Exclusão não realizada","Exclusão de categoria", JOptionPane.ERROR_MESSAGE);
			
		buscarInvestimento();
	}
}
