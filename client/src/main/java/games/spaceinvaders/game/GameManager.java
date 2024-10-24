package games.spaceinvaders.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.springframework.stereotype.Service;

import games.spaceinvaders.constants.Board;
import games.spaceinvaders.actors.Alien;
import games.spaceinvaders.actors.Bullet;
import games.spaceinvaders.dto.GameStateDto;
import games.spaceinvaders.utils.Mapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameManager {

	private final AnimationManager animationManager;

	public void updateLocalGameState( final GameStateDto gameState ) {
		GameState.aliens = Mapper.getAliensFromGameStateDto( gameState );
		GameState.bullets = Mapper.getBulletsFromGameStateDto( gameState );
		GameState.ship = Mapper.getShipFromGameStateDto( gameState );
		GameState.score = gameState.getScore();
		GameState.lives = gameState.getLives();
		GameState.gameOver = gameState.isGameOver();
	}

	public void drawGame( final Graphics g ) {
		// Draw Ship
		g.drawImage( animationManager.getShipAnimation(), GameState.ship.getX(), GameState.ship.getY(), GameState.ship.getWidth(), GameState.ship.getHeight(), null );

		// Draw Aliens
		for ( Alien alien : GameState.aliens ) {
			if ( alien.isAlive() ) {
				g.drawImage( animationManager.getAlienAnimation(), alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight(), null );
			}
		}

		// Draw Bullets
		g.setColor( Color.WHITE );
		for ( Bullet bullet : GameState.bullets ) {
			if ( !bullet.isUsed() ) {
				g.fillRect( bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight() );
			}
		}

		// Draw score
		g.setColor( Color.WHITE );
		g.setFont( new Font( Font.MONOSPACED, Font.PLAIN, 16 ) );
		if ( GameState.gameOver ) {
			g.drawString( "Game Over: " + GameState.score, Board.tileSize / 3, Board.tileSize / 3 * 2 );
			g.drawString( "Press Enter to restart", Board.width / 4 + Board.tileSize / 2, Board.height / 2 );
		} else {
			// Player 1 score
			g.drawString( "SCORE<1>", Board.tileSize / 3, Board.tileSize / 3 * 2 );
			g.drawString( String.format("%04d", GameState.score), Board.tileSize, Board.tileSize / 3 * 4 );

			// High score
			g.drawString( "HIGH SCORE", Board.width / 3 + Board.tileSize, Board.tileSize / 3 * 2 );
			g.drawString( String.format("%04d", 0), Board.width / 3 + Board.tileSize * 2, Board.tileSize / 3 * 4 );

			// Lives
			g.setFont( new Font( Font.MONOSPACED, Font.PLAIN, 12 ) );
			g.drawString( "LIVES: ", 12, Board.height - 8 );
			for ( int i = 0; i < GameState.lives; i++ ) {
				g.drawImage( animationManager.getShipAnimation(), 58 + i * GameState.ship.getWidth() / 2, Board.height - GameState.ship.getHeight(), GameState.ship.getWidth() / 2, GameState.ship.getHeight() / 2, null );
			}
		}
	}

	public void toggleAlienAnimation() {
		animationManager.toggleAlienAnimation();
	}

}
