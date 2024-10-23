package games.spaceinvaders.server.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import games.spaceinvaders.dto.RestartGame;
import games.spaceinvaders.dto.ShipMove;
import games.spaceinvaders.dto.ShipShot;
import games.spaceinvaders.server.models.Bullet;
import games.spaceinvaders.server.game.StateManager;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class PlayerActionController {

	private final StateManager stateManager;

	@MessageMapping("/move")
	public void handleShipMove( final ShipMove message ) {
		System.out.println( "Received message: " + message );

		final var ship = stateManager.getShip();
		ship.move( message.getDirection() );
	}

	@MessageMapping("/shoot")
	public void handleShipShot( final ShipShot message ) {
 		System.out.println( "Received message: " + message );

		final var ship = stateManager.getShip();
		final var bullets = stateManager.getBullets();
		final var bullet = new Bullet( ship );
		bullets.add( bullet );
	}

	@MessageMapping("/restart")
	public void handleRestart( final RestartGame message ) {
		System.out.println( "Received message: " + message );

		stateManager.resetGame();
	}

}
