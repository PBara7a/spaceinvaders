package games.spaceinvaders.actors;

import games.spaceinvaders.contants.Board;

public class Bullet extends Sprite {

	private final int yVelocity;
	private boolean hasHit;

	public Bullet( final Sprite shooter ) {
		super( null, Board.tileSize / 16, Board.tileSize / 4,
				shooter.getX() + shooter.getWidth() * 15 / 32, shooter.getY(), 0 );
		this.yVelocity = -10;
		this.hasHit = false;
	}

	public int getYVelocity() {
		return yVelocity;
	}

	public boolean hasHit() {
		return hasHit;
	}

	public void setHasHit( final boolean hasHit ) {
		this.hasHit = hasHit;
	}

	public void flyYAxis() {
		setY( getY() + getYVelocity() );
	}
}
