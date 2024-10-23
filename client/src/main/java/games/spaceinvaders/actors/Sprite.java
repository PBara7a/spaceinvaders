package games.spaceinvaders.actors;

import java.awt.Image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Sprite {

	private Image image;
	private int width;
	private int height;
	private int x;
	private int y;
	private int xVelocity;

}
