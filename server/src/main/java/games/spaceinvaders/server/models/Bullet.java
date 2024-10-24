package games.spaceinvaders.server.models;

import games.spaceinvaders.constants.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bullet extends Actor {

	private int yVelocity;
	private boolean isUsed;

	public Bullet( final Actor shooter ) {
		super( Board.tileSize / 16, Board.tileSize / 4,
				shooter.getX() + shooter.getWidth() * 15 / 32, shooter.getY(), 0 );
		this.yVelocity = -10;
		this.isUsed = false;
	}

	public void flyYAxis() {
		setY( getY() + getYVelocity() );
	}
}
