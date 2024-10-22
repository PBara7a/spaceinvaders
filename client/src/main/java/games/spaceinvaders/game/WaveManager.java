package games.spaceinvaders.game;

import java.util.ArrayList;
import java.util.List;

import games.spaceinvaders.actors.Alien;
import games.spaceinvaders.contants.Board;

public class WaveManager {

	private int alienRows = 2;
	private int alienColumns = 3;

	public int getAlienRows() {
		return alienRows;
	}

	public int getAlienColumns() {
		return alienColumns;
	}

	public List<Alien> createFirstWave() {
		reset();
		return createAliens( alienRows, alienColumns );
	}

	public List<Alien> createNextWave() {
		alienRows = Math.min( alienRows + 1, Board.rows - 6 );
		alienColumns = Math.min( alienColumns + 1, Board.columns - 4 );
		return createAliens( alienRows, alienColumns );
	}

	private void reset() {
		alienRows = 2;
		alienColumns = 3;
	}

	private List<Alien> createAliens( final int rows, final int columns ) {
		final var aliens = new ArrayList<Alien>();
		for ( int r = 0; r < rows; r++ ) {
			for ( int c = 0; c < columns; c++ ) {
				aliens.add( new Alien( r, c ) );
			}
		}
		return aliens;
	}
}
