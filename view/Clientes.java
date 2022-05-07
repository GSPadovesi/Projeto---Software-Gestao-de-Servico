package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;

public class Clientes extends JDialog {
	private JLabel lblHora;
	private JLabel lblStatus;
	private JLabel lblData;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtId;
	private JButton btnNewButton;
	private JTable tableClientes;

	public static void main(String[] args) {
		try {
			Clientes dialog = new Clientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Clientes() {
		setTitle("HYPER CUSTOM - FUNCION\u00C1RIOS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/icones/icone.png")));
		getContentPane().setBounds(new Rectangle(100, 100, 856, 500));
		getContentPane().setLayout(null);
		setBounds(new Rectangle(100, 100, 856, 500));
		setModal(true);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 884, 67);
		panel.setBackground(Color.GRAY);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblData = new JLabel("");
		lblData.setBounds(38, 11, 294, 29);
		panel.add(lblData);

		lblHora = new JLabel("");
		lblHora.setBounds(382, 11, 132, 29);
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 21));
		panel.add(lblHora);

		lblStatus = new JLabel("");
		lblStatus.setBounds(791, 11, 86, 56);
		lblStatus.setToolTipText("Conex\u00E3o");
		panel.add(lblStatus);

		lblNewLabel = new JLabel("Clie");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 25));
		lblNewLabel.setBounds(259, 70, 117, 36);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("ntes");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.ITALIC, 25));
		lblNewLabel_1.setBounds(312, 78, 131, 20);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Pesquisar");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 107, 111, 25);
		getContentPane().add(lblNewLabel_2);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(70, 152, 111, 27);
		getContentPane().add(txtId);

		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3.setBounds(10, 157, 88, 25);
		getContentPane().add(lblNewLabel_3);

		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Clientes.class.getResource("/icones/Hyper.png")));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.setBounds(709, 351, 131, 110);
		getContentPane().add(btnNewButton);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(SystemColor.control);
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/icones/ExcluirFunc.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBorder(null);
		btnExcluir.setBounds(10, 376, 82, 74);
		getContentPane().add(btnExcluir);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(464, 70, 376, 81);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 356, 59);
		desktopPane.add(scrollPane);

		tableClientes = new JTable();
		tableClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(tableClientes);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/AdicionarFunc.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBorder(null);
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(538, 366, 103, 84);
		getContentPane().add(btnAdicionar);

		JLabel lblNewLabel_3_1 = new JLabel("Nome");
		lblNewLabel_3_1.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3_1.setBounds(0, 193, 88, 25);
		getContentPane().add(lblNewLabel_3_1);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(80, 197, 206, 25);
		getContentPane().add(txtNome);

		lblNewLabel_3_2 = new JLabel("Fone 1");
		lblNewLabel_3_2.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3_2.setBounds(0, 241, 88, 25);
		getContentPane().add(lblNewLabel_3_2);

		txtFoneUm = new JTextField();
		txtFoneUm.setColumns(10);
		txtFoneUm.setBounds(80, 245, 206, 25);
		getContentPane().add(txtFoneUm);

		lblNewLabel_3_3 = new JLabel("Fone 2");
		lblNewLabel_3_3.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3_3.setBounds(0, 288, 88, 25);
		getContentPane().add(lblNewLabel_3_3);

		txtFoneDois = new JTextField();
		txtFoneDois.setColumns(10);
		txtFoneDois.setBounds(91, 292, 195, 25);
		getContentPane().add(txtFoneDois);

		JLabel lblNewLabel_3_4 = new JLabel("UF");
		lblNewLabel_3_4.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3_4.setBounds(263, 351, 88, 25);
		getContentPane().add(lblNewLabel_3_4);

		cboUf = new JComboBox();
		cboUf.setAlignmentX(Component.RIGHT_ALIGNMENT);
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(324, 351, 65, 22);
		getContentPane().add(cboUf);

		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(291, 387, 138, 25);
		getContentPane().add(txtCep);

		lblNewLabel_3_5 = new JLabel("CEP");
		lblNewLabel_3_5.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3_5.setBounds(241, 383, 88, 25);
		getContentPane().add(lblNewLabel_3_5);

		lblNewLabel_3_6 = new JLabel("Endere\u00E7o");
		lblNewLabel_3_6.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3_6.setBounds(439, 166, 94, 25);
		getContentPane().add(lblNewLabel_3_6);

		lblNewLabel_3_7 = new JLabel("Numero");
		lblNewLabel_3_7.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3_7.setBounds(434, 202, 88, 25);
		getContentPane().add(lblNewLabel_3_7);

		lblNewLabel_3_8 = new JLabel("Cidade");
		lblNewLabel_3_8.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3_8.setBounds(439, 241, 88, 25);
		getContentPane().add(lblNewLabel_3_8);

		lblNewLabel_3_9 = new JLabel("Bairro");
		lblNewLabel_3_9.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3_9.setBounds(439, 277, 88, 25);
		getContentPane().add(lblNewLabel_3_9);

		lblNewLabel_3_10 = new JLabel("Complemento");
		lblNewLabel_3_10.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3_10.setBounds(434, 313, 88, 25);
		getContentPane().add(lblNewLabel_3_10);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(553, 163, 206, 25);
		getContentPane().add(txtEndereco);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(553, 199, 206, 25);
		getContentPane().add(txtNumero);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(553, 247, 206, 25);
		getContentPane().add(txtCidade);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(553, 288, 206, 25);
		getContentPane().add(txtBairro);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(553, 319, 206, 25);
		getContentPane().add(txtComplemento);

		btnCep = new JButton("Buscar");
		btnCep.setForeground(Color.WHITE);
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBackground(Color.BLACK);
		btnCep.setBounds(317, 423, 88, 23);
		getContentPane().add(btnCep);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(104, 113, 149, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		btnEditar = new JButton("");
		btnEditar.setEnabled(false);
		btnEditar.setBackground(SystemColor.control);
		btnEditar.setBorder(null);
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/EditarFunc.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(117, 376, 89, 74);
		getContentPane().add(btnEditar);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				setarData();
				status();
			}

		});
	}

	private void setarData() {
		Date dataLabel = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblData.setText(formatador.format(dataLabel));

		Calendar c = Calendar.getInstance();
		Date hora = c.getTime();
		DateFormat horaFormatador = DateFormat.getTimeInstance();
		lblHora.setText(horaFormatador.format(dataLabel));

	}

	DAO dao = new DAO();
	private JTextField txtNome;
	private JButton btnAdicionar;
	private JButton btnExcluir;
	private JLabel lblNewLabel_3_2;
	private JTextField txtFoneUm;
	private JLabel lblNewLabel_3_3;
	private JTextField txtFoneDois;
	private JTextField txtCep;
	private JLabel lblNewLabel_3_5;
	private JLabel lblNewLabel_3_6;
	private JLabel lblNewLabel_3_7;
	private JLabel lblNewLabel_3_8;
	private JLabel lblNewLabel_3_9;
	private JLabel lblNewLabel_3_10;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtCidade;
	private JTextField txtBairro;
	private JTextField txtComplemento;
	private JComboBox cboUf;
	private JButton btnCep;
	private JTextField txtPesquisar;
	private JButton btnEditar;

	private void status() {

		try {
			Connection con = dao.conectar();

			System.out.println("Banco Conectado");

			if (con == null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/desconectou.png")));
			} else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/conectou.png")));
			}
			con.close();
		} catch (com.mysql.cj.jdbc.exceptions.CommunicationsException ex) {
			JOptionPane.showMessageDialog(null, "Servidor Indisponivel");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						// lblStatus.setIcon(new
						// javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
					} else {
						JOptionPane.showMessageDialog(null, "CEP nao encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void setarCampos() {

		int setar = tableClientes.getSelectedRow();

		txtId.setText(tableClientes.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(tableClientes.getModel().getValueAt(setar, 1).toString());
		txtFoneUm.setText(tableClientes.getModel().getValueAt(setar, 2).toString());
		cboUf.setSelectedItem(tableClientes.getModel().getValueAt(setar, 3).toString());
		txtEndereco.setText(tableClientes.getModel().getValueAt(setar, 4).toString());
		txtNumero.setText(tableClientes.getModel().getValueAt(setar, 5).toString());
		txtCep.setText(tableClientes.getModel().getValueAt(setar, 6).toString());
		txtComplemento.setText(tableClientes.getModel().getValueAt(setar, 7).toString());
		txtBairro.setText(tableClientes.getModel().getValueAt(setar, 8).toString());
		txtCidade.setText(tableClientes.getModel().getValueAt(setar, 9).toString());
		txtFoneDois.setText(tableClientes.getModel().getValueAt(setar, 10).toString());
		
		

		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
	}
	
	private void pesquisarCliente() {
		String read = "select idcli as ID, nome as clientes, fone as Telefone,uf, endereco as Endereço, numero as Numero, cep as Cep, complemento as Complemento, bairro as Bairro, cidade as Cidade, fone2 as Celular from clientes where nome like ?";
		try {

			
			Connection con = dao.conectar();
			
			PreparedStatement pst = con.prepareStatement(read);
			
			pst.setString(1, txtPesquisar.getText() + "%");
		
			ResultSet rs = pst.executeQuery();
		
			tableClientes.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void adicionarCliente(){
		
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo nome", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtNome.requestFocus();
		} else if (txtFoneUm.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Fone", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtFoneUm.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtCidade.requestFocus();
		}else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo UF", "Atenção", JOptionPane.WARNING_MESSAGE);
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo endereço", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Numero", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtNumero.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cep", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtCep.requestDefaultFocus();
		} else {
			
			String create = "insert into clientes (nome, cep, endereco, numero, complemento, bairro, cidade, uf, fone, fone2) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";		
			
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCep.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumero.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCidade.getText());
				pst.setString(8, cboUf.getSelectedItem().toString());
				pst.setString(9, txtFoneUm.getText());
				pst.setString(10, txtFoneDois.getText());
				
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente Adicionado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
				con.close();
				limpar();
				
			} catch (Exception e) {
				System.out.println(e);
			}
		
		}
		
	}
	
	private void editarCliente() {
		
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo nome", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtNome.requestFocus();
		} else if (txtFoneUm.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Fone", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtFoneUm.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtCidade.requestFocus();
		}else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo UF", "Atenção", JOptionPane.WARNING_MESSAGE);
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo endereço", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Numero", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtNumero.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cep", "Atenção", JOptionPane.WARNING_MESSAGE);
			txtCep.requestDefaultFocus();
		} else {
			String update = "update clientes set nome = ?,fone = ?,fone2 = ?,endereco = ?,numero = ?, cidade = ?,bairro = ?, complemento = ?, uf = ?, cep = ? where idcli = ?";
			try {
				
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFoneUm.getText());
				pst.setString(3, txtFoneDois.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtCidade.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtComplemento.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtCep.getText());
				pst.setString(11, txtId.getText());
				

				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente editado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	}
	
	private void excluirCliente() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desse cliente ?", "Atenção!", JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from clientes where idcli = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					JOptionPane.showMessageDialog(null, "Cliente Excluido com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				} 
				pst.executeUpdate();
				con.close();
				limpar();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Exclusão não concluida /n O cliente possui pedido em aberto", "Atenção!", JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	private void limpar() {
		
		txtId.setText(null);
		txtNome.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtFoneUm.setText(null);
		txtFoneDois.setText(null);
		txtComplemento.setText(null);
		((DefaultTableModel) tableClientes.getModel()).setRowCount(0);
		
		
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
}
