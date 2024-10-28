package games.spaceinvaders.server.model;

import games.spaceinvaders.constants.Board;
import games.spaceinvaders.constants.Direction;


public class Ship extends Actor {

	public Ship() {
		super( Board.tileSize, Board.tileSize / 2,
				Board.tileSize * Board.columns / 2 - Board.tileSize,
				Board.height - Board.tileSize * 2, Board.tileSize / 2 );
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
