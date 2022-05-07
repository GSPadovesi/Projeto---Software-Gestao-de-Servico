
package view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import model.DAO;

import java.awt.Cursor;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;


public class Login extends JFrame{
	
	private JLabel lblData;
	private JLabel lblHora;
	private JLabel lblStatus;
	private JTextField txtLogin;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public Login() {
		
		setResizable(false);
		setSize(new Dimension(900, 550));
		

		
		setName("frame100");
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 63));
		getContentPane().setBackground(Color.WHITE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				setarData();
				status();
			}

		});
		
		setTitle("HYPER CUSTOM");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/icones/icone.png")));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 931, 67);
		panel.setBackground(Color.GRAY);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblData = new JLabel("");
		lblData.setBounds(38, 11, 294, 29);
		panel.add(lblData);
		
		lblHora = new JLabel("");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblHora.setBounds(382, 11, 132, 29);
		panel.add(lblHora);
		
		lblStatus = new JLabel("");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/icones/desconectou.png")));
		lblStatus.setToolTipText("Conex\u00E3o");
		lblStatus.setBounds(791, 11, 86, 56);
		panel.add(lblStatus);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(349, 121, 143, 36);
		lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 32));
		lblNewLabel.setForeground(Color.BLACK);
		getContentPane().add(lblNewLabel);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(331, 379, 185, 37);
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
	
		btnLogin.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.windowText, SystemColor.textText, SystemColor.textText, SystemColor.menuText));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setToolTipText("Seguinte");
		getContentPane().add(btnLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(279, 193, 64, 26);
		lblNewLabel_1.setFont(new Font("Rockwell", Font.ITALIC, 15));
		getContentPane().add(lblNewLabel_1);
	
		
		txtLogin = new JTextField();
		txtLogin.setBounds(278, 224, 289, 26);
		txtLogin.setColumns(10);
		getContentPane().add(txtLogin);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setBounds(279, 271, 64, 26);
		lblNewLabel_1_1.setFont(new Font("Rockwell", Font.ITALIC, 15));
		getContentPane().add(lblNewLabel_1_1);
		
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(279, 312, 288, 26);
		getContentPane().add(txtSenha);
		
		
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
	private JPasswordField txtSenha;
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
	
	private void logar() {
		if(txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo usuário", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
			txtLogin.requestFocus();
		} else if(txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo senha", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
			txtSenha.requestFocus();
		} else {
			try {
				String read = "select * from usuarios where usuario = ? and senha = md5(?)";
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read);
				
				pst.setString(1, txtLogin.getText());
				pst.setString(2, txtSenha.getText());
				
				ResultSet rs = pst.executeQuery();
				
				if (rs.next()) {
					String perfil = rs.getString(4);
					System.out.println(perfil);
					
					if (perfil.equals("administrador")) {
						Principal principal = new Principal();
						principal.setVisible(true);
						
						principal.btnCadFuncionario.setEnabled(true);
						
				} else {
					Principal principal = new Principal();
					principal.setVisible(true);
					
					principal.btnCadFuncionario.setEnabled(false);
					
					//dispose();
				}
					this.dispose();	
			} else {
				JOptionPane.showMessageDialog(null, "Campo Login ou Senha invalidos", "Atenção",
						JOptionPane.WARNING_MESSAGE);
			}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}	
}
