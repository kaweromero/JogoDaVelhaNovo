package Projeto;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class JogoDaVelha extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	static JPanel jPanelTabuleiro = null;
	private JLabel jbtTitulo = null;
	//private JLabel jlbSubTitulo = null;

	private int[][] winCombinations = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 },
			{ 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 }, { 1, 5, 9 }, { 3, 5, 7 } };

	private static JButton[] buttons = new JButton[10];
	private int counts = 0;
	private boolean wins = false;
	String result;
	// private String winner;
	private String player = null;
	private static int derrot = 0;
	private static int vitoria = 0;
	private static int empate = 0;
	private static int vito = 0;
	private static int derr = 0;
	private int[] copie_tabela = new int[10];
	

	private JPanel getJPanelTabuleiro() {
		if (jPanelTabuleiro == null) {
			jPanelTabuleiro = new JPanel();
			jPanelTabuleiro.setLayout(null);
			jPanelTabuleiro.setBounds(new Rectangle(167, 101, 500, 500));
			// jPanelTabuleiro.setPreferredSize(new Dimension(500, 500));
			jPanelTabuleiro.setLayout(new GridLayout(3, 3));
			jPanelTabuleiro.setVisible(true);
			jPanelTabuleiro.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createBevelBorder(BevelBorder.LOWERED),
					"Tabuleiro", TitledBorder.CENTER,
					TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD,
							18), new Color(30, 144, 255)));
		}
		return jPanelTabuleiro;
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JogoDaVelha thisClass = new JogoDaVelha();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);

			}
		});
	}

	public JogoDaVelha() {
		
		super();
		initialize();

		for (int i = 1; i <= 9; i++) {
			buttons[i] = new JButton();
			buttons[i].setFont(new Font("Arial", Font.BOLD, 72));
			buttons[i].setText("");
			buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			jPanelTabuleiro.add(buttons[i]);
			buttons[i].addActionListener(this);
		}

	}

	private void initialize() {
		this.setSize(806, 668);
		this.setContentPane(getJContentPane());
		this.setTitle("Jogo Da Velha @Kawe");
		// Tamanho fixo do programa, sem alteração
		this.setResizable(false);
		// Deixa o programa no meio da tela, centralizado
		this.setLocationRelativeTo(null);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {

			jbtTitulo = new JLabel();
			jbtTitulo.setBounds(new Rectangle(88, 5, 643, 40));
			jbtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			jbtTitulo.setFont(new Font("Arial", Font.BOLD, 38));
			jbtTitulo.setForeground(new Color(0, 0, 204));
			jbtTitulo.setText("Jogo Da Velha");

			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJPanelTabuleiro(), null);
			jContentPane.add(jbtTitulo, null);
			//jContentPane.add(jlbSubTitulo, null);
			
			
		}
		return jContentPane;
	}

	public static void reset_score(ActionEvent evt) {
		derrot = (vitoria = empate = 0);
		vito = (derr = 0);
	}

	public void actionPerformed(ActionEvent a) {
		JButton pressedButton = (JButton) a.getSource();

		if (pressedButton.getText().equals("")) {
			pressedButton.setText("X");
			pressedButton.setForeground(Color.blue);
			 copie_tabela = copie_tabela();
			counts += 1;
			checkWin();

			int poz_max = min_max(copie_tabela);

			buttons[poz_max].setText("O");
			buttons[poz_max].setForeground(Color.red);
			counts += 1;
			checkWin();
			
		} else {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Escolha outro movimento!!!!","Jogo Da Velha ", JOptionPane.ERROR_MESSAGE);

		}
	}

	public int[] poz_lib_cp(int[] cop) {
		
		int bb = 0;
		int[] poz_0 = new int[10];
		for (int b = 1; b <= 9; b++) {
			if (cop[b] == 0) {
				bb++;
				poz_0[bb] = b;
			}
		}
		return poz_0;
	}

	public int[] copie_tabela() {
		int[] tabe = new int[10];

		for (int cas = 1; cas <= 9; cas++)
			if (buttons[cas].getText().equals("X")) {
				tabe[cas] = 1;
			} else if (buttons[cas].getText().equals("O")) {
				tabe[cas] = 2;
			} else {
				tabe[cas] = 0;
			}
		return tabe;
	}

	
	public int table_winner(int[] cc) {
		
		int rez = -1;
		int zero = 0;
		String winn = "";
		boolean game_over = false;
		
		for (int i = 0; i <= 7; i++) {
			
			if ((cc[winCombinations[i][0]] == 1)
					&& (cc[winCombinations[i][0]] == cc[winCombinations[i][1]])
					&& (cc[winCombinations[i][1]] == cc[winCombinations[i][2]])
					&& (cc[winCombinations[i][0]] != 0)) {
				game_over = true;
				winn = "X";
				rez = -1000000;
			}

			if ((cc[winCombinations[i][0]] != 2)
					|| (cc[winCombinations[i][0]] != cc[winCombinations[i][1]])
					|| (cc[winCombinations[i][1]] != cc[winCombinations[i][2]])
					|| (cc[winCombinations[i][0]] == 0)) {
				continue;
			}
			game_over = true;
			winn = "O";
			rez = 1000000;
		}

		for (int c = 1; c <= 9; c++) {
			if (cc[c] != 0) {
				zero++;
			}
		}

		if ((zero >= 9) && (!game_over)) {
			winn = "Empate";
			rez = 0;
		}

		return rez;
	}

	public void display_winner(int vit, int derro, int empa, String tex) {
		if (JOptionPane.showConfirmDialog(null, vit + " Vitórias  ," + empa
				+ "  Empates ," + derro + "  Derrotas\n" + "Jogar de novo?",
				tex, 0) != 0) {

			// zerando os botões
			for (int i = 1; i <= 9; i++) {
				buttons[i].setText("");
				 copie_tabela[i] = 0;
			}// fim do for zerando os botões

		} else
			newgame();
	}

	public void newgame() {
		counts = 0;
		wins = false;
		result = "";

		for (int i = 1; i <= 9; i++) {
			buttons[i].setText("");
			 copie_tabela[i] = 0;
		}
	}

	// Metodo do IA
	public int min_max(int[] board) {
		int bestval = -1000000;
		int index = 0;
		int[] best_move = new int[10];
		int[] p_lib = new int[10];
		p_lib = poz_lib_cp(board);
		int nr_poz = 0;

		for (int cc = 1; cc <= 9; cc++) {
			if (p_lib[cc] > 0) {
				nr_poz++;
			}
		}

		int nr = 1;
		
		while (nr <= nr_poz) {
			
			int mut = p_lib[nr];
			board[mut] = 2;

			int val = MinMove(board);
			
			if (val > bestval) {
				System.out.println(bestval);
				bestval = val;
				System.out.println(bestval);

				index = 0;
				best_move[index] = mut;
			} else if (val == bestval) {
				index++;
				best_move[index] = mut;
			}
			board[mut] = 0;
			nr++;
		}

		int r = 0;
		
		if (index > 0) {
			Random x = new Random();
			r = x.nextInt(index);
		}
		return best_move[r];
	}

	public int MinMove(int[] board) {
		int pos_value = table_winner(board);

		if (pos_value != -1) {
			return pos_value;
		}

		int best_val = 1000000;
		int[] p_lib = new int[10];
		p_lib = poz_lib_cp(board);
		int nr_poz = 0;
		
		for (int cc = 1; cc <= 9; cc++) {
			if (p_lib[cc] > 0) {
				nr_poz++;
			}
		}
		
		int nr = 1;
		while (nr <= nr_poz) {
			
			int mut = p_lib[nr];
			board[mut] = 1;
			int val = MaxMove(board);
			
			if (val < best_val) {
				//System.out.println(bestval);

				best_val = val;
				//System.out.println(bestval);
			}
			board[mut] = 0;
			nr++;
		}
		return best_val;
	}

	public int MaxMove(int[] board) {
		int pos_value = table_winner(board);

		if (pos_value != -1) {
			return pos_value;
		}
		
		int best_val = -1000000;
		int[] p_lib = new int[10];
		p_lib = poz_lib_cp(board);
		int nr_poz = 0;
		
		for (int cc = 1; cc <= 9; cc++) {
			if (p_lib[cc] > 0) {
				nr_poz++;
			}
		}
		int nr = 1;

		while (nr <= nr_poz) {
			
			int mut = p_lib[nr];
			board[mut] = 2;
			int val = MinMove(board);
			
			if (val > best_val) {
				best_val = val;
				//System.out.println(bestval);
			}

			board[mut] = 0;
			nr++;
		}
		return best_val;
	}

	// Metodo Checar vencedor
	public void checkWin() {
		int nr = 0;
		wins = false;

		for (int i = 0; i <= 7; i++) {
			if ((buttons[winCombinations[i][0]].getText().equals("X"))&& (buttons[winCombinations[i][0]].getText().equals(buttons[winCombinations[i][1]].getText()))
					&& (buttons[winCombinations[i][1]].getText().equals(buttons[winCombinations[i][2]].getText()))&& (!buttons[winCombinations[i][0]].getText().equals(""))) {
				player = "X";
				wins = true;
			}

			if ((!buttons[winCombinations[i][0]].getText().equals("O"))
					|| (!buttons[winCombinations[i][0]].getText().equals(
							buttons[winCombinations[i][1]].getText()))
					|| (!buttons[winCombinations[i][1]].getText().equals(
							buttons[winCombinations[i][2]].getText()))
					|| (buttons[winCombinations[i][0]].getText().equals(""))) {
				continue;
			}
			player = "O";
			wins = true;
		}

		for (int c = 1; c <= 9; c++) {
			if ((buttons[c].getText().equals("X"))
					|| (buttons[c].getText().equals("O"))) {
				nr++;
			}
		}
		if (wins == true) {
			if (player == "X") {
				vito += 1;
				vitoria += 1;
				result = "X Ganhou o jogo!!!";
				display_winner(vito, derr, empate, result);
			}

			if (player == "O") {
				derrot += 1;
				derr += 1;
				result = "O Ganhou o jogo!!!";
				display_winner(vitoria, derrot, empate, result);
			}
		}

		if ((nr == 9) && (counts >= 9) && (!wins)) {
			empate += 1;
			result = "Jogo empatado!!!";

			if (JOptionPane.showConfirmDialog(null, empate + " Empates\n"
					+ "Jogar de novo?", result, 0) != 0) {
				// window.dispose();
			} else
				newgame();
		}
	}

}
