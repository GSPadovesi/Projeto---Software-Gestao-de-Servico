package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class Usuarios extends JDialog {
	private JLabel lblHora;
	private JLabel lblStatus;
	private JLabel lblData;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtPesquisar;
	private JTextField txtId;
	private JButton btnNewButton;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtUsuario;
	private JLabel lblNewLabel_6;
	private JPasswordField txtSenha;
	private JTable tableUsuarios;
	
	public static void main(String[] args) {
		try {
			Usuarios dialog = new Usuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Usuarios() {
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
		
		lblNewLabel = new JLabel("Funci");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 25));
		lblNewLabel.setBounds(264, 78, 117, 36);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("on\u00E1rio");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.ITALIC, 25));
		lblNewLabel_1.setBounds(344, 86, 131, 20);
		getContentPane().add(lblNewLabel_1);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarUsuario();
			}
		});
		txtPesquisar.setColumns(10);
		txtPesquisar.setBounds(104, 125, 174, 36);
		getContentPane().add(txtPesquisar);
		
		JLabel lblNewLabel_2 = new JLabel("Pesquisar");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 120, 111, 25);
		getContentPane().add(lblNewLabel_2);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(120, 204, 206, 36);
		getContentPane().add(txtId);
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_3.setBounds(42, 206, 88, 25);
		getContentPane().add(lblNewLabel_3);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Usuarios.class.getResource("/icones/Hyper.png")));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.setBounds(675, 332, 165, 129);
		getContentPane().add(btnNewButton);
		
		lblNewLabel_4 = new JLabel("Senha");
		lblNewLabel_4.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_4.setBounds(42, 292, 117, 20);
		getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Usu\u00E1rio ");
		lblNewLabel_5.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_5.setBounds(368, 206, 117, 20);
		getContentPane().add(lblNewLabel_5);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(495, 206, 229, 36);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblNewLabel_6 = new JLabel("Perfil ");
		lblNewLabel_6.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		lblNewLabel_6.setBounds(378, 292, 117, 20);
		getContentPane().add(lblNewLabel_6);
		
		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setBackground(SystemColor.control);
		btnAdicionar.setBorder(null);
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/icones/AdicionarFunc.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setBounds(223, 366, 103, 84);
		getContentPane().add(btnAdicionar);
		
		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkPass.isSelected()) {
					editarUsuario();
					
				}else {
					editarUsuarioPersonalizado();
				}
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBackground(SystemColor.control);
		btnEditar.setBorder(null);
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/icones/EditarFunc.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(104, 366, 114, 84);
		getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(SystemColor.control);
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/icones/ExcluirFunc.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBorder(null);
		btnExcluir.setBounds(10, 366, 94, 84);
		getContentPane().add(btnExcluir);
		
		
		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] {"", "administrador", "suporte", "vendedor ", "dba"}));
		cboPerfil.setBounds(493, 285, 231, 36);
		getContentPane().add(cboPerfil);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(120, 292, 206, 36);
		getContentPane().add(txtSenha);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(454, 78, 376, 81);
		getContentPane().add(desktopPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 356, 59);
		desktopPane.add(scrollPane);
		
		tableUsuarios = new JTable();
		tableUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
				setarSenha();
			}
		});
		scrollPane.setViewportView(tableUsuarios);
		
		chkPass = new JCheckBox("Confirmar altera\u00E7\u00E3o de senha ");
		chkPass.setVisible(false);
		chkPass.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 15));
		chkPass.setBounds(368, 386, 301, 23);
		getContentPane().add(chkPass);
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
	private JComboBox cboPerfil;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JCheckBox chkSenha;
	private JCheckBox chkPass;
	private void status() {
		
		
		
		try {
			Connection con = dao.conectar();
			
			System.out.println("Banco Conectado");
			
			if(con == null) {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/desconectou.png")));
			}else {
				lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/conectou.png")));
			}
			con.close();
		} catch ( com.mysql.cj.jdbc.exceptions.CommunicationsException ex) {
			JOptionPane.showMessageDialog(null, "Servidor Indisponivel");
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	private void pesquisarUsuario() {
		String read = "select id as Id, usuario as Usuario, perfil as Perfil, senha as Senha from usuarios where usuario like ?";
		try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				pst.setString(1, txtPesquisar.getText() + "%");
				ResultSet rs = pst.executeQuery();
				tableUsuarios.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void adicionarUsuario() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Usuario", "Atenção!", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		} else if(txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senha", "Atenção!", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		} else if(cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Perfil", "Atenção!", JOptionPane.ERROR_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			String create = "insert into usuarios(usuario, senha, perfil) values (?, md5(?), ?)";
			
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtSenha.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());					
				
				int confirma = pst.executeUpdate();
				if(confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuário criado com sucesso", "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login ja existente \n Coloque outro login", "Atenção",
						JOptionPane.WARNING_MESSAGE);
				txtUsuario.setText(null);
				txtUsuario.requestFocus();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	private void editarUsuario() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Usuario", "Atenção!", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senhs", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		}else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Perfil", "Atenção", JOptionPane.ERROR_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			String update = "update usuarios set usuario = ?, senha = md5(?), perfil = ? where id = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtSenha.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtId.getText());
				int confirma = pst.executeUpdate();
				if(confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuário editado com sucesso", "MENSAGEM", JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	
	private void editarUsuarioPersonalizado() {
		if(txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Usuário", "Atenção", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Perfil", "Atenção", JOptionPane.ERROR_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			String update = "update usuarios set usuario = ?, perfil = ? where id = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, cboPerfil.getSelectedItem().toString());
				pst.setString(3, txtId.getText());
				int confirma = pst.executeUpdate();
				if(confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuário editado com sucesso", "Atenção", JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	private void excluirUsuario(){
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir esse usuário ?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
			if(confirma == JOptionPane.YES_OPTION) {
				String delete = "delete from usuarios where id = ?";
				try {
					Connection con = dao.conectar();
					PreparedStatement pst = con.prepareStatement(delete);
					pst.setString(1, txtId.getText());
					int excluir = pst.executeUpdate();
					if(excluir == 1) {
						JOptionPane.showMessageDialog(null, "Usuario Excluido com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
					}
					pst.executeUpdate();
					con.close();
					limpar();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		
	}
		
	
	private void setarSenha() {
		String read2 = "select senha from usuarios where id=?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtId.getText());
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				txtSenha.setText(rs.getString(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void setarCampos() {
		
		int setar = tableUsuarios.getSelectedRow();
		 
		txtId.setText(tableUsuarios.getModel().getValueAt(setar, 0).toString());
		txtUsuario.setText(tableUsuarios.getModel().getValueAt(setar, 1).toString());
		cboPerfil.setSelectedItem(tableUsuarios.getModel().getValueAt(setar, 2).toString());
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
		chkPass.setVisible(true);
		
	}
		
	private void limpar() {
		txtId.setText(null);
		txtUsuario.setText(null);
		txtSenha.setText(null);
		txtPesquisar.setText(null);
		cboPerfil.setSelectedItem(null);
		((DefaultTableModel) tableUsuarios.getModel()).setRowCount(0);
		
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		chkPass.setVisible(false);	
	}
}


// Falta editar Usuario, editar personalizado{ e excluir usuario