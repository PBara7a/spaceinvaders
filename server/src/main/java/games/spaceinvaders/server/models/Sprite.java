package games.spaceinvaders.server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Sprite {

	private int width;
	private int height;
	private int x;
	private int y;
	private int xVelocity;

}
