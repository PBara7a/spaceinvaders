package games.spaceinvaders.game;

import java.awt.*;

import org.springframework.stereotype.Service;

import games.spaceinvaders.animation.AlienAnimation;
import games.spaceinvaders.animation.ShipAnimation;
import lombok.Getter;

@Getter
@Service
public class AnimationManager {

	private final Image shipAnimation = ShipAnimation.FULL_HEALTH.getImage();
	private Image alienAnimation = AlienAnimation.ALIEN_A.getImage();

	public void toggleAlienAnimation() {
		if ( alienAnimation == AlienAnimation.ALIEN_A.getImage() ) {
			alienAnimation = AlienAnimation.ALIEN_B.getImage();
		} else {
			alienAnimation = AlienAnimation.ALIEN_A.getImage();
		}
	}

}
