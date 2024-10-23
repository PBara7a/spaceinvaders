package games.spaceinvaders.utils;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import games.spaceinvaders.actors.Alien;
import games.spaceinvaders.actors.Bullet;
import games.spaceinvaders.actors.Ship;
import games.spaceinvaders.dto.GameStateDto;

public class Mapper {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static List<Alien> getAliensFromGameStateDto( final GameStateDto gameStateDto ) {
		return objectMapper.convertValue( gameStateDto.getAliens(), new TypeReference<List<Alien>>() {} );
	}

	public static List<Bullet> getBulletsFromGameStateDto( final GameStateDto gameStateDto ) {
		return objectMapper.convertValue( gameStateDto.getBullets(), new TypeReference<LinkedList<Bullet>>() {} );
	}

	public static Ship getShipFromGameStateDto( final GameStateDto gameStateDto ) {
		return objectMapper.convertValue( gameStateDto.getShip(), Ship.class );
	}
}
