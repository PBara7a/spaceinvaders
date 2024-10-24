package games.spaceinvaders.actors;

import games.spaceinvaders.constants.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bullet extends Actor {

	private boolean isUsed;

	public Bullet() {
		setWidth( Board.tileSize / 16 );
		setHeight( Board.tileSize / 4 );
	}

}
