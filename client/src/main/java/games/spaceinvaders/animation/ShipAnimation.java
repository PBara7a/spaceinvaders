package games.spaceinvaders.animation;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShipAnimation {

	FULL_HEALTH( new ImageIcon( Objects.requireNonNull(
			ShipAnimation.class.getClassLoader().getResource( "sprites/ship.png" ) ) ).getImage() );

	private final Image image;

}
