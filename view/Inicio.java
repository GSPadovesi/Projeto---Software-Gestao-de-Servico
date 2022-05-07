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


public class Inicio extends JFrame{
	private JLabel lblData;
	private JLabel lblHora;
	private JLabel lblStatus;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public Inicio() {
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
		
		setTitle("HYPER CUSTOM");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/icones/icone.png")));
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
		lblStatus.setIcon(new ImageIcon(Inicio.class.getResource("/icones/desconectou.png")));
		lblStatus.setToolTipText("Conex\u00E3o");
		lblStatus.setBounds(791, 11, 86, 56);
		panel.add(lblStatus);
		
		JLabel lblNewLabel = new JLabel("HYPER ");
		lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 32));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(286, 115, 149, 36);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Personaliza\u00E7\u00E3o  de T\u00EAnis");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(342, 168, 212, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnProsseguir = new JButton("Prosseguir");
		btnProsseguir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProsseguir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnProsseguir.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, SystemColor.windowText, SystemColor.textText, SystemColor.textText, SystemColor.menuText));
		btnProsseguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnProsseguir.setForeground(Color.WHITE);
		btnProsseguir.setBackground(Color.BLACK);
		btnProsseguir.setToolTipText("Seguinte");
		btnProsseguir.setBounds(331, 379, 185, 37);
		getContentPane().add(btnProsseguir);
		
		JLabel lblNewLabel_2 = new JLabel("CUSTOM");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.PLAIN, 32));
		lblNewLabel_2.setBounds(429, 119, 251, 29);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Inicio.class.getResource("/icones/Hyper.png")));
		lblNewLabel_3.setBounds(353, 193, 201, 162);
		getContentPane().add(lblNewLabel_3);
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
}
