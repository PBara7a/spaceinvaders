package games.spaceinvaders.game;

import java.awt.Graphics;

import games.spaceinvaders.animation.AnimationManager;
import games.spaceinvaders.dto.GameStateDto;
import games.spaceinvaders.model.GameState;
import games.spaceinvaders.utils.Mapper;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GameManager {

	private GameState gameState;
	private AnimationManager animationManager;

	public GameManager( final AnimationManager animationManager ) {
		this.gameState = new GameState();
		this.animationManager = animationManager;
	}

	public void updateLocalGameState( final GameStateDto gameStateDto ) {
		final var updatedState = Mapper.mapToGameState( gameStateDto );
		setGameState( updatedState );
	}

	public void drawGame( final Graphics g ) {
		animationManager.drawGame( g, gameState );
	}

	public void toggleAnimations() {
		animationManager.toggleAlienAnimation();
	}

}
