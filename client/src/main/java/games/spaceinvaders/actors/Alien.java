package games.spaceinvaders.actors;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;

import games.spaceinvaders.constants.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alien extends Sprite {

	@Getter
	@AllArgsConstructor
	private enum AlienAnimation {

		ALIEN_A( new ImageIcon( Objects.requireNonNull(
				AlienAnimation.class.getClassLoader().getResource( "sprites/alien.png" ) ) ).getImage() ),
		ALIEN_B( new ImageIcon( Objects.requireNonNull(
				AlienAnimation.class.getClassLoader().getResource( "sprites/alien-b.png" ) ) ).getImage() );

		private final Image image;

	}

	private boolean alive;

	public Alien() {
		setImage( AlienAnimation.ALIEN_A.getImage() );
		setWidth( Board.tileSize );
		setHeight( Board.tileSize / 2 );
	}

	public void toggleAnimation() {
		if ( getImage() == AlienAnimation.ALIEN_A.getImage() ) {
			setImage( AlienAnimation.ALIEN_B.getImage() );
		} else {
			setImage( AlienAnimation.ALIEN_A.getImage() );
		}
	}
}
