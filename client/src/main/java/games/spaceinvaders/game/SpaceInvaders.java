package games.spaceinvaders.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import games.spaceinvaders.input.InputHandler;
import games.spaceinvaders.contants.Board;

public class SpaceInvaders extends JPanel implements ActionListener {

	private final Timer gameLoop;
	private final GameManager gameManager;

	public SpaceInvaders() {
		setPreferredSize( new Dimension( Board.width, Board.height ) );
		setBackground( Board.color );
		setFocusable( true );

		final var waveManager = new WaveManager();
		gameManager = new GameManager( waveManager );
		addKeyListener( new InputHandler( this, gameManager ) );

		gameLoop = new Timer( 1000/60, this ); // 60 frames per second;
		gameLoop.start();

		final var animationLoop = new Timer( 500, e -> gameManager.toggleAlienAnimation() );
		animationLoop.start();
	}

	public void restartGameLoop() {
		gameLoop.restart();
	}

	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		gameManager.drawGame( g );
	}

	@Override
	public void actionPerformed( final ActionEvent e ) {
		gameManager.updateGame();
		repaint();
		if ( gameManager.isGameOver() ) {
			gameLoop.stop();
		}
	}

}
