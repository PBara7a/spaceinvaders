package games.spaceinvaders.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameStateDto {

	private List<AlienDto> aliens;
	private List<BulletDto> bullets;
	private ShipDto ship;
	private int score;
	private int lives;
	private boolean gameOver;

	@Override
	public String toString() {
		return String.format( "GameStateDto(aliens=%d, bullets=%d, ship=%s, score=%d, lives=%d, gameOver=%b)",
				aliens.size(), bullets.size(), ship, score, lives, gameOver);
	}
}
