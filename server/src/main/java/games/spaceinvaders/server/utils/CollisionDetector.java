package games.spaceinvaders.server.utils;

import games.spaceinvaders.server.models.Sprite;

public class CollisionDetector {

	public static boolean hasCollided( final Sprite spriteA, final Sprite spriteB ) {
		return spriteA.getX() < spriteB.getX() + spriteB.getWidth() &&   // A's left side is before B's right side
				spriteA.getX() + spriteA.getWidth() > spriteB.getX() &&  // A's right side is after B's left side
				spriteA.getY() < spriteB.getY() + spriteB.getHeight() && // A's top side is before B's bottom side
				spriteA.getY() + spriteA.getHeight() > spriteB.getY();   // A's bottom side is after B's top side
	}
}
