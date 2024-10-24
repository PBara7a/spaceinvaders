package games.spaceinvaders.animation;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlienAnimation {

	ALIEN_A( new ImageIcon( Objects.requireNonNull(
			AlienAnimation.class.getClassLoader().getResource( "sprites/alien.png" ) ) ).getImage() ),
	ALIEN_B( new ImageIcon( Objects.requireNonNull(
			AlienAnimation.class.getClassLoader().getResource( "sprites/alien-b.png" ) ) ).getImage() );

	private final Image image;

}
