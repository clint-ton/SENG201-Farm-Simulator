package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JTextArea;

/**
 * End of game GUI window
 * @author jke99, cjw237
 *
 */

public class EndGameWindow {

	private JFrame endGameWindow;
	private Game game;


	/**
	 * Create the application.
	 * @param game Game state passed to window
	 */
	public EndGameWindow(Game game) {
		this.game = game;
		initialize();
		endGameWindow.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		endGameWindow = new JFrame();
		endGameWindow.setTitle("Game Completed");
		endGameWindow.setBounds(100, 100, 1000, 600);
		endGameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endGameWindow.getContentPane().setLayout(null);
		
		/**
		 * Header labels
		 */
		JLabel gameCompleteLbl = new JLabel("Game Completed");
		gameCompleteLbl.setHorizontalAlignment(SwingConstants.CENTER);
		gameCompleteLbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		gameCompleteLbl.setBounds(10, 33, 966, 48);
		endGameWindow.getContentPane().add(gameCompleteLbl);
		
		JLabel youScoredLbl = new JLabel("You scored a total of:");
		youScoredLbl.setHorizontalAlignment(SwingConstants.CENTER);
		youScoredLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		youScoredLbl.setBounds(142, 174, 703, 48);
		endGameWindow.getContentPane().add(youScoredLbl);
		
		JLabel finalScoreLbl = new JLabel("Your final score is:");
		finalScoreLbl.setHorizontalAlignment(SwingConstants.CENTER);
		finalScoreLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		finalScoreLbl.setBounds(142, 375, 703, 48);
		endGameWindow.getContentPane().add(finalScoreLbl);
		
		
		/**
		 * Gets player name
		 */
		JLabel congratulationsLBl = new JLabel("Congratulations " + game.getPlayer().getName() + " - You Finished the Game!");
		congratulationsLBl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		congratulationsLBl.setHorizontalAlignment(SwingConstants.CENTER);
		congratulationsLBl.setBounds(142, 116, 703, 48);
		endGameWindow.getContentPane().add(congratulationsLBl);
		
		/**
		 * Calls method in game class to calculate scores from each area of the game and displays string representation of the scores 
		 */
		JTextPane scoresPane = new JTextPane();
		scoresPane.setEditable(false);
		scoresPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scoresPane.setBackground(UIManager.getColor("Button.background"));
		scoresPane.setBounds(184, 254, 630, 106);
		scoresPane.setText(game.endGameMessage());
		endGameWindow.getContentPane().add(scoresPane);
		
		/**
		 * Calls method in Game class to calculate and display final total score
		 */
		JLabel pointsLbl = new JLabel(String.format("%.0f",game.endGameTotal()) + " Points");
		pointsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		pointsLbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		pointsLbl.setBounds(142, 414, 703, 48);
		endGameWindow.getContentPane().add(pointsLbl);
		
        /**
         * Calls method in Game class to restart game and dispose of current game
         */
		JButton playAgainBtn = new JButton("Play Again");
		playAgainBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.restartGame();
				endGameWindow.dispose();
			}
		});
		playAgainBtn.setBounds(799, 509, 158, 29);
		playAgainBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		endGameWindow.getContentPane().add(playAgainBtn);
		
		
		/**
		 * Closes window and ends game
		 */
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endGameWindow.dispose();
			}
		});
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		closeBtn.setBounds(27, 509, 158, 29);
		endGameWindow.getContentPane().add(closeBtn);
		
		
		
		
	}
}
