package grupo08sistemacaja;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;

public class ventana2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnRegresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana2 frame = new ventana2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private ventana1 v1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField txtCodAuto;
	private JTextField txtIDtrabajador;
	private JButton btnRetirar;
	private JScrollPane scrollPane;
	private JTextArea txtSRetiro;
	
	
	public ventana2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 331);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("RETIRO ");
			lblNewLabel.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
			lblNewLabel.setBounds(173, 11, 84, 22);
			contentPane.add(lblNewLabel);
		}
		{
			btnRegresar = new JButton("Regresar");
			btnRegresar.setBackground(new Color(169, 169, 169));
			btnRegresar.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
			btnRegresar.addActionListener(this);
			btnRegresar.setBounds(286, 261, 89, 23);
			contentPane.add(btnRegresar);
		}
		{
			lblNewLabel_1 = new JLabel("Codigo autorizacion :");
			lblNewLabel_1.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			lblNewLabel_1.setBounds(3, 46, 152, 24);
			contentPane.add(lblNewLabel_1);
		}
		{
			lblNewLabel_2 = new JLabel("ID trabajador :");
			lblNewLabel_2.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			lblNewLabel_2.setBounds(48, 87, 107, 14);
			contentPane.add(lblNewLabel_2);
		}
		{
			txtCodAuto = new JTextField();
			txtCodAuto.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			txtCodAuto.setBounds(158, 44, 107, 25);
			contentPane.add(txtCodAuto);
			txtCodAuto.setColumns(10);
		}
		{
			txtIDtrabajador = new JTextField();
			txtIDtrabajador.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			txtIDtrabajador.setColumns(10);
			txtIDtrabajador.setBounds(158, 79, 107, 25);
			contentPane.add(txtIDtrabajador);
		}
		{
			btnRetirar = new JButton("Retirar");
			btnRetirar.setBackground(new Color(169, 169, 169));
			btnRetirar.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			btnRetirar.addActionListener(this);
			btnRetirar.setBounds(286, 44, 89, 62);
			contentPane.add(btnRetirar);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(48, 145, 326, 105);
			contentPane.add(scrollPane);
			{
				txtSRetiro = new JTextArea();
				txtSRetiro.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
				scrollPane.setViewportView(txtSRetiro);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRetirar) {
			do_btnRetirar_actionPerformed(e);
		}
		if (e.getSource() == btnRegresar) {
			do_btnRegresar_actionPerformed(e);
		}
	}
	public void setV1(ventana1 v1) {
		this.v1=v1;
	}
	protected void do_btnRegresar_actionPerformed(ActionEvent e) {
		
		v1.setVisible(true);
		this.setVisible(false);
	}
	protected void do_btnRetirar_actionPerformed(ActionEvent e) {
		  String codTexto = txtCodAuto.getText().trim();
		    String idTexto = txtIDtrabajador.getText().trim();
		    if (codTexto.isEmpty() || idTexto.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "Complete todos los campos.");
		        return;
		    }
		    int codigo, id;
		    try {
		    	 codigo = Integer.parseInt(codTexto);
		         id = Integer.parseInt(idTexto);
		    }catch(NumberFormatException ex) {
		    	JOptionPane.showMessageDialog(this,"Código de autorización e ID deben ser números enteros.");
		    	return;
		    }
		    if (codigo != 1140) {
		        JOptionPane.showMessageDialog(this, "Código incorrecto. Ingrese nuevamente el código.");
		        return;
		    }
		    String nombre = "";
		    String turno = "";

		    if (id == 258411) {
		        nombre = "Roberto";
		        turno = "Día";
		    } else if (id == 257961) {
		        nombre = "María";
		        turno = "Noche";
		    } else {
		        JOptionPane.showMessageDialog(this, "ID incorrecto. Ingrese nuevamente el ID.");
		        return;
		    }
		if(v1.getSistema().realizarretiro()) {
			 LocalDate fecha = LocalDate.now();
		        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        txtSRetiro.setText("RETIRO AUTORIZADO\n");
		        txtSRetiro.append("Supervisor: " + nombre + "\n");
		        txtSRetiro.append("Turno: " + turno + "\n");
		        txtSRetiro.append("Fecha: " + fecha.format(formato) + "\n");
		        txtSRetiro.append("Monto actual: " + v1.getSistema().Montoactual() + " soles");
			
			v1.actualizartextocaja();
		    
		}else {
			txtSRetiro.setText("No se puede autorizar el retiro. El monto es insuficiente.");
			
		}
	}
}
