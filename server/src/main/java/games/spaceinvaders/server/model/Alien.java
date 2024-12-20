package games.spaceinvaders.server.model;

import games.spaceinvaders.constants.Board;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Alien extends Actor {

	private boolean alive;

	public Alien( final int r, final int c ) {
		super( Board.tileSize / 10 * 8, Board.tileSize / 2,
				c * (Board.tileSize + Board.alienGap) + Board.tileSize,
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
