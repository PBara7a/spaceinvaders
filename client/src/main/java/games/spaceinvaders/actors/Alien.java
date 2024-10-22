package games.spaceinvaders.actors;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;


import games.spaceinvaders.contants.Board;

public class Alien extends Sprite {

	private enum AlienAnimation {

		ALIEN_A( new ImageIcon( Objects.requireNonNull(
				AlienAnimation.class.getClassLoader().getResource( "sprites/alien.png" ) ) ).getImage() ),
		ALIEN_B( new ImageIcon( Objects.requireNonNull(
				AlienAnimation.class.getClassLoader().getResource( "sprites/alien-b.png" ) ) ).getImage() );

		private final Image image;

		AlienAnimation( final Image image ) {
			this.image = image;
		}

		public Image getImage() {
			return image;
		}
	}

	private boolean alive;

	public Alien( final int r, final int c ) {
		super( AlienAnimation.ALIEN_A.getImage(), Board.tileSize, Board.tileSize / 2,
				c * Board.tileSize + Board.tileSize, r * Board.tileSize + Board.tileSize * 2, 1 );
		this.alive = true;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive( final boolean alive ) {
		this.alive = alive;
	}

	public void moveXAxis() {
		setX( getX() + getXVelocity() );
	}

	public void moveYAxis() {
		setY( getY() + getHeight() );
	}

	public void reverseDirection() {
		setXVelocity( getXVelocity() * -1 );
	}

	public void toggleAnimation() {
		if ( getImage() == AlienAnimation.ALIEN_A.getImage() ) {
			setImage( AlienAnimation.ALIEN_B.getImage() );
		} else {
			setImage( AlienAnimation.ALIEN_A.getImage() );
		}
	}
}
