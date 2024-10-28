package games.spaceinvaders.server.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

import org.springframework.stereotype.Service;

import games.spaceinvaders.server.model.Alien;
import games.spaceinvaders.server.model.Bullet;
import games.spaceinvaders.server.model.Ship;
import games.spaceinvaders.constants.Board;
import games.spaceinvaders.server.model.GameState;
import games.spaceinvaders.server.service.GameStateService;
import games.spaceinvaders.server.utils.CollisionDetector;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class StateManager implements ActionListener {

	private final Timer gameLoop;
	private final GameStateService gameStateService;
	private final WaveManager waveManager;

	private Ship ship;
	private List<Alien> aliens;
	private List<Bullet> bullets;

	private int alienCount;
	private int score;
	private int lives;
	private boolean gameOver;

	public StateManager( final GameStateService gameStateService, final WaveManager waveManager ) {
		this.gameStateService = gameStateService;
		this.waveManager = waveManager;
		this.gameLoop = new Timer( 1000/30, this );

		ship = new Ship();
		aliens = waveManager.createFirstWave();
		alienCount = aliens.size();
		bullets = new LinkedList<>();
		score = 0;
		lives = 3;
		gameOver = false;
		gameLoop.start();

	}

	public void updateGame() {
		// Move Aliens
		boolean hasBouncedOnEdge = false;

		for ( Alien alien : aliens ) {
			if ( alien.isAlive() ) {
				alien.moveXAxis();

				if ( alien.getX() + alien.getWidth() >= Board.width - Board.alienGap || alien.getX() <= 0 ) {
					hasBouncedOnEdge = true;
					break;
				}

				if ( CollisionDetector.hasCollided( alien, ship ) || alien.getY() > ship.getY() ) {
					gameOver = true;
				}
			}
		}

		if ( hasBouncedOnEdge ) {
			for ( Alien alien : aliens ) {
				alien.reverseDirection();
				alien.moveYAxis();
			}
		}

		// Move Bullets
		final var bulletIterator = bullets.iterator();
		while ( bulletIterator.hasNext() ) {
			final var bullet = bulletIterator.next();
			bullet.flyYAxis();

			for ( Alien alien : aliens ) {
				if ( !bullet.isUsed() && alien.isAlive() && CollisionDetector.hasCollided( bullet, alien ) ) {
					bullet.setUsed( true );
					alien.setAlive( false );
					alienCount--;
					score += 10;
				}
			}

			if ( bullet.isUsed() || bullet.getY() < 0 ) {
				bulletIterator.remove();
			}
		}

		// Next level
		if ( alienCount == 0 ) {
			score += calculateBonusScore( waveManager.getAlienRows(), waveManager.getAlienColumns() ); // bonus for clearing level
			bullets.clear();
			aliens = waveManager.createNextWave();
			alienCount = aliens.size();
		}
	}

	public void resetGame() {
		ship = new Ship();
		aliens = waveManager.createFirstWave();
		alienCount = aliens.size();
		bullets = new LinkedList<>();
		score = 0;
		lives = 3;
		gameOver = false;
		gameLoop.restart();
	}

	private int calculateBonusScore( final int alienRows, final int alienCols ) {
		return alienCols * alienRows * 100;
	}

	private GameState getGameState() {
		return new GameState( aliens, bullets, ship, score, lives, gameOver );
	}

	@Override
	public void actionPerformed( final ActionEvent e ) {
		updateGame();

		// Broadcast current state
		gameStateService.broadcastGameState( getGameState() );

		if ( isGameOver() ) {
			gameLoop.stop();
		}
	}
}
