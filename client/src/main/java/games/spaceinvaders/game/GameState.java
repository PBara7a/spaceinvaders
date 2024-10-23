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

	public static Ship ship = new Ship();
	public static List<Alien> aliens = new ArrayList<>();
	public static List<Bullet> bullets = new LinkedList<>();
	public static int score = 0;
	public static int lives = 3;
	public static boolean gameOver = false;
	public static int alienCount = 0;

}
