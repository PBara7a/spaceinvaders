package games.spaceinvaders.server.utils;

import games.spaceinvaders.server.model.Actor;

public class CollisionDetector {

	public static boolean hasCollided( final Actor actorA, final Actor actorB ) {
		return actorA.getX() < actorB.getX() + actorB.getWidth() &&   // A's left side is before B's right side
				actorA.getX() + actorA.getWidth() > actorB.getX() &&  // A's right side is after B's left side
				actorA.getY() < actorB.getY() + actorB.getHeight() && // A's top side is before B's bottom side
				actorA.getY() + actorA.getHeight() > actorB.getY();   // A's bottom side is after B's top side
	}
}
