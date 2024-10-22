package games.spaceinvaders.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import games.spaceinvaders.contants.Direction;
import games.spaceinvaders.game.GameManager;
import games.spaceinvaders.game.SpaceInvaders;
import games.spaceinvaders.actors.Bullet;

public class InputHandler implements KeyListener {

	private final SpaceInvaders game;
	private final GameManager gameManager;

	public InputHandler( final SpaceInvaders game, final GameManager gameManager ) {
		this.game = game;
		this.gameManager = gameManager;
	}

	@Override
	public void keyTyped( final KeyEvent e ) {}

	@Override
	public void keyPressed( final KeyEvent e ) {
		final var ship = gameManager.getShip();
		final var bullets = gameManager.getBullets();

		// Ship controls
		if ( e.getKeyCode() == KeyEvent.VK_LEFT ) {
			ship.move( Direction.LEFT );
		} else if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			ship.move( Direction.RIGHT );
		} else if ( e.getKeyCode() == KeyEvent.VK_SPACE ) {
			final var bullet = new Bullet( ship );
			bullets.add( bullet );
		}

		// Restart game
		if ( gameManager.isGameOver() && e.getKeyCode() == KeyEvent.VK_ENTER ) {
			gameManager.resetGame();
			game.restartGameLoop();
		}
	}

	@Override
	public void keyReleased( final KeyEvent e ) {}

}
