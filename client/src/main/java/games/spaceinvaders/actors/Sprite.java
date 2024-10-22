package games.spaceinvaders.actors;

import java.awt.Image;


public abstract class Sprite {

	private Image image;
	private final int width;
	private final int height;
	private int x;
	private int y;
	private int xVelocity;

	Sprite( final Image image, final int width, final int height, final int x, final int y, final int xVelocity ) {
		this.image = image;
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
	}

	public Image getImage() {
		return image;
	}

	public void setImage( final Image image ) {
		this.image = image;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getY() {
		return y;
	}

	public void setY( final int y ) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX( final int x ) {
		this.x = x;
	}

	public int getXVelocity() {
		return xVelocity;
	}

	public void setXVelocity( final int xVelocity ) {
		this.xVelocity = xVelocity;
	}

}
