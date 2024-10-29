package games.spaceinvaders.server.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

	private int id;
	private GameState state;

	public Game( final int id ) {
		this.id = id;
		this.state = new GameState();
	}

}
