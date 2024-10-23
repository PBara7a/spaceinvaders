package games.spaceinvaders.actors;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;

import games.spaceinvaders.constants.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Ship extends Sprite {

	@Getter
	@AllArgsConstructor
	private enum ShipAnimation {

		FULL_HEALTH( new ImageIcon( Objects.requireNonNull(
				ShipAnimation.class.getClassLoader().getResource( "sprites/ship.png" ) ) ).getImage() );

		private final Image image;

	}

	public Ship() {
		super( ShipAnimation.FULL_HEALTH.getImage(), Board.tileSize, Board.tileSize / 2,
				Board.tileSize * Board.columns / 2 - Board.tileSize,
				Board.height - Board.tileSize * 2, Board.tileSize );
	}

}
