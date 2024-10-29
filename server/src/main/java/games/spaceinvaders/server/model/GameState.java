package games.spaceinvaders.server.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameState {

	private List<Alien> aliens;
	private List<Bullet> bullets;
	private Ship ship;
	private int score;
	private int lives;
	private boolean gameOver;
	private int alienCount;

	public GameState() {
		this.score = 0;
		this.lives = 3;
		this.gameOver = false;
	}

	public GameState( final List<Alien> aliens, final List<Bullet> bullets, final Ship ship ) {
		this.aliens = aliens;
		this.bullets = bullets;
		this.ship = ship;
		this.alienCount = aliens.size();
		this.score = 0;
		this.lives = 3;
		this.gameOver = false;
	}

}
