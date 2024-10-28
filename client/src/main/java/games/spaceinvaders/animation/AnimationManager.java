package games.spaceinvaders.animation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import org.springframework.stereotype.Service;

import games.spaceinvaders.actors.Alien;
import games.spaceinvaders.actors.Bullet;
import games.spaceinvaders.constants.Board;
import games.spaceinvaders.model.GameState;
import lombok.Getter;

@Getter
@Service
public class AnimationManager {

	private final Image shipAnimation = ShipAnimation.FULL_HEALTH.getImage();
	private Image alienAnimation = AlienAnimation.ALIEN_A.getImage();

	public void toggleAlienAnimation() {
		if ( alienAnimation == AlienAnimation.ALIEN_A.getImage() ) {
			alienAnimation = AlienAnimation.ALIEN_B.getImage();
		} else {
			alienAnimation = AlienAnimation.ALIEN_A.getImage();
		}
	}

	public void drawGame( final Graphics g, final GameState gameState ) {
		// Draw Ship
		g.drawImage( getShipAnimation(), gameState.getShip().getX(), gameState.getShip().getY(), gameState.getShip().getWidth(), gameState.getShip().getHeight(), null );

		// Draw Aliens
		for ( Alien alien : gameState.getAliens() ) {
			if ( alien.isAlive() ) {
				g.drawImage( getAlienAnimation(), alien.getX(), alien.getY(), alien.getWidth(), alien.getHeight(), null );
			}
		}

		// Draw Bullets
		g.setColor( Color.WHITE );
		for ( Bullet bullet : gameState.getBullets() ) {
			if ( !bullet.isUsed() ) {
				g.fillRect( bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight() );
			}
		}

		// Draw score
		g.setColor( Color.WHITE );
		g.setFont( new Font( Font.MONOSPACED, Font.PLAIN, 16 ) );
		if ( gameState.isGameOver() ) {
			g.drawString( "Game Over: " + gameState.getScore(), Board.tileSize / 3, Board.tileSize / 3 * 2 );
			g.drawString( "Press Enter to restart", Board.width / 4 + Board.tileSize / 2, Board.height / 2 );
		} else {
			// Player 1 score
			g.drawString( "SCORE<1>", Board.tileSize / 3, Board.tileSize / 3 * 2 );
			g.drawString( String.format( "%04d", gameState.getScore() ), Board.tileSize, Board.tileSize / 3 * 4 );

			// High score
			g.drawString( "HIGH SCORE", Board.width / 3 + Board.tileSize, Board.tileSize / 3 * 2 );
			g.drawString( String.format( "%04d", 0 ), Board.width / 3 + Board.tileSize * 2, Board.tileSize / 3 * 4 );

			// Lives
			g.setFont( new Font( Font.MONOSPACED, Font.PLAIN, 12 ) );
			g.drawString( "LIVES: ", 12, Board.height - 8 );
			for ( int i = 0; i < gameState.getLives(); i++ ) {
				g.drawImage( getShipAnimation(), 58 + i * gameState.getShip().getWidth() / 2, Board.height - gameState.getShip().getHeight(), gameState.getShip().getWidth() / 2, gameState.getShip().getHeight() / 2, null );
			}
		}
	}

}
