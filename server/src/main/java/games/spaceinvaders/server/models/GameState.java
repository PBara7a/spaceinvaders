package games.spaceinvaders.server.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameState {

	private List<Alien> aliens;
	private List<Bullet> bullets;
	private Ship ship;
	private int score;
	private int lives;
	private boolean gameOver;

}
