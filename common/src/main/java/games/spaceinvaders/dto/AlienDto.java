package games.spaceinvaders.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AlienDto {

	private int x;
	private int y;
	private boolean alive;

}
