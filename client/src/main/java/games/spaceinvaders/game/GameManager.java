package games.spaceinvaders.game;

import games.spaceinvaders.dto.GameStateDto;
import games.spaceinvaders.utils.Mapper;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GameManager {

	private GameState gameState;

	public GameManager() {
		this.gameState = new GameState();
	}

	public void updateLocalGameState( final GameStateDto gameStateDto ) {
		final var updatedState = Mapper.mapToGameState( gameStateDto );
		setGameState( updatedState );
	}

}
