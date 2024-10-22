package games.spaceinvaders.actors;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;

import games.spaceinvaders.contants.Board;
import games.spaceinvaders.contants.Direction;

public class Ship extends Sprite {

	private enum ShipAnimation {

		FULL_HEALTH( new ImageIcon( Objects.requireNonNull(
				ShipAnimation.class.getClassLoader().getResource( "sprites/ship.png" ) ) ).getImage() );

		private final Image image;

		ShipAnimation( final Image image ) {
			this.image = image;
		}

		public Image getImage() {
			return image;
		}
	}

	public Ship() {
		super( ShipAnimation.FULL_HEALTH.getImage(), Board.tileSize, Board.tileSize / 2,
				Board.tileSize * Board.columns / 2 - Board.tileSize,
				Board.height - Board.tileSize * 2, Board.tileSize );
	}

	public void resetShip() {
		this.setX( Board.tileSize * Board.columns / 2 - Board.tileSize );
		this.setY( Board.height - Board.tileSize * 2 );
	}

	public void move( final Direction direction ) {
		if ( direction == Direction.RIGHT &&
				getX() + getWidth() + getXVelocity() <= Board.width ) {
			setX( getX() + getXVelocity() );
		} else if ( direction == Direction.LEFT && getX() - getXVelocity() >= 0 ) {
			setX( getX() - getXVelocity() );
		}
	}

}
