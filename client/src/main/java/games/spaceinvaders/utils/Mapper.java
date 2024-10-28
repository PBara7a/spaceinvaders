package games.spaceinvaders.utils;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import games.spaceinvaders.actors.Alien;
import games.spaceinvaders.actors.Bullet;
import games.spaceinvaders.actors.Ship;
import games.spaceinvaders.dto.GameStateDto;
import games.spaceinvaders.game.GameState;

public class Mapper {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static GameState mapToGameState( final GameStateDto gameStateDto ) {
		final var gameState = new GameState();
		gameState.setAliens( objectMapper.convertValue( gameStateDto.getAliens(), new TypeReference<List<Alien>>() {} ) );
		gameState.setBullets( objectMapper.convertValue( gameStateDto.getBullets(), new TypeReference<LinkedList<Bullet>>() {} ) );
		gameState.setShip( objectMapper.convertValue( gameStateDto.getShip(), Ship.class) );
		gameState.setScore( gameStateDto.getScore() );
		gameState.setLives( gameStateDto.getLives() );
		gameState.setGameOver( gameStateDto.isGameOver() );
		return gameState;
	}
}
