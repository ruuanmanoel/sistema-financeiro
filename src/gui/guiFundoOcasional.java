package gui;

import java.awt.EventQueue;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import service.FundoOcasional;
import service.Investimento;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class guiFundoOcasional extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JSeparator separator;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JButton btnCadastrar;
	private JLabel lblFundoOcasional;
	private JScrollPane scrollPane;
	private FundoOcasional fundo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiFundoOcasional frame = new guiFundoOcasional();
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
	public guiFundoOcasional() throws SQLException, IOException {
		setTitle("Fundo Ocasional ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		initComponents();
		this.fundo = new FundoOcasional();
		this.buscarFundoOcasional();
		
		
	
	}
	private void initComponents(){
		lblFundoOcasional = new JLabel("Fundo Ocasional");
		lblFundoOcasional.setBounds(257, 12, 111, 17);
		contentPane.add(lblFundoOcasional);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnCadastrarFundoOcasional();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(104, 49, 105, 27);
		contentPane.add(btnCadastrar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnEditarFundoOcasional();
				} catch (NumberFormatException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEditar.setBounds(257, 49, 105, 27);
		contentPane.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnExcluirFundoOcasional();
				} catch (NumberFormatException | SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnExcluir.setBounds(410, 49, 105, 27);
		contentPane.add(btnExcluir);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 266, 606, 172);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Fundo Ocasional", "Mensal", "Ocasional", "Total Anual"
			}
		));
		TableColumnModel columnModel = table.getColumnModel();
	     TableColumn column = columnModel.getColumn(0);
	     column.setMinWidth(0);
	     column.setMaxWidth(0);
	     column.setWidth(0);
	     column.setPreferredWidth(0);
		separator = new JSeparator();
		separator.setBounds(12, 109, 594, 2);
		contentPane.add(separator);
	}
	
	private void buscarFundoOcasional() throws SQLException, IOException {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.fireTableDataChanged();
		modelo.setRowCount(0);
		
		List<FundoOcasional> fundos = this.fundo.buscarTodos();
		for(FundoOcasional itemFundos: fundos) {
			modelo.addRow(new Object[] {
					itemFundos.getId(),
					itemFundos.getFundo(),
					itemFundos.getMensal(),
					itemFundos.getOcasional(),
					itemFundos.getTotalAno()
					
			});
		}
	}
	private void btnCadastrarFundoOcasional() throws SQLException, IOException {
		String nome = JOptionPane.showInputDialog(null, "Nome do Fundo", "Entrada de dados",JOptionPane.QUESTION_MESSAGE);
		double mensal = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor Mensal", "Entrada de dados",JOptionPane.QUESTION_MESSAGE));
		double ocasional = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor Ocasional", "Entrada de dados",JOptionPane.QUESTION_MESSAGE));
		if(new FundoOcasional(nome,mensal,ocasional).cadastrarFundoOcasional())
			JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso","Cadastro de Fundos", JOptionPane.INFORMATION_MESSAGE);
		else 
			JOptionPane.showMessageDialog(null, "Cadastro não realizada","Cadastro de Fundos", JOptionPane.ERROR_MESSAGE);
		buscarFundoOcasional();
		
	}
	private void btnEditarFundoOcasional() throws NumberFormatException, SQLException, IOException {
		final int getLinha = table.getSelectedRow();
		if(
			new FundoOcasional(
			table.getValueAt(getLinha, 1).toString(),
			Double.parseDouble(table.getValueAt(getLinha, 2).toString()),
			Double.parseDouble(table.getValueAt(getLinha, 3).toString())
			).editarFundoOcasional(Integer.parseInt(table.getValueAt(getLinha, 0).toString())
		)) { 
			JOptionPane.showMessageDialog(null, "Editado com sucesso","Edição de fundo", JOptionPane.INFORMATION_MESSAGE);
			buscarFundoOcasional();
			}
		else 
			JOptionPane.showMessageDialog(null, "Edição não realizada","Edição de fundo", JOptionPane.ERROR_MESSAGE); 
		
	}
	private void btnExcluirFundoOcasional() throws NumberFormatException, SQLException, IOException {
		final int getLinha = table.getSelectedRow();
		if(new Investimento().excluirInvestimento(Integer.parseInt(table.getValueAt(getLinha, 0).toString())))
			JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso","Exclusão de fundo", JOptionPane.INFORMATION_MESSAGE);
		else 
			JOptionPane.showMessageDialog(null, "Exclusão não realizada","Exclusão de fundo", JOptionPane.ERROR_MESSAGE);
			
		buscarFundoOcasional();
	}
}
