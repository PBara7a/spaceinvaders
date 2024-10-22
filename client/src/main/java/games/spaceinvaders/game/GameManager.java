package games.spaceinvaders.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import games.spaceinvaders.contants.Board;
import games.spaceinvaders.actors.Alien;
import games.spaceinvaders.actors.Bullet;
import games.spaceinvaders.actors.Ship;
import games.spaceinvaders.utils.CollisionDetector;

public class GameManager {

	private final WaveManager waveManager;

	private Ship ship;
	private List<Alien> aliens;
	private List<Bullet> bullets;

	private int alienCount;
	private int score;
	private int lives;
	private boolean gameOver;

	public GameManager( final WaveManager waveManager ) {
		this.waveManager = waveManager;
		resetGame();
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public Ship getShip() {
		return ship;
	}

	public List<Bullet> getBullets() {
		return bullets;
	}

	public void updateGame() {
		// Move Aliens
		boolean hasBouncedOnEdge = false;

		for ( Alien alien : aliens ) {
			if ( alien.isAlive() ) {
				alien.moveXAxis();

				if ( alien.getX() + alien.getWidth() >= Board.width || alien.getX() <= 0 ) {
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
				if ( !bullet.hasHit() && alien.isAlive() && CollisionDetector.hasCollided( bullet, alien ) ) {
					bullet.setHasHit( true );
					alien.setAlive( false );
					alienCount--;
					score += 10;
				}
			}

			if ( bullet.hasHit() || bullet.getY() < 0 ) {
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

	public void drawGame( final Graphics g ) {
		// Draw Ship
		g.drawImage( ship.getImage(), ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight(), null );

		// Draw Aliens
		for ( Alien alien : aliens ) {
			if ( alien.isAlive() ) {
				g.drawImage( alien.getImage(), alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight(), null );
			}
		}

		// Draw Bullets
		g.setColor( Color.WHITE );
		for ( Bullet bullet : bullets ) {
			if ( !bullet.hasHit() ) {
				g.fillRect( bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight() );
			}
		}

		// Draw score
		g.setColor( Color.WHITE );
		g.setFont( new Font( Font.MONOSPACED, Font.PLAIN, 16 ) );
		if ( gameOver ) {
			g.drawString( "Game Over: " + score, Board.tileSize / 3, Board.tileSize / 3 * 2 );
			g.drawString( "Press Enter to restart", Board.width / 4 + Board.tileSize / 2, Board.height / 2 );
		} else {
			// Player 1 score
			g.drawString( "SCORE<1>", Board.tileSize / 3, Board.tileSize / 3 * 2 );
			g.drawString( String.format("%04d", score), Board.tileSize, Board.tileSize / 3 * 4 );

			// High score
			g.drawString( "HIGH SCORE", Board.width / 3 + Board.tileSize, Board.tileSize / 3 * 2 );
			g.drawString( String.format("%04d", 0), Board.width / 3 + Board.tileSize * 2, Board.tileSize / 3 * 4 );

			// Lives
			g.setFont( new Font( Font.MONOSPACED, Font.PLAIN, 12 ) );
			g.drawString( "LIVES: ", 12, Board.height - 8 );
			for ( int i = 0; i < lives; i++ ) {
				g.drawImage( ship.getImage(), 58 + i * ship.getWidth() / 2, Board.height - ship.getHeight(), ship.getWidth() / 2, ship.getHeight() / 2, null );
			}
		}
	}

	public void toggleAlienAnimation() {
		for ( Alien alien : aliens ) {
			alien.toggleAnimation();
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
	}

	private int calculateBonusScore( final int alienRows, final int alienCols ) {
		return alienCols * alienRows * 100;
	}

}
