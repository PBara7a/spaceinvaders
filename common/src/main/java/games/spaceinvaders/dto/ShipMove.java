package games.spaceinvaders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShipMove {
	private int player;
	private int shipX;
	private int shipY;
}
