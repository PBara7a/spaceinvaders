package games.spaceinvaders.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShipDto {

	private int x;
	private int y;
	private int xVelocity;

}
