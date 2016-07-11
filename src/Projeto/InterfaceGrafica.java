package Projeto;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InterfaceGrafica extends JFrame{

	JPanel painelPrincipal = new JPanel();
	JButton b00 = new JButton();
	JButton b01 = new JButton();
	JButton b02 = new JButton();
	JButton b10 = new JButton();
	JButton b11 = new JButton();
	JButton b12 = new JButton();
	JButton b20 = new JButton();
	JButton b21 = new JButton();
	JButton b22 = new JButton();
	
	
	public InterfaceGrafica(){
		
		setSize(630, 700);
		setResizable(false);
		setLayout(null);
		
		
		b00.setLocation(100, 150);
		b01.setLocation(250, 150);
		b02.setLocation(400, 150);
		b10.setLocation(100, 300);
		b11.setLocation(250, 300);
		b12.setLocation(400, 300);
		b20.setLocation(100, 450);
		b21.setLocation(250, 450);
		b22.setLocation(400, 450);
		
		
		b00.setSize(130, 130);
		b01.setSize(130, 130);
		b02.setSize(130, 130);
		b10.setSize(130, 130);
		b11.setSize(130, 130);
		b12.setSize(130, 130);
		b20.setSize(130, 130);
		b21.setSize(130, 130);
		b22.setSize(130, 130);
		
		//b00.setIcon(new ImageIcon("imagens/bola.jpg"));
		
		add(b00);add(b01);add(b02);add(b10);add(b11);add(b12);add(b20);add(b21);add(b22);
		
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String [] args){
		new InterfaceGrafica();
	}
	
}
