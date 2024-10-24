package games.spaceinvaders.actors;

import games.spaceinvaders.constants.Board;


public class Ship extends Actor {

	public Ship() {
		super( Board.tileSize, Board.tileSize / 2,
				Board.tileSize * Board.columns / 2 - Board.tileSize,
				Board.height - Board.tileSize * 2 );
	}

}
