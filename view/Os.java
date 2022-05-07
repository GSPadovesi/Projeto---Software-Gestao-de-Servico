package view;




import java.awt.Color;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.border.TitledBorder;




public class Os extends JDialog {
	private JLabel lblHora;
	private JLabel lblStatus;
	private JLabel lblData;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JTable tableClientes;
	private byte[] imagem;
	private String tipo;
	private JButton btnImagem;
	private JLabel lblNewLabel_11;


	public static void main(String[] args) {
		try {
			Os dialog = new Os();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	

	public Os() {
		
		
		setTitle("HYPER CUSTOM - FUNCION\u00C1RIOS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/icones/icone.png")));
		getContentPane().setBounds(new Rectangle(100, 100, 856, 500));
		getContentPane().setLayout(null);
		setBounds(new Rectangle(100, 100, 891, 573));
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

		lblNewLabel = new JLabel("Ordem D");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 25));
		lblNewLabel.setBounds(230, 62, 157, 36);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("e Servi\u00E7o");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.ITALIC, 25));
		lblNewLabel_1.setBounds(350, 70, 131, 20);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Pesquisar");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(0, 78, 111, 25);
		getContentPane().add(lblNewLabel_2);

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
		btnNewButton.setBounds(746, 421, 119, 113);
		getContentPane().add(btnNewButton);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirOs();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(SystemColor.control);
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/icones/ExcluirFunc.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBorder(null);
		btnExcluir.setBounds(0, 460, 82, 74);
		getContentPane().add(btnExcluir);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(489, 92, 376, 96);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 356, 74);
		desktopPane.add(scrollPane);

		tableClientes = new JTable();
		tableClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pesquisarId();
			}
		});
		scrollPane.setViewportView(tableClientes);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarOs();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/AdicionarFunc.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBorder(null);
		btnAdicionar.setBackground(SystemColor.menu);
		btnAdicionar.setBounds(632, 439, 103, 84);
		getContentPane().add(btnAdicionar);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(89, 78, 131, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		btnEditar = new JButton("");
		btnEditar.setEnabled(false);
		btnEditar.setBackground(SystemColor.control);
		btnEditar.setBorder(null);
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/icones/EditarFunc.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarOs();
			}
		});
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(89, 460, 89, 74);
		getContentPane().add(btnEditar);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Os", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 109, 341, 147);
		getContentPane().add(panel_1);
		
		cboStatus = new JComboBox();
		cboStatus.setModel(new DefaultComboBoxModel(new String[] {"", "em analise", "aprovado", "em andamento", "retirado"}));
		cboStatus.setBounds(10, 101, 153, 22);
		panel_1.add(cboStatus);
		
		txtOs = new JTextField();
		txtOs.setEditable(false);
		txtOs.setColumns(10);
		txtOs.setBounds(10, 30, 106, 20);
		panel_1.add(txtOs);
		
		lblNewLabel_4 = new JLabel("Data");
		lblNewLabel_4.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 11));
		lblNewLabel_4.setBounds(133, 33, 46, 14);
		panel_1.add(lblNewLabel_4);
		
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setColumns(10);
		txtData.setBounds(173, 30, 113, 20);
		panel_1.add(txtData);
		
		lblNewLabel_5 = new JLabel("Status");
		lblNewLabel_5.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 11));
		lblNewLabel_5.setBounds(20, 63, 46, 14);
		panel_1.add(lblNewLabel_5);
		
		chkOrcamento = new JCheckBox("or\u00E7amento");
		chkOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "orçamento";
			}
		});
		chkOrcamento.setBounds(199, 78, 97, 23);
		panel_1.add(chkOrcamento);
		
		chkServico = new JCheckBox("servi\u00E7o");
		chkServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "serviço";
			}
		});
		chkServico.setBounds(199, 101, 97, 23);
		panel_1.add(chkServico);
		
		lblNewLabel_11 = new JLabel("Tipo");
		lblNewLabel_11.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 11));
		lblNewLabel_11.setBounds(173, 58, 46, 14);
		panel_1.add(lblNewLabel_11);
		
		lblNewLabel_3 = new JLabel("Servi\u00E7o");
		lblNewLabel_3.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3.setBounds(10, 267, 75, 20);
		getContentPane().add(lblNewLabel_3);
		
		txtServicos = new JTextField();
		txtServicos.setBounds(85, 267, 197, 20);
		getContentPane().add(txtServicos);
		txtServicos.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("ID");
		lblNewLabel_3_1.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3_1.setBounds(502, 70, 75, 20);
		getContentPane().add(lblNewLabel_3_1);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(542, 70, 170, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarOs();
			}
		});
		btnPesquisar.setBackground(SystemColor.control);
		btnPesquisar.setBorder(null);
		btnPesquisar.setIcon(new ImageIcon(Os.class.getResource("/icones/Pesquisa.png")));
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setBounds(542, 441, 89, 82);
		getContentPane().add(btnPesquisar);
		
		lblNewLabel_6 = new JLabel("Modelo");
		lblNewLabel_6.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_6.setBounds(7, 298, 75, 20);
		getContentPane().add(lblNewLabel_6);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(95, 298, 187, 20);
		getContentPane().add(txtModelo);
		txtModelo.setColumns(10);
		
		lblNewLabel_7 = new JLabel("Marca");
		lblNewLabel_7.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_7.setBounds(7, 329, 75, 20);
		getContentPane().add(lblNewLabel_7);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(89, 333, 193, 20);
		getContentPane().add(txtMarca);
		txtMarca.setColumns(10);
		
		lblNewLabel_8 = new JLabel("Personalizador");
		lblNewLabel_8.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_8.setBounds(7, 368, 148, 20);
		getContentPane().add(lblNewLabel_8);
		
		txtTecnico = new JTextField();
		txtTecnico.setBounds(169, 372, 111, 20);
		getContentPane().add(txtTecnico);
		txtTecnico.setColumns(10);
		
		lblNewLabel_9 = new JLabel("Valor");
		lblNewLabel_9.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_9.setBounds(7, 413, 75, 20);
		getContentPane().add(lblNewLabel_9);
		
		txtValor = new JTextField();
		txtValor.setBounds(92, 417, 190, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);
		
		lblNewLabel_10 = new JLabel("Data de Retirada");
		lblNewLabel_10.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_10.setBounds(435, 214, 196, 20);
		getContentPane().add(lblNewLabel_10);
		
		txtRetirada = new JTextField();
		txtRetirada.setBounds(611, 218, 170, 20);
		getContentPane().add(txtRetirada);
		txtRetirada.setColumns(10);
		
		lblFoto = new JLabel("                                 INSERIR FOTO");
		lblFoto.setBounds(445, 261, 291, 165);
		getContentPane().add(lblFoto);
		
		btnImprimir = new JButton("");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirOs();
			}
		});
		btnImprimir.setEnabled(false);
		btnImprimir.setBorder(null);
		btnImprimir.setBackground(SystemColor.control);
		btnImprimir.setIcon(new ImageIcon(Os.class.getResource("/icones/Imprimir.png")));
		btnImprimir.setToolTipText("Imprimir OS");
		btnImprimir.setBounds(188, 460, 89, 74);
		getContentPane().add(btnImprimir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparBotao();
			}
		});
		
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setBackground(SystemColor.desktop);
		btnLimpar.setBounds(350, 480, 89, 23);
		getContentPane().add(btnLimpar);
		
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
	private JButton btnAdicionar;
	private JTextField txtPesquisar;
	private JButton btnEditar;
	private JPanel panel_1;
	private JComboBox cboStatus;
	private JTextField txtOs;
	private JLabel lblNewLabel_4;
	private JTextField txtData;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_3;
	private JTextField txtServicos;
	private JTextField txtId;
	private JButton btnPesquisar;
	private JButton btnExcluir;
	private JLabel lblNewLabel_6;
	private JTextField txtModelo;
	private JCheckBox chkOrcamento;
	private JCheckBox chkServico;
	private JLabel lblNewLabel_7;
	private JTextField txtMarca;
	private JLabel lblNewLabel_8;
	private JTextField txtTecnico;
	private JLabel lblNewLabel_9;
	private JTextField txtValor;
	private JLabel lblNewLabel_10;
	private JTextField txtRetirada;
	private JLabel lblFoto;
	private JButton btnImprimir;

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
	
	private void pesquisarCliente() {
		String read = "select idcli as ID, nome as clientes from clientes where nome like ?";
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
	// --------------------------------------------------------------------------------------------------------
	private void pesquisarId() {
		int setar = tableClientes.getSelectedRow();
		
		txtId.setText(tableClientes.getModel().getValueAt(setar, 0).toString());
	}
	
	// --------------------------------------------------------------------------------------------------------
	
	private void pesquisarOs() {
		String numOs = JOptionPane.showInputDialog("Numero da OS");
		String read = "select * from tbos where os = " + numOs;
		
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnImprimir.setEnabled(true);
		btnAdicionar.setEnabled(false);
		
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				txtOs.setText(rs.getString(1));
				txtData.setText(rs.getString(2));
				if (rs.getString(3).equals("orçamento")) {
					chkOrcamento.setSelected(true);
					
				} else {
					chkServico.setSelected(true);
				}
				cboStatus.setSelectedItem(rs.getString(4));
				txtModelo.setText(rs.getString(5));
				txtMarca.setText(rs.getString(6));
				txtServicos.setText(rs.getString(7));
				txtTecnico.setText(rs.getString(8));
				txtValor.setText(rs.getString(9));
				txtRetirada.setText(rs.getString(10));
				txtId.setText(rs.getString(11)); 
																														
				
			} else {
					JOptionPane.showMessageDialog(null, "Numero de OS não existe", "Atenção", JOptionPane.ERROR_MESSAGE);
				
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// --------------------------------------------------------------------------------------------------------
	
	
	private void emitirOs() {
	
		System.out.println(tipo);
	}
	
	// --------------------------------------------------------------------------------------------------------
	
	private void adicionarOs(){
		
		if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Status", "Atenção", JOptionPane.ERROR_MESSAGE);
			cboStatus.requestFocus();
	} else if (txtServicos.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o campo Serviços", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtServicos.requestFocus();
	} else if (txtModelo.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o Modelo do Tenis","Atenção", JOptionPane.ERROR_MESSAGE);
		txtModelo.requestFocus();
	} else if (txtMarca.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha a Marca do Tenis, ", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtMarca.requestFocus();
	} else if (txtTecnico.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Informe o personalizador da Ordem de Serviço", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtTecnico.requestFocus();
	} else if (txtValor.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null,"Preencha o Valor do Serviço","Atenção", JOptionPane.ERROR_MESSAGE);
		txtValor.requestFocus();
	} else {
		String create = " insert into tbos (tipo, osstatus, modelo, marca, servico, tecnico, valor,dataret, idcli) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(create);
			
			pst.setString(1, tipo);
			pst.setString(2, cboStatus.getSelectedItem().toString());
			pst.setString(3, txtModelo.getText());
			pst.setString(4, txtMarca.getText());
			pst.setString(5, txtServicos.getText());
			pst.setString(6, txtTecnico.getText());
			pst.setString(7, txtValor.getText());
			pst.setString(8, txtRetirada.getText());
			pst.setString(9, txtId.getText());
			
			int confirma = pst.executeUpdate();
			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "OS adicionado com sucesso", "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
			}
			con.close(); 
			limpar();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
	
	// --------------------------------------------------------------------------------------------------------
	
	private void editarOs(){
		
		if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Status", "Atenção", JOptionPane.ERROR_MESSAGE);
			cboStatus.requestFocus();
	} else if (txtServicos.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o campo Serviços", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtServicos.requestFocus();
	} else if (txtModelo.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha o Modelo do Tenis","Atenção", JOptionPane.ERROR_MESSAGE);
		txtModelo.requestFocus();
	} else if (txtMarca.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Preencha a Marca do Tenis, ", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtMarca.requestFocus();
	} else if (txtTecnico.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null, "Informe o personalizador da Ordem de Serviço", "Atenção", JOptionPane.ERROR_MESSAGE);
		txtTecnico.requestFocus();
	} else if (txtValor.getText().isEmpty()) {
		JOptionPane.showMessageDialog(null,"Preencha o Valor do Serviço","Atenção", JOptionPane.ERROR_MESSAGE);
		txtValor.requestFocus();
	} else {
		String update = "update tbos set tipo = ?, osstatus = ?, modelo = ?, marca = ?, servico = ?, tecnico = ?, valor = ?, dataret = ? where os = ?";
		try {
			
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(update);
			
			pst.setString(1, tipo);
			pst.setString(2, cboStatus.getSelectedItem().toString());
			pst.setString(3, txtModelo.getText());
			pst.setString(4, txtMarca.getText());
			pst.setString(5, txtServicos.getText());
			pst.setString(6, txtTecnico.getText());
			pst.setString(7, txtValor.getText());
			pst.setString(8, txtRetirada.getText());
			pst.setString(9, txtOs.getText());
			
			
			int confirma = pst.executeUpdate();
			if (confirma == 1) {
				JOptionPane.showMessageDialog(null, "Os editado com sucesso", "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
			}
			con.close(); 
			limpar();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
		
	}
	// --------------------------------------------------------------------------------------------------------
	
	private void excluirOs() {
		
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão dessa Ordem de Serviço ?", "Atenção!", JOptionPane.YES_NO_OPTION);
		
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from tbos where os = ?";
			
			try {
				
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				
				pst.setString(1, txtOs.getText());
				int excluir = pst.executeUpdate();
				if(excluir == 1) {
					JOptionPane.showMessageDialog(null, "OS excluida com sucesso!!!", "Atenção", JOptionPane.WARNING_MESSAGE);
				}
				
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	// --------------------------------------------------------------------------------------------------------
	
	  private void imprimirOs() {
	  
		  	Connection con = dao.conectar();
		  int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desta OS?", "Atenção", JOptionPane.YES_NO_OPTION);
	        if (confirma == JOptionPane.YES_OPTION) {
	            try {
	                //HashMap filtro = new HashMap();
	                //filtro.put("os", Integer.parseInt(txtOs.getText()));
	                //JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream("/reports/os.jasper"), filtro, con);
	                //JasperViewer.viewReport(print, false);
	                con.close();
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, e);
	            }
	        }
	  
	  }
	
	// --------------------------------------------------------------------------------------------------------
	
		private void limparBotao() {
			int confirma = JOptionPane.showConfirmDialog(null, "Deseja limpar a tela de OS", "Atenção", JOptionPane.YES_NO_OPTION);
			if(confirma == JOptionPane.YES_OPTION) { 
			txtId.setText(null);
			txtServicos.setText(null);
			txtValor.setText(null);
			txtTecnico.setText(null);
			cboStatus.setSelectedItem(null);
			txtOs.setText(null);
			txtData.setText(null);
			txtModelo.setText(null);
			txtMarca.setText(null);
			txtRetirada.setText(null);
			chkOrcamento.setSelected(false);
			chkServico.setSelected(false);
			btnAdicionar.setEnabled(true);
			btnEditar.setEnabled(false);
			btnExcluir.setEnabled(false);
			btnImprimir.setEnabled(false);
			}
		}
	  
		// --------------------------------------------------------------------------------------------------------
	  
	private void limpar() {
		txtId.setText(null);
		txtServicos.setText(null);
		txtValor.setText(null);
		txtTecnico.setText(null);
		cboStatus.setSelectedItem(null);
		txtOs.setText(null);
		txtData.setText(null);
		txtModelo.setText(null);
		txtMarca.setText(null);
		txtRetirada.setText(null);
		chkOrcamento.setSelected(false);
		chkServico.setSelected(false);
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnImprimir.setEnabled(false);
	}
}
