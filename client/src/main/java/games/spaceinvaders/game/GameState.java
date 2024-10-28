package games.spaceinvaders.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import games.spaceinvaders.actors.Alien;
import games.spaceinvaders.actors.Bullet;
import games.spaceinvaders.actors.Ship;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameState {

	private Ship ship;
	private List<Alien> aliens;
	private List<Bullet> bullets;
	private int score;
	private int lives;
	private boolean gameOver;

	public GameState() {
		this.ship = new Ship();
		this.aliens = new ArrayList<>();
		this.bullets = new LinkedList<>();
		this.score = 0;
		this.lives = 3;
		this.gameOver = false;
	}

}
