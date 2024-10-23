package games.spaceinvaders.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import games.spaceinvaders.input.InputHandler;
import games.spaceinvaders.constants.Board;

public class SpaceInvaders extends JPanel implements ActionListener {

	private final GameManager gameManager;

	public SpaceInvaders( final GameManager gameManager, final InputHandler inputHandler ) {
		setPreferredSize( new Dimension( Board.width, Board.height ) );
		setBackground( Board.color );
		setFocusable( true );
		addKeyListener( inputHandler );

		this.gameManager = gameManager;

		final var gameLoop = new Timer( 1000 / 60, this );
		gameLoop.start();

		final var animationLoop = new Timer( 500, e -> gameManager.toggleAlienAnimation() );
		animationLoop.start();
	}

	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		gameManager.drawGame( g );
	}

	@Override
	public void actionPerformed( final ActionEvent e ) {
		repaint();
	}

}
