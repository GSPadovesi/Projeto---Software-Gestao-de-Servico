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
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Principal extends JFrame{
	private JLabel lblData;
	private JLabel lblHora;
	private JLabel lblStatus;
	public JPanel contentPane;
	public JButton btnCadFuncionario;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public Principal() {
		setResizable(false);
		setSize(new Dimension(900, 550));
		setName("frame100");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		setTitle("HYPER CUSTOM - PRINCIPAL");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/icones/icone.png")));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 931, 67);
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
		lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/icones/desconectou.png")));
		lblStatus.setToolTipText("Conex\u00E3o");
		lblStatus.setBounds(791, 11, 86, 56);
		panel.add(lblStatus);
		
		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setBorder(null);
		btnSobre.setBackground(Color.WHITE);
		btnSobre.setIcon(new ImageIcon(Principal.class.getResource("/icones/HyperPrincipal2.png")));
		btnSobre.setBounds(289, 176, 277, 251);
		getContentPane().add(btnSobre);
		
		JLabel lblNewLabel = new JLabel("HYPER ");
		lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 25));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(317, 78, 149, 36);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Personaliza\u00E7\u00E3o  de T\u00EAnis");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(341, 130, 212, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CUSTOM");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(424, 82, 251, 29);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cadastrar Funcion\u00E1rio");
		lblNewLabel_3.setBounds(107, 189, 157, 14);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnOs = new JButton("");
		btnOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Os os = new Os();
				os.setVisible(true);
			}
		});
		btnOs.setIcon(new ImageIcon(Principal.class.getResource("/icones/OrdemDeServico.png")));
		btnOs.setForeground(Color.WHITE);
		btnOs.setBorder(null);
		btnOs.setBackground(Color.WHITE);
		btnOs.setBounds(102, 366, 122, 102);
		getContentPane().add(btnOs);
		
		JLabel lblNewLabel_4 = new JLabel("Ordem de Servi\u00E7o ");
		lblNewLabel_4.setBounds(107, 344, 149, 14);
		getContentPane().add(lblNewLabel_4);
		
		
		
		JLabel lblNewLabel_5 = new JLabel("Cadastrar Cliente");
		lblNewLabel_5.setBounds(672, 189, 158, 14);
		getContentPane().add(lblNewLabel_5);
		
		JButton btnListarClientes = new JButton("");
		btnListarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lista lista = new Lista();
				lista.setVisible(true);
			}
		});
		btnListarClientes.setIcon(new ImageIcon(Principal.class.getResource("/icones/ListarCli.png")));
		btnListarClientes.setForeground(Color.WHITE);
		btnListarClientes.setBorder(null);
		btnListarClientes.setBackground(Color.WHITE);
		btnListarClientes.setBounds(672, 366, 122, 102);
		getContentPane().add(btnListarClientes);
		
		JLabel lblNewLabel_6 = new JLabel("Lista de Clientes");
		lblNewLabel_6.setBounds(672, 344, 122, 14);
		getContentPane().add(lblNewLabel_6);
		
		JButton btnCadastrarCliente = new JButton("");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);;
			}
		});
		btnCadastrarCliente.setIcon(new ImageIcon(Principal.class.getResource("/icones/CadastrarCliente.png")));
		btnCadastrarCliente.setBackground(Color.WHITE);
		btnCadastrarCliente.setBorder(null);
		btnCadastrarCliente.setToolTipText("Cadastrar Cliente");
		btnCadastrarCliente.setBounds(647, 214, 149, 119);
		getContentPane().add(btnCadastrarCliente);
		
		btnCadFuncionario = new JButton("");
		btnCadFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios usuario = new Usuarios();
				usuario.setVisible(true);
			}
		});
		btnCadFuncionario.setIcon(new ImageIcon(Principal.class.getResource("/icones/CadastrarFunc.png")));
		btnCadFuncionario.setBackground(Color.WHITE);
		btnCadFuncionario.setBorder(null);
		btnCadFuncionario.setToolTipText("CadastrarFuncionario");
		btnCadFuncionario.setBounds(87, 214, 137, 102);
		getContentPane().add(btnCadFuncionario);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sair();
			}
		});
		btnSair.setToolTipText("Seguinte");
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSair.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.windowText, SystemColor.textText, SystemColor.textText, SystemColor.menuText));
		btnSair.setBackground(Color.BLACK);
		btnSair.setBounds(341, 463, 185, 37);
		getContentPane().add(btnSair);
		
		
		
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
	private void status() {
		
		DAO dao = new DAO();
		
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
	
	private void Sair() {
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja Sair", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			Inicio inicio = new Inicio();
			inicio.setVisible(true);
			this.dispose();
		}
	}
}
