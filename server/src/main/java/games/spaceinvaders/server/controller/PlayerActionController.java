package games.spaceinvaders.server.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import games.spaceinvaders.dto.RestartGame;
import games.spaceinvaders.dto.ShipMove;
import games.spaceinvaders.dto.ShipShot;
import games.spaceinvaders.server.model.Bullet;
import games.spaceinvaders.server.game.StateManager;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class PlayerActionController {

	private final StateManager stateManager;

	@MessageMapping("/game/{gameId}/move")
	public void handleShipMove( @DestinationVariable final int gameId, final ShipMove message ) {
		System.out.println( "Received message: " + message );

		final var ship = stateManager.getGame( gameId ).getState().getShip();
		ship.move( message.getDirection() );
	}

	@MessageMapping("/game/{gameId}/shoot")
	public void handleShipShot( @DestinationVariable final int gameId, final ShipShot message ) {
		System.out.println( "Received message: " + message );

		final var ship = stateManager.getGame( gameId ).getState().getShip();
		final var bullets = stateManager.getGame( gameId ).getState().getBullets();
		final var bullet = new Bullet( ship );
		bullets.add( bullet );
	}

	@MessageMapping("/game/{gameId}/restart")
	public void handleRestart( @DestinationVariable final int gameId, final RestartGame message ) {
		System.out.println( "Received message: " + message );

		stateManager.resetGame( gameId );
	}

}
