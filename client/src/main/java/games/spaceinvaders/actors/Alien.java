package games.spaceinvaders.actors;

import games.spaceinvaders.constants.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alien extends Actor {

	private boolean alive;

	public Alien() {
		setWidth( Board.tileSize );
		setHeight( Board.tileSize / 2 );
	}

}
