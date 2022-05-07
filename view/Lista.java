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

public class Lista extends JDialog {
	private JLabel lblHora;
	private JLabel lblStatus;
	private JLabel lblData;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JTable tableClientes;

	public static void main(String[] args) {
		try {
			Lista dialog = new Lista();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Lista() {
		setTitle("HYPER CUSTOM - FUNCION\u00C1RIOS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Lista.class.getResource("/icones/icone.png")));
		getContentPane().setBounds(new Rectangle(100, 100, 856, 500));
		setBounds(new Rectangle(100, 100, 856, 500));
		setModal(true);
		getContentPane().setLayout(null);

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

		lblNewLabel = new JLabel("Lista d");
		lblNewLabel.setBounds(246, 128, 117, 36);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Arial Black", Font.ITALIC, 25));
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("e Clientes");
		lblNewLabel_1.setBounds(342, 136, 160, 20);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.ITALIC, 25));
		getContentPane().add(lblNewLabel_1);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 195, 820, 255);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 800, 233);
		desktopPane.add(scrollPane);

		tableClientes = new JTable();
		tableClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// setarCampos();
			}
		});
		scrollPane.setViewportView(tableClientes);

		btnNewButton = new JButton("");
		btnNewButton.setBounds(699, 74, 131, 110);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Clientes.class.getResource("/icones/Hyper.png")));
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(SystemColor.control);

		lblNewLabel_2 = new JLabel("Pesquisa");
		lblNewLabel_2.setBounds(10, 88, 123, 20);
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei", Font.ITALIC, 20));
		getContentPane().add(lblNewLabel_2);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(113, 92, 160, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

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
	private JLabel lblNewLabel_2;
	private JTextField txtPesquisar;

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
}