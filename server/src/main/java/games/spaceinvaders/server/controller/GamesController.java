package games.spaceinvaders.server.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import games.spaceinvaders.server.game.StateManager;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class GamesController {

	private final StateManager stateManager;

	@MessageMapping("/join-game")
	public void handleJoinGame( final int gameId ) {
		System.out.println("Received join game: " + gameId);
		stateManager.createGame( gameId );
	}
}
