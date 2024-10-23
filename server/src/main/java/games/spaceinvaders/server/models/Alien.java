package games.spaceinvaders.server.models;

import games.spaceinvaders.constants.Board;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Alien extends Sprite {

	private boolean alive;

	public Alien( final int r, final int c ) {
		super( Board.tileSize, Board.tileSize / 2,
				c * Board.tileSize + Board.tileSize,
				r * Board.tileSize + Board.tileSize * 2, 1 );
		this.alive = true;
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

}
