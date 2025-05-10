package grupo08sistemacaja;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class ventana1 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JButton btnRetiro;
	private JLabel lblNewLabel_1;
	private JTextField txtMonto;
	private JScrollPane scrollPane;
	private JTextArea txtS;
	private JButton btndepositar;
	private sistemacaja sistema;
	private JLabel lblNewLabel_2;
	private JTextField txtvalorprod;
	private JLabel lblNewLabel_3;
	private JTextField txtvuelto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana1 frame = new ventana1();
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
	
	
	
	public ventana1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 546);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			lblNewLabel = new JLabel("SISTEMA MONTO-RETIRO");
			lblNewLabel.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
			lblNewLabel.setBounds(147, 24, 267, 23);
			contentPane.add(lblNewLabel);
		}
		{
			btnRetiro = new JButton("Retiro");
			btnRetiro.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			btnRetiro.setBackground(new Color(169, 169, 169));
			btnRetiro.addActionListener(this);
			btnRetiro.setBounds(424, 79, 106, 46);
			contentPane.add(btnRetiro);
		}
		{
			lblNewLabel_1 = new JLabel("MONTO INGRESADO : ");
			lblNewLabel_1.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			lblNewLabel_1.setBounds(10, 82, 153, 14);
			contentPane.add(lblNewLabel_1);
		}
		{
			txtMonto = new JTextField();
			txtMonto.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			txtMonto.setBounds(173, 80, 101, 20);
			contentPane.add(txtMonto);
			txtMonto.setColumns(10);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(58, 236, 410, 224);
			contentPane.add(scrollPane);
			{
				txtS = new JTextArea();
				txtS.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
				scrollPane.setViewportView(txtS);
			}
		}
		{
			btndepositar = new JButton("Depositar");
			btndepositar.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			btndepositar.setForeground(new Color(0, 0, 0));
			btndepositar.setBackground(new Color(169, 169, 169));
			btndepositar.addActionListener(this);
			btndepositar.setBounds(310, 79, 104, 46);
			contentPane.add(btndepositar);
		}
		{
			lblNewLabel_2 = new JLabel("TOTAL PRODUCTO :");
			lblNewLabel_2.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			lblNewLabel_2.setBounds(20, 134, 143, 14);
			contentPane.add(lblNewLabel_2);
		}
		{
			txtvalorprod = new JTextField();
			txtvalorprod.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			txtvalorprod.setColumns(10);
			txtvalorprod.setBounds(173, 131, 101, 20);
			contentPane.add(txtvalorprod);
		}
		{
			lblNewLabel_3 = new JLabel("VUELTO: ");
			lblNewLabel_3.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			lblNewLabel_3.setBounds(96, 176, 63, 14);
			contentPane.add(lblNewLabel_3);
		}
		{
			txtvuelto = new JTextField();
			txtvuelto.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
			txtvuelto.setEditable(false);
			txtvuelto.setColumns(10);
			txtvuelto.setBounds(173, 174, 101, 20);
			contentPane.add(txtvuelto);
		}
		 sistema = new sistemacaja();
		
		txtS.append("\nMonto inicial: "+sistema.getMontoInicial() + " soles\n");
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btndepositar) {
			do_btndepositar_actionPerformed(e);
		}
		if (e.getSource() == btnRetiro) {
			do_btnRetiro_actionPerformed(e);
		}
	}
	protected void do_btnRetiro_actionPerformed(ActionEvent e) {
		ventana2 v2 = new ventana2();
		v2.setV1(this);
		v2.setVisible(true);
		this.setVisible(false);
	}
	protected void do_btndepositar_actionPerformed(ActionEvent e) {
		
		realizarDeposito();
			
		
		
	}
	private void realizarDeposito() {
        try {
            double monto = Double.parseDouble(txtMonto.getText());
            double valorProducto = Double.parseDouble(txtvalorprod.getText());
            if(monto <=0) {
            	JOptionPane.showMessageDialog(this,"Ingrese un monto mayor a 0.");
            }else {
            	if (monto < valorProducto) {
                    JOptionPane.showMessageDialog(this, "El monto ingresado debe ser igual o mayor al valor del producto.");
                    return;
                }
            	double vuelto = monto - valorProducto;
                txtvuelto.setText(String.valueOf(vuelto));
                if (sistema.agregarmonto(valorProducto)) {
                    txtS.setText("");  
                    txtS.append("Monto inicial: " + sistema.getMontoInicial() + " soles\n");
                    txtS.append("Monto actual: " + sistema.Montoactual() + " soles\n");
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede agregar monto. El límite es de 1000 soles.");
                }
           
            
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese montos válidos.");
        }
    }
	
	
	public void actualizartextocaja() {
		txtS.setText("");
		txtS.append("Monto inicial: " + sistema.getMontoInicial() + " soles\n");
        txtS.append("Monto actual: " + sistema.Montoactual() + " soles\n");
		
	}
	public sistemacaja getSistema() {
	    return sistema;
	}
}
